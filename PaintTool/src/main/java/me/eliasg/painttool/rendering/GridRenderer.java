package me.eliasg.painttool.rendering;

import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.eliasg.painttool.ProjectSettings;
import me.eliasg.painttool.Vector;

public class GridRenderer
{
    private final ScaleConverter scaleConverter;

    public GridRenderer(ScaleConverter scaleConverter)
    {
        this.scaleConverter = scaleConverter;
    }

    public void render(GraphicsContext gc)
    {
        Color[] colors = new Color[] { Color.LIGHTGRAY, Color.WHITE };

        for (int x = 0; x < ProjectSettings.getSize(); x++)
        {
            for (int y = 0; y < ProjectSettings.getSize(); y++)
            {
                gc.setFill(colors[(x+y) % 2]);
                Vector startPosition = scaleConverter.getScreenPosition(new Vector(x, y));
                Vector endPosition = scaleConverter.getScreenPosition(new Vector(x+1, y+1));
                gc.fillRect(startPosition.getX(), startPosition.getY(), endPosition.getX() - startPosition.getX(), endPosition.getY() - startPosition.getY());
            }
        }
    }
}
