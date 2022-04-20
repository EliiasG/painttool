package me.eliasg.painttool.inspectorpropperties;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import me.eliasg.painttool.Vector;

public class InspectorVectorProperty implements InspectorProperty
{
    private final Vector value;
    private final String name;
    private final Pane pane;

    public InspectorVectorProperty(String name, Vector value)
    {
        this.name = name;
        this.value = value;
        InspectorDoubleProperty xProperty = new InspectorDoubleProperty("X: ", value.xProperty());
        InspectorDoubleProperty yProperty = new InspectorDoubleProperty("Y: ", value.yProperty());

        pane = new VBox(new TitledPane(name, new VBox(xProperty.getPane(), yProperty.getPane())));
    }

    public String getName()
    {
        return name;
    }

    public Vector getValue()
    {
        return value;
    }

    public String toString() {
        return value.toString();
    }

    @Override
    public Pane getPane()
    {
        return pane;
    }
}