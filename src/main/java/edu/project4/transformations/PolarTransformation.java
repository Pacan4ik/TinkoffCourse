package edu.project4.transformations;

import edu.project4.Point;
import java.awt.Color;

public class PolarTransformation extends AbstractTransformation {

    public PolarTransformation(Color color) {
        super(color);
    }

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        return new Point(
            Math.atan(y / x) / Math.PI,
            Math.sqrt(x * x + y * y) - 1
        );
    }
}
