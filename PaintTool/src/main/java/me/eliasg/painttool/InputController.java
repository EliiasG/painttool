package me.eliasg.painttool;

import javafx.scene.Scene;
import me.eliasg.painttool.popups.TextWindow;
import me.eliasg.painttool.sceneitems.SceneObject;

public class InputController
{
    public InputController(PaintToolScene paintToolScene, ProgramState programState)
    {
        programState.getScene().setOnKeyPressed(e ->
        {
            switch (e.getCode())
            {
                case PLUS ->
                {
                    TextWindow textWindow = new TextWindow("Add Object", "Name:");
                    if (textWindow.getResult() == null) return;

                    SceneObject sceneObject = new SceneObject(textWindow.getResult(), paintToolScene.getTreeView(), programState);
                    paintToolScene.addObject(sceneObject);
                }
                case DELETE ->
                {
                    paintToolScene.deleteSelected();
                }
            }
        });
    }
}
