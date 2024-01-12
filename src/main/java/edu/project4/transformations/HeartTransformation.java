package edu.project4.transformations;

import edu.project4.Point;
import java.awt.Color;

public class HeartTransformation extends AbstractTransformation {

    public HeartTransformation(Color color) {
        super(color);
    }

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double sqrt = Math.sqrt(x * x + y * y);
        double atan = Math.atan(y / x);
        return new Point(
            sqrt * Math.sin(sqrt * atan),
            -1 * sqrt * Math.cos(sqrt * atan)
        );

    }
}
