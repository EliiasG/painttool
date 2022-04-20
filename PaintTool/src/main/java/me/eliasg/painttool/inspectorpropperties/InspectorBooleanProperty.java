package me.eliasg.painttool.inspectorpropperties;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class InspectorBooleanProperty implements InspectorProperty
{
    private final Pane pane;

    public InspectorBooleanProperty(String name, BooleanProperty value)
    {
        CheckBox checkBox = new CheckBox();
        checkBox.selectedProperty().bindBidirectional(value);
        pane = new HBox(new Label(name), checkBox);
    }


    @Override
    public Pane getPane()
    {
        return pane;
    }
}
