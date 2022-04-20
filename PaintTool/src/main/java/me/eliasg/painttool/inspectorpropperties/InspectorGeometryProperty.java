package me.eliasg.painttool.inspectorpropperties;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import me.eliasg.painttool.Geometries;
import me.eliasg.painttool.Geometry;
import me.eliasg.painttool.ProgramState;
import me.eliasg.painttool.Vector;
import me.eliasg.painttool.popups.ConfirmWindow;

public class InspectorGeometryProperty implements InspectorProperty
{
    private final Pane pane;

    private final ColorPicker colorPicker;

    private final Geometry geometry;

    public InspectorGeometryProperty(int index, String name, Geometries geometries, ProgramState programState)
    {
        this.geometry = geometries.geometriesProperty().get(index);

        colorPicker = new ColorPicker(geometry.getColor());

        colorPicker.setOnAction(event -> geometry.setColor(colorPicker.getValue()));

        Button deleteButton = new Button("Delete");

        Button editButton = new Button("Edit");

        deleteButton.setOnAction(event ->
        {
            ConfirmWindow confirmWindow = new ConfirmWindow("Delete geometry", "Are you sure you want to delete \"" + name + "\"?");
            if(confirmWindow.isConfirmed()) geometries.geometriesProperty().remove(index);
        });

        editButton.setOnAction(event ->
        {
            if(programState.getEditingGeometry() != geometry) programState.setEditingGeometry(geometry);
            else programState.setEditingGeometry(null);
        });

        HBox buttons = new HBox(deleteButton, editButton);
        pane = new Pane(new TitledPane(name, new VBox(colorPicker, buttons)));
    }

    @Override
    public Pane getPane()
    {
        return pane;
    }
}
