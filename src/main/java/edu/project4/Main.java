package edu.project4;

import edu.project4.postprocessing.GammaCorrection;
import edu.project4.renderers.ConcurrencyRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.PolarTransformation;
import edu.project4.transformations.PopcornTransformation;
import edu.project4.transformations.SinTransformation;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Main {
    private static final int X_RES = 1920;
    private static final int Y_RES = 1080;

    private static final int THREADS = 16;
    private static final Rect WORLD = new Rect(-100, -50, 200, 100);

    private static final List<Transformation> TRANSFORMATIONS = List.of(
        new AffineTransformation(0.24, -0.85, -0.04, -0.73, -0.18, -1.21, new Color(0, 124, 134))
            .and(new PopcornTransformation(0.5, 1, new Color(30, 30, 30)))
            .and(new PolarTransformation(new Color(100, 100, 100)))
            .and(new AffineTransformation(2, 0, 0, 0, 1, 0, new Color(100, 100, 200))),
        new AffineTransformation(-0.95, 0.26, 2.67, -0.32, 0.88, 0.15, Color.BLUE)
            .and(new SinTransformation(Color.GRAY))
    );

    private static final int SAMPLES = 1500000;
    private static final int ITERATIONS_PER_SAMPLE = 150;
    private static final int SYMMETRY = 1;

    private static final double GAMMA = 0.75;
    private static final double MAX_GAMMA = 0.0;

    private Main() {
    }

    public static void main(String[] args) {

        Renderer renderer = new ConcurrencyRenderer(THREADS);
        //Renderer renderer = new SRenderer();
        FractalImage canvas = FractalImage.create(X_RES, Y_RES);

        renderer.render(
            canvas,
            WORLD,
            TRANSFORMATIONS,
            SAMPLES * TRANSFORMATIONS.size(),
            ITERATIONS_PER_SAMPLE,
            SYMMETRY
        );

        GammaCorrection gammaCorrection = new GammaCorrection(GAMMA, MAX_GAMMA);
        gammaCorrection.process(canvas);
        ImageUtils.saveToFile(canvas, "fractal", ImageUtils.ImageFormat.PNG);
    }
}
