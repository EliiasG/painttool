package me.eliasg.painttool.sceneitems;

import javafx.scene.layout.VBox;

public interface SceneItem
{
    VBox getInspector();

    boolean isDrawOnTopOfParent();
}
