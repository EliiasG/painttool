package me.eliasg.painttool.inspectorpropperties;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
import me.eliasg.painttool.popups.TextWindow;

public class InspectorDoubleProperty implements InspectorProperty
{
    private final DoubleProperty value;
    private final String name;
    private HBox pane;
    private double min = 0;
    private double max = 0;
    boolean clamp = false;

    public InspectorDoubleProperty(String name, DoubleProperty value)
    {
        this.value = value;
        this.name = name;
        createPane();
    }

    public InspectorDoubleProperty(String name, DoubleProperty value, double min, double max)
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
        Button button = new Button("");
        button.textProperty().bindBidirectional(value, new NumberStringConverter());

        button.setOnMouseClicked(event ->
        {
            TextWindow window = new TextWindow("Enter value", "Enter value for " + name);
            double newValue = 0;
            if(window.getResult() == null) return;
            try
            {
                newValue = Double.parseDouble(window.getResult());
            }
            catch (NumberFormatException e)
            {
                return;
            }

            if(clamp) value.set(Math.max(Math.min(newValue, max), min));
            else value.set(newValue);
        });

        pane = new HBox(new Label(name), button);
        pane.setAlignment(Pos.CENTER_LEFT);
    }

    public DoubleProperty valueProperty()
    {
        return this.value;
    }

    public double getValue() {
        return this.value.get();
    }

    public void setValue(double value)
    {
        this.value.set(value);
    }

    @Override
    public Pane getPane()
    {
        return pane;
    }
}
