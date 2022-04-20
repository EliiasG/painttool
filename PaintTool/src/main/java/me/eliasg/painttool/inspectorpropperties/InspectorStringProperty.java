package me.eliasg.painttool.inspectorpropperties;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class InspectorStringProperty implements InspectorProperty
{
    private final HBox pane;

    public InspectorStringProperty(String name, StringProperty property)
    {
        Label label = new Label(name);
        TextField textField = new TextField(property.getValue());

        property.bind(textField.textProperty());
        pane = new HBox(label, textField);
        pane.setSpacing(5);
        pane.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    public Pane getPane()
    {
        return pane;
    }
}
