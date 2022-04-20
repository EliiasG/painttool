package me.eliasg.painttool.rendering;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import me.eliasg.painttool.ProjectSettings;
import me.eliasg.painttool.Vector;

public class ScaleConverter
{
    private float zoom;
    private Vector offset = new Vector(0, 0);
    private DoubleProperty widthProperty;
    private DoubleProperty heightProperty;


    public ScaleConverter(DoubleProperty widthProperty, DoubleProperty heightProperty)
    {
        this.widthProperty = widthProperty;
        this.heightProperty = heightProperty;
        zoom = 1;
    }

    public Vector getScreenPosition(Vector v)
    {
        Vector translated = Vector.add(v, offset);
        Vector scaled = Vector.multiply(translated, zoom);
        return Vector.add(scaled, new Vector(widthProperty.get() / 2, heightProperty.get() / 2));
    }

    public float getZoom()
    {
        return zoom;
    }

    public void setZoom(float zoom)
    {
        this.zoom = Math.min(Math.max(zoom, 1), 300);
    }

    public Vector getOffset()
    {
        return offset;
    }

    public void setOffset(Vector offset)
    {
        this.offset = offset;
    }
}
