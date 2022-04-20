package me.eliasg.painttool.rendering;

import javafx.beans.property.ListProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.eliasg.painttool.Geometry;
import me.eliasg.painttool.ProgramState;
import me.eliasg.painttool.Vector;

public class GeometryRenderer
{
    private ProgramState programState;

    public GeometryRenderer(ProgramState programState)
    {
        this.programState = programState;
    }

    public void render(GraphicsContext gc, Geometry geometry)
    {
        ListProperty<Vector> vertices = geometry.verticesProperty();
        double[] xPoints = new double[vertices.size()];
        double[] yPoints = new double[vertices.size()];

        for (int i = 0; i < vertices.size(); i++)
        {
            Vector v = vertices.get(i);
            xPoints[i] = v.getX();
            yPoints[i] = v.getY();
        }

        gc.setFill(geometry.getColor());
        gc.fillPolygon(xPoints, yPoints, vertices.size());

        gc.setFill(Color.BLACK);
        gc.setLineWidth(0.5);
        if(programState.getEditingGeometry() == geometry)
        {
            for(Vector v : vertices)
            {
                double size = 3;
                gc.strokeOval(v.getX() - size / 2, v.getY() - size / 2, size, size);
            }
        }
    }
}
