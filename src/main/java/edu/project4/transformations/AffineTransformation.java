package edu.project4.transformations;

import edu.project4.Point;
import java.awt.Color;

public class AffineTransformation extends AbstractTransformation {
    private double a = 1;
    private double b = 1;
    private double c = 0;
    private double d = 1;
    private double e = 1;
    private double f = 0;


    public AffineTransformation(double a, double b, double c, double d, double e, double f, Color color) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    @Override
    public Point apply(Point point) {
        return new Point(
            a * point.x() + b * point.y() + c,
            d * point.x() + e * point.y() + f
        );
    }

}
