package edu.project4.transformations;

import edu.project4.Point;
import java.awt.Color;

public class DiskTransformation extends AbstractTransformation {

    public DiskTransformation(Color color) {
        super(color);
    }

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double piSqrt = Math.PI * Math.sqrt(x * x + y * y);
        double atan = Math.atan(y / x);
        double d = 1 / Math.PI * atan;
        return new Point(
            d * Math.sin(piSqrt),
            d * Math.cos(piSqrt)
        );
    }
}
