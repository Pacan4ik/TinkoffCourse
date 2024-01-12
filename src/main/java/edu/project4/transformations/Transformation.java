package edu.project4.transformations;

import edu.project4.Point;
import java.awt.Color;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    Color getDefaultColor();

    default Transformation and(Transformation after) {

        Transformation t = this;
        return new Transformation() {
            @Override
            public Color getDefaultColor() {
                int red = (t.getDefaultColor().getRed() + after.getDefaultColor().getRed()) / 2;
                int green = (t.getDefaultColor().getGreen() + after.getDefaultColor().getGreen()) / 2;
                int blue = (t.getDefaultColor().getBlue() + after.getDefaultColor().getBlue()) / 2;
                return new Color(red, green, blue);
            }

            @Override
            public Point apply(Point point) {
                return after.apply(t.apply(point));
            }
        };
    }
}
