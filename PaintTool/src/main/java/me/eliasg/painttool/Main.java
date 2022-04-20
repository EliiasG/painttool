package me.eliasg.painttool;

import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import me.eliasg.painttool.popups.TextWindow;
import me.eliasg.painttool.rendering.ViewportRenderer;

public class Main extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Paint Tool");
        BorderPane sceneRoot = new BorderPane();

        ProgramState programState = new ProgramState();
        PaintToolScene paintToolScene = new PaintToolScene(programState);
        ViewportRenderer viewportRenderer = new ViewportRenderer(sceneRoot, paintToolScene, programState);

        sceneRoot.setLeft(paintToolScene.getTreeView());
        sceneRoot.setCenter(viewportRenderer.getViewport());
        sceneRoot.setRight(paintToolScene.getInspector());

        viewportRenderer.render();
        sceneRoot.setId("main-pane");
        Scene scene = new Scene(sceneRoot, 1200, 600);
        stage.setScene(scene);
        programState.setScene(scene);
        stage.show();
        scene.getStylesheets().add("Default.css");
        new InputController(paintToolScene, programState);
        viewportRenderer.render();
    }
}

