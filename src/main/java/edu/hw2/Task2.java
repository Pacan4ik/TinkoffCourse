package edu.hw2;

public final class Task2 {
    private Task2() {
    }

    public static class Rectangle {
        private int width;
        private int height;

        public Rectangle() {
        }

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        final Rectangle setWidth(int width) {
            Rectangle rect = new Rectangle();
            rect.width = width;
            rect.height = this.height;
            return rect;
        }

        final Rectangle setHeight(int height) {
            Rectangle rect = new Rectangle();
            rect.width = this.width;
            rect.height = height;
            return rect;
        }

        final double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        public Square() {

        }

        public Square(int side) {
            super.width = side;
            super.height = side;
        }

    }

}
