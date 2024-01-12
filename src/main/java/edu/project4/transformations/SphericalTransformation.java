package edu.project4.transformations;

import edu.project4.Point;
import java.awt.Color;

public class SphericalTransformation extends AbstractTransformation {

    public SphericalTransformation(Color color) {
        super(color);
    }

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double d = x * x + y * y;
        return new Point(x / d, y / d);
    }
}
