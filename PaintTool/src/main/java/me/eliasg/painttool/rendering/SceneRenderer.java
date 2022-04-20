package me.eliasg.painttool.rendering;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TreeItem;
import me.eliasg.painttool.Geometry;
import me.eliasg.painttool.PaintToolScene;
import me.eliasg.painttool.ProgramState;
import me.eliasg.painttool.Vector;
import me.eliasg.painttool.sceneitems.SceneItem;
import me.eliasg.painttool.sceneitems.SceneObject;

public class SceneRenderer
{
    private final PaintToolScene scene;
    private final ScaleConverter scaleConverter;
    private final GeometryRenderer geometryRenderer;

    public SceneRenderer(PaintToolScene scene, ScaleConverter scaleConverter, ProgramState programState)
    {
        this.scene = scene;
        this.scaleConverter = scaleConverter;
        this.geometryRenderer = new GeometryRenderer(programState);
    }

    public void render(GraphicsContext gc)
    {
        gc.save();
        Vector corner = scaleConverter.getScreenPosition(new Vector(0, 0));
        gc.translate(corner.getX(), corner.getY());
        gc.scale(scaleConverter.getZoom(), scaleConverter.getZoom());
        renderSceneObject(gc, scene.getRoot());
        gc.restore();
    }

    private void renderSceneObject(GraphicsContext gc, TreeItem<SceneItem> object)
    {
        //save the current transform
        gc.save();
        //render the children that should be below the object
        if(object.getValue() instanceof SceneObject sceneObject)
        {
            Vector translation = sceneObject.getPosition();
            gc.translate(translation.getX(), translation.getY());
            gc.rotate(sceneObject.getRotation());
            Vector scale = sceneObject.getScale();
            gc.scale(scale.getX(), scale.getY());
        }

        for (TreeItem<SceneItem> child : object.getChildren())
        {
            if(!child.getValue().isDrawOnTopOfParent()) renderSceneObject(gc, child);
        }
        //render the object
        if(object.getValue() instanceof SceneObject sceneObject)
        {
            for (Geometry geometry : sceneObject.getGeometry().geometriesProperty())
            {
                geometryRenderer.render(gc, geometry);
            }
        }
        //render the children that should be above the object
        for (TreeItem<SceneItem> child : object.getChildren())
        {
            if(child.getValue().isDrawOnTopOfParent()) renderSceneObject(gc, child);
        }
        //restore the transform
        gc.restore();
    }

}
