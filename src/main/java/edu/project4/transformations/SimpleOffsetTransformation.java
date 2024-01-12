package edu.project4.transformations;

import edu.project4.Point;
import java.awt.Color;

public class SimpleOffsetTransformation extends AbstractTransformation {

    private final double offsetX;
    private final double offsetY;

    public SimpleOffsetTransformation(double offsetX, double offsetY, Color color) {
        super(color);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public Point apply(Point point) {
        return new Point(point.x() + offsetX, point.y() + offsetY);
    }

}
