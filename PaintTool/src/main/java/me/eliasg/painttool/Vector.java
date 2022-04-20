package me.eliasg.painttool;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Vector
{
    private final DoubleProperty x = new SimpleDoubleProperty();
    private final DoubleProperty y = new SimpleDoubleProperty();

    public Vector(double x, double y)
    {
        this.x.setValue(x);
        this.y.setValue(y);
    }

    public Vector(final double v)
    {
        this(v, v);
    }

    public Vector()
    {
        this.x.setValue(0);
        this.y.setValue(0);
    }

    public double getX()
    {
        return x.get();
    }

    public DoubleProperty xProperty()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x.set(x);
    }

    public double getY()
    {
        return y.get();
    }

    public DoubleProperty yProperty()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y.set(y);
    }

    public static Vector add(Vector a, Vector b)
    {
        return new Vector(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Vector multiply(Vector a, double b)
    {
        return new Vector(a.getX() * b, a.getY() * b);
    }

    public static Vector subtract(Vector a, Vector b)
    {
        return new Vector(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static Vector divide(Vector a, double b)
    {
        return new Vector(a.getX() / b, a.getY() / b);
    }


}
