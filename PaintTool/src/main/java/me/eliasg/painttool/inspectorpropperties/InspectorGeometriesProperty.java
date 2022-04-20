package me.eliasg.painttool.inspectorpropperties;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import me.eliasg.painttool.Geometries;
import me.eliasg.painttool.Geometry;
import me.eliasg.painttool.ProgramState;
import me.eliasg.painttool.Vector;

public class InspectorGeometriesProperty implements InspectorProperty
{
    private final Geometries geometries;
    private final Button addButton;
    private final VBox vbox;
    private final Pane pane;
    private final ProgramState programState;

    public InspectorGeometriesProperty(String name, Geometries geometries, ProgramState programState)
    {
        this.geometries = geometries;
        this.programState = programState;
        addButton = new Button("Add geometry");

        geometries.geometriesProperty().addListener((observable, oldValue, newValue) -> refresh());

        addButton.setOnAction(event -> geometries.addGeometry(new Geometry()));

        vbox = new VBox();

        pane = new Pane(new TitledPane(name, vbox));
        refresh();
    }

    private void refresh()
    {
        vbox.getChildren().clear();

        for(int i = 0; i < geometries.geometriesProperty().size(); i++)
        {
            InspectorGeometryProperty inspectorGeometryProperty = new InspectorGeometryProperty(i,"Geometry " + i, geometries, programState);
            vbox.getChildren().add(inspectorGeometryProperty.getPane());
        }

        vbox.getChildren().add(addButton);
    }

    @Override
    public Pane getPane()
    {
        return pane;
    }
}
