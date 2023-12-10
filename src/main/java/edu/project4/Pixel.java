package edu.project4;

public class Pixel {
    private static final int MAX_COLOR_VALUE = 255;
    private int counter;
    private int red;
    private int green;
    private int blue;

    public Pixel(int red, int green, int blue) {
        this.counter = 0;
        this.red = red % (MAX_COLOR_VALUE + 1);
        this.green = green % (MAX_COLOR_VALUE + 1);
        this.blue = blue % (MAX_COLOR_VALUE + 1);
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red % (MAX_COLOR_VALUE + 1);
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green % (MAX_COLOR_VALUE + 1);
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue % (MAX_COLOR_VALUE + 1);
    }
}

