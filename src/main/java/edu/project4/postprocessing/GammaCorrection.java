package edu.project4.postprocessing;

import edu.project4.FractalImage;
import edu.project4.Pixel;

public class GammaCorrection implements ImageProcessor {

    private final double gamma;
    private final double max;

    public GammaCorrection(double gamma, double max) {
        this.gamma = gamma;
        this.max = max;
    }

    @Override
    public void process(FractalImage image) {
        double localMax = max;
        double[][] pixelNormals = new double[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int counter = image.pixel(i, j).getCounter();
                if (counter != 0) {
                    pixelNormals[i][j] = Math.log10(counter);
                    if (pixelNormals[i][j] > localMax) {
                        localMax = pixelNormals[i][j];
                    }
                }
            }
        }

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                double normal = pixelNormals[i][j] / localMax;
                Pixel pixel = image.pixel(i, j);
                double r = pixel.getRed();
                double g = pixel.getGreen();
                double b = pixel.getBlue();
                pixel.setRed((int) (r * Math.pow(normal, 1.0 / gamma)));
                pixel.setGreen((int) (g * Math.pow(normal, 1.0 / gamma)));
                pixel.setBlue((int) (b * Math.pow(normal, 1.0 / gamma)));
            }
        }
    }
}
