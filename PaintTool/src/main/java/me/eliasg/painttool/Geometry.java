package me.eliasg.painttool;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.eliasg.painttool.rendering.GeometryRenderer;

public class Geometry
{
    private final ListProperty<Vector> vertices;
    private Color color;

    public Geometry()
    {
        ObservableList<Vector> list = FXCollections.observableArrayList();
        this.vertices = new SimpleListProperty<>(list);
        this.color = Color.BLACK;
        vertices.add(new Vector(0, 0));
        vertices.add(new Vector(10, 0));
        vertices.add(new Vector(10, 10));
    }

    public ListProperty<Vector> verticesProperty()
    {
        return this.vertices;
    }

    public void addVertex(Vector v)
    {
        this.vertices.add(v);
    }

    public void removeVertex(int index)
    {
        this.vertices.remove(index);
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }

}
