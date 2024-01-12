package edu.project4.renderers;

import edu.project4.FractalImage;
import edu.project4.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConcurrencyRenderer implements Renderer {
    private static final int TIMEOUT_TERMINATION_SECONDS = 600;
    private final int threads;

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
        int samplesPerThread = (int) Math.ceil((double) samples / threads);
        try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            for (int i = 0; i < threads; i++) {
                executorService.execute(() -> new SRenderer().render(
                    canvas,
                    world,
                    variants,
                    samplesPerThread,
                    iterPerSample,
                    symmetry
                ));
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
}
