package edu.project4;

public class FractalImage {
    private Pixel[][] data;
    private int width;
    private int height;

    private FractalImage() {
    }

    public static FractalImage create(int width, int height) {
        Pixel[][] data = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = new Pixel(0, 0, 0);
            }
        }
        FractalImage newFractalImage = new FractalImage();
        newFractalImage.height = height;
        newFractalImage.width = width;
        newFractalImage.data = data;
        return newFractalImage;
    }

    public boolean contains(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

    public Pixel pixel(int x, int y) {
        return data[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
