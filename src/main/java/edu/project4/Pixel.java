package edu.project4;

public class Pixel {
    private static final int MAX_COLOR_VALUE = 255;
    private volatile int counter;
    private volatile int red;
    private volatile int green;
    private volatile int blue;

    public Pixel(int red, int green, int blue) {
        this.counter = 0;
        this.red = red % (MAX_COLOR_VALUE + 1);
        this.green = green % (MAX_COLOR_VALUE + 1);
        this.blue = blue % (MAX_COLOR_VALUE + 1);
    }

    public int getCounter() {
        return counter;
    }

    public synchronized void incrementCounter() {
        counter++;
    }

    public int getRed() {
        return red;
    }

    public synchronized void setRed(int red) {
        this.red = red % (MAX_COLOR_VALUE + 1);
    }

    public int getGreen() {
        return green;
    }

    public synchronized void setGreen(int green) {
        this.green = green % (MAX_COLOR_VALUE + 1);
    }

    public int getBlue() {
        return blue;
    }

    public synchronized void setBlue(int blue) {
        this.blue = blue % (MAX_COLOR_VALUE + 1);
    }

    public synchronized void mixColor(int red, int green, int blue) {
        this.setRed((this.getRed() + red) / 2);
        this.setGreen((this.getGreen() + green) / 2);
        this.setBlue((this.getBlue() + blue) / 2);
    }
}

