package me.eliasg.painttool;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectSettings
{
    private static final IntegerProperty size = new SimpleIntegerProperty(32);

    private static final ListProperty<StringProperty> layers = new SimpleListProperty<>(FXCollections.observableArrayList());

    public static IntegerProperty sizeProperty()
    {
        return size;
    }

    public static int getSize()
    {
        return size.get();
    }

    public static void setSize(int size)
    {
        ProjectSettings.size.set(size);
    }

    public static ObservableList<StringProperty> getLayers()
    {
        return layers.get();
    }

    public static ListProperty<StringProperty> layersProperty()
    {
        return layers;
    }
}
