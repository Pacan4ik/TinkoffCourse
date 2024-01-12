package edu.project4.transformations;

import java.awt.Color;
import java.util.Objects;

public abstract class AbstractTransformation implements Transformation {
    private final Color color;

    public AbstractTransformation(Color color) {
        Objects.requireNonNull(color);
        this.color = color;
    }

    @Override
    public Color getDefaultColor() {
        return color;
    }

}
