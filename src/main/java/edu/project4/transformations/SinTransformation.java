package edu.project4.transformations;

import edu.project4.Point;
import java.awt.Color;

public class SinTransformation extends AbstractTransformation {

    public SinTransformation(Color color) {
        super(color);
    }

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
