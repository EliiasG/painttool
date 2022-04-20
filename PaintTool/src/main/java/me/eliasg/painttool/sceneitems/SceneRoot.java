package me.eliasg.painttool.sceneitems;

import javafx.scene.layout.VBox;
import me.eliasg.painttool.ProjectSettings;
import me.eliasg.painttool.inspectorpropperties.InspectorIntProperty;

public class SceneRoot implements SceneItem
{

    private final VBox vbox;

    public SceneRoot()
    {
        vbox = new VBox();
        InspectorIntProperty size = new InspectorIntProperty("Size", ProjectSettings.sizeProperty(), 1, 128);
        vbox.getChildren().addAll(size.getPane());
    }

    @Override
    public VBox getInspector()
    {
        return vbox;
    }

    @Override
    public boolean isDrawOnTopOfParent()
    {
        return false;
    }

    @Override
    public String toString()
    {
        return "Project";
    }

}
