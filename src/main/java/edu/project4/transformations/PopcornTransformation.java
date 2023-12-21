package edu.project4.transformations;

import edu.project4.Point;
import java.awt.Color;

public class PopcornTransformation extends AbstractTransformation {

    private final double c;
    private final double f;

    public PopcornTransformation(double c, double f, Color color) {
        super(color);
        this.c = c;
        this.f = f;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        return new Point(
            x + c * Math.sin(Math.tan(3 * y)),
            y + f * Math.sin(Math.tan(3 * x))
        );
    }
}
