package me.eliasg.painttool;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Geometries
{
    private final ListProperty<Geometry> geometries;

    public Geometries()
    {
        ObservableList<Geometry> list = FXCollections.observableArrayList();
        this.geometries = new SimpleListProperty<>(list);
    }

    public ListProperty<Geometry> geometriesProperty()
    {
        return this.geometries;
    }

    public void addGeometry(Geometry geometry)
    {
        this.geometries.add(geometry);
    }

    public void removeGeometry(int index)
    {
        this.geometries.remove(index);
    }
}
