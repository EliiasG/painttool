package me.eliasg.painttool.rendering;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import me.eliasg.painttool.Geometry;
import me.eliasg.painttool.PaintToolScene;
import me.eliasg.painttool.ProgramState;
import me.eliasg.painttool.Vector;


public class ViewportRenderer
{
    private Canvas canvas;
    private ScaleConverter scaleConverter;
    private Pane viewport;
    private double oldMouseX;
    private double oldMouseY;
    private final GridRenderer gridRenderer;
    private final SceneRenderer sceneRenderer;

    public ViewportRenderer(Pane root, PaintToolScene scene, ProgramState programState)
    {
        viewport = new Pane();
        viewport.setPrefHeight(Double.MAX_VALUE);
        viewport.setPrefWidth(Double.MAX_VALUE);
        canvas = new Canvas(500, 500);
        canvas.heightProperty().bind(viewport.heightProperty());
        canvas.widthProperty().bind(viewport.widthProperty());
        viewport.getChildren().add(canvas);
        scaleConverter = new ScaleConverter(canvas.widthProperty(), canvas.heightProperty());

        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                render();
            }
        };

        timer.start();

        root.onMouseMovedProperty().set(event ->
        {
            oldMouseX = event.getSceneX();
            oldMouseY = event.getSceneY();
        });

        root.setOnScroll(event ->
        {
            if(viewport.isHover()) scaleConverter.setZoom(scaleConverter.getZoom() + (float) (event.getDeltaY() / 100f) * scaleConverter.getZoom());
        });

        root.onMouseDraggedProperty().set(event ->
        {
            if(viewport.isHover() && event.isSecondaryButtonDown())
            {
                scaleConverter.setOffset(Vector.add(scaleConverter.getOffset(),Vector.divide(new Vector(event.getSceneX() - oldMouseX, event.getSceneY() - oldMouseY), scaleConverter.getZoom())));
            }

            oldMouseX = event.getSceneX();
            oldMouseY = event.getSceneY();
        });

        gridRenderer = new GridRenderer(scaleConverter);
        sceneRenderer = new SceneRenderer(scene, scaleConverter, programState);
    }

    public void render()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gridRenderer.render(gc);
        sceneRenderer.render(gc);
    }

    public Pane getViewport()
    {
        return viewport;
    }
}
