package edu.project4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
    private ImageUtils() {
    }

    public enum ImageFormat {
        JPEG,
        BMP,
        PNG
    }

    public static void saveToFile(FractalImage fractalImage, String fileName, ImageFormat format) {
        int xRes = fractalImage.getWidth();
        int yRes = fractalImage.getHeight();
        BufferedImage image = new BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                Pixel pixel = fractalImage.pixel(i, j);
                if (pixel == null) {
                    continue;
                }
                int rgb =
                    new java.awt.Color(
                        pixel.getRed(),
                        pixel.getGreen(),
                        pixel.getBlue()
                    ).getRGB();
                image.setRGB(i, j, rgb);
            }
        }
        saveImage(image, fileName, format);
    }

    private static void saveImage(BufferedImage image, String filename, ImageFormat format) {
        try {
            ImageIO.write(
                image,
                format.name().toLowerCase(),
                new File(String.format("%s.%s", filename, format.name().toLowerCase()))
            );
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
