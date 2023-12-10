package edu.project4.renderers;

import edu.project4.FractalImage;
import edu.project4.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect word,
        List<Transformation> variants,
        int samples,
        int iterPerSample,
        int symmetry
    );

}
