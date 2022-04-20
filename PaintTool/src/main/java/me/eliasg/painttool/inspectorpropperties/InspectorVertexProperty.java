package me.eliasg.painttool.inspectorpropperties;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import me.eliasg.painttool.Geometry;
import me.eliasg.painttool.Vector;

public class InspectorVertexProperty implements InspectorProperty
{
    private final VBox pane;

    public InspectorVertexProperty(int index, Geometry geometry)
    {
        pane = new VBox();

        InspectorVectorProperty position = new InspectorVectorProperty("Position", geometry.verticesProperty().get(index));

        Button deleteButton = new Button("Delete");

        Button addButton = new Button("Add");

        HBox buttonBox = new HBox(deleteButton, addButton);

        deleteButton.setOnAction(e -> geometry.removeVertex(index));

        addButton.setOnAction(e -> geometry.verticesProperty().add(index, new Vector(0, 0)));

        pane.getChildren().addAll(position.getPane(), buttonBox);
    }

    @Override
    public Pane getPane()
    {
        return pane;
    }
}
