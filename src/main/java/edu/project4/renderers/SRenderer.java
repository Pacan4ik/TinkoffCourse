package edu.project4.renderers;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.Rect;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.Random;

public class SRenderer implements Renderer {
    protected Random random;

    protected SRenderer() {
        this.random = new Random();
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variants,
        int samples,
        int iterPerSample,
        int symmetry
    ) {

        for (int num = 0; num < samples; num++) {
            Point pw =
                new Point(random.nextDouble(world.x(), world.width()), random.nextDouble(world.y(), world.height()));

            for (int step = 0; step < iterPerSample; step++) {
                Transformation transformation = variants.get(random.nextInt(variants.size()));
                pw = transformation.apply(pw);
                Color tColor = transformation.getDefaultColor();

                double theta = 0.0;
                double delta = Math.PI * 2 / symmetry;
                for (int s = 0; s < symmetry; theta += delta, s++) {
                    Point pwr = rotate(pw, theta);
                    Pixel pixel = mapRange(pwr, canvas);
                    if (pixel == null) {
                        continue;
                    }
                    processPixel(pixel, tColor);

                }
            }
        }
        return canvas;

    }

    protected void processPixel(Pixel pixel, Color tColor) {
        pixel.setRed((pixel.getRed() + tColor.getRed()) / 2);
        pixel.setGreen((pixel.getGreen() + tColor.getGreen()) / 2);
        pixel.setBlue((pixel.getBlue() + tColor.getBlue()) / 2);
        pixel.incrementCounter();
    }

    protected static Point rotate(Point point, double rad) {
        double oldX = point.x();
        double oldY = point.y();
        double rotatedX = oldX * Math.cos(rad) - oldY * Math.sin(rad);
        double rotatedY = oldX * Math.sin(rad) + oldY * Math.cos(rad);
        return new Point(rotatedX, rotatedY);
    }

    protected static Pixel mapRange(Point point, FractalImage canvas) {
        int resX = canvas.getWidth();
        int resY = canvas.getHeight();
        int centerX = resX / 2;
        int centerY = resY / 2;
        int x = (int) (point.x() * resX / 2) + centerX;
        int y = (int) (point.y() * resY / 2) + centerY;
        if (!canvas.contains(x, y)) {
            return null;
        }
        return canvas.pixel(x, y);

    }

}
