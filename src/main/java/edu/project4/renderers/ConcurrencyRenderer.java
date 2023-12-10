package edu.project4.renderers;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.Rect;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class ConcurrencyRenderer extends SRenderer {
    private int threads;

    public ConcurrencyRenderer(int threads) {
        this.threads = threads;
    }

    private FractalImage canvas;
    private Rect world;
    List<Transformation> variants;
    int iterPerSample;
    int symmetry;

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variants,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        this.canvas = canvas;
        this.world = world;
        this.variants = variants;
        this.iterPerSample = iterPerSample;
        this.symmetry = symmetry;

        int samplesPerThread = (int) Math.ceil((double) samples / threads);
        var tasks = new ArrayList<>(Stream.generate(() -> (Callable<Void>) () -> {
                new ProcessPoints(samplesPerThread).run();
                return null;
            })
            .limit(threads).toList());
        try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return canvas;
    }

    private class ProcessPoints implements Runnable {

        private final int samples;

        private final Random random = ThreadLocalRandom.current();

        ProcessPoints(int samples) {
            this.samples = samples;
        }

        @Override
        public void run() {
            for (int num = 0; num < samples; num++) {
                Point pw =
                    new Point(
                        random.nextDouble(world.x(), world.width()),
                        random.nextDouble(world.y(), world.height())
                    );

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
                        synchronized (pixel) {
                            pixel.setRed((pixel.getRed() + tColor.getRed()) / 2);
                            pixel.setGreen((pixel.getGreen() + tColor.getGreen()) / 2);
                            pixel.setBlue((pixel.getBlue() + tColor.getBlue()) / 2);
                            pixel.incrementCounter();
                        }
                    }

                }
            }
        }
    }
}
