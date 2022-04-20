package me.eliasg.painttool.inspectorpropperties;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
import me.eliasg.painttool.popups.TextWindow;

public class InspectorIntProperty implements InspectorProperty
{
    private final IntegerProperty value;
    private final String name;
    private HBox pane;
    private int min = 0;
    private int max = 0;
    private boolean clamp = false;

    public InspectorIntProperty(String name, IntegerProperty value)
    {
        this.value = value;
        this.name = name;
        createPane();
    }

    public InspectorIntProperty(String name, IntegerProperty value, int min, int max)
    {
        this.value = value;
        this.name = name;
        this.min = min;
        this.max = max;
        this.clamp = true;
        createPane();
    }

    private void createPane()
    {
        Button button = new Button();
        button.textProperty().bindBidirectional(value, new NumberStringConverter());

        button.setOnMouseClicked(event ->
        {
            TextWindow window = new TextWindow("Enter value", "Enter value for " + name);
            int newValue = 0;
            if(window.getResult() == null) return;
            try
            {
                newValue = Integer.parseInt(window.getResult());
            }
            catch (NumberFormatException e)
            {
                return;
            }

            if(clamp) value.setValue(Math.min(Math.max(newValue, min), max));
            else value.set(newValue);
        });

        pane = new HBox(new Label(name), button);
        pane.setAlignment(Pos.CENTER_LEFT);
    }

    public IntegerProperty valueProperty()
    {
        return this.value;
    }

    public double getValue()
    {
        return this.value.get();
    }

    public void setValue(int value)
    {
        this.value.set(value);
    }

    @Override
    public Pane getPane()
    {
        return pane;
    }
}
