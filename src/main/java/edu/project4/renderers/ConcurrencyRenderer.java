package edu.project4.renderers;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Rect;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConcurrencyRenderer implements Renderer {
    private static final int TIMEOUT_TERMINATION_SECONDS = 600;
    private int threads;
    private FractalImage canvas;
    private Rect world;
    List<Transformation> variants;
    int iterPerSample;
    int symmetry;

    public ConcurrencyRenderer(int threads) {
        this.threads = threads;
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
        this.canvas = canvas;
        this.world = world;
        this.variants = variants;
        this.iterPerSample = iterPerSample;
        this.symmetry = symmetry;

        int samplesPerThread = (int) Math.ceil((double) samples / threads);
        try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            for (int i = 0; i < threads; i++) {
                executorService.execute(new ProcessPoints(samplesPerThread));
            }
            executorService.shutdown();
            if (!executorService.awaitTermination(TIMEOUT_TERMINATION_SECONDS, TimeUnit.SECONDS)) {
                throw new TimeoutException("Timed out waiting for ProcessPoints to complete execution.");
            }
        } catch (InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        return canvas;
    }

    private class ProcessPoints extends SRenderer implements Runnable {

        private final int samples;

        ProcessPoints(int samples) {
            this.samples = samples;
            super.random = ThreadLocalRandom.current();
        }

        @Override
        public void run() {
            this.render(canvas, world, variants, samples, iterPerSample, symmetry);
        }

        @Override
        public void processPixel(Pixel pixel, Color tColor) {
            synchronized (pixel) {
                super.processPixel(pixel, tColor);
            }
        }
    }
}
