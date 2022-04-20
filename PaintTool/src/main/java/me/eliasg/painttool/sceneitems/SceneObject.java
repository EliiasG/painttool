package me.eliasg.painttool.sceneitems;

import javafx.beans.property.*;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import me.eliasg.painttool.Geometries;
import me.eliasg.painttool.ProgramState;
import me.eliasg.painttool.Vector;
import me.eliasg.painttool.inspectorpropperties.*;
import me.eliasg.painttool.sceneitems.SceneItem;

public class SceneObject implements SceneItem
{
    private final StringProperty name;
    private final DoubleProperty rotation;
    private final Vector scale;
    private final Vector position;
    private final BooleanProperty drawOnTopOfParent;
    private final VBox inspector;
    private final Geometries geometry;

    public SceneObject(String name, TreeView<SceneItem> treeView, ProgramState programState)
    {
        this.name = new SimpleStringProperty(name);
        drawOnTopOfParent = new SimpleBooleanProperty(false);
        scale = new Vector(1, 1);
        position = new Vector(0, 0);
        rotation = new SimpleDoubleProperty(0);

        geometry = new Geometries();

        inspector = new VBox();

        this.name.addListener((observable, oldValue, newValue) -> treeView.refresh());

        InspectorGeometriesProperty geometries = new InspectorGeometriesProperty("Geometries", geometry, programState);
        InspectorStringProperty inspectorName = new InspectorStringProperty("Name: ", this.name);
        InspectorBooleanProperty inspectorDrawOnTop = new InspectorBooleanProperty("Draw on top of parent: ", drawOnTopOfParent);
        InspectorVectorProperty inspectorScale = new InspectorVectorProperty("Scale ", scale);
        InspectorVectorProperty inspectorPosition = new InspectorVectorProperty("Position ", position);
        InspectorDoubleProperty inspectorRotation = new InspectorDoubleProperty("Rotation: ", rotation);

        inspector.getChildren().addAll(inspectorName.getPane(), inspectorDrawOnTop.getPane(), inspectorRotation.getPane(), inspectorPosition.getPane(), inspectorScale.getPane(), geometries.getPane());

        inspector.setSpacing(5);
    }

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public String toString()
    {
        return getName();
    }

    @Override
    public VBox getInspector()
    {
        return inspector;
    }

    public boolean isDrawOnTopOfParent()
    {
        return drawOnTopOfParent.get();
    }

    public BooleanProperty drawOnTopOfParentProperty()
    {
        return drawOnTopOfParent;
    }

    public Geometries getGeometry()
    {
        return geometry;
    }

    public Vector getPosition()
    {
        return position;
    }

    public double getRotation()
    {
        return rotation.get();
    }

    public Vector getScale()
    {
        return scale;
    }


}
