package ru.university.montecarlo.geometry;

public class BoundingBox {

    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;

    public BoundingBox(double minX, double maxX, double minY, double maxY) {
        if (minX >= maxX) {
            throw new IllegalArgumentException("minX must be less than maxX");
        }
        if (minY >= maxY) {
            throw new IllegalArgumentException("minY must be less than maxY");
        }

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public double getMinX() { return minX; }
    public double getMaxX() { return maxX; }
    public double getMinY() { return minY; }
    public double getMaxY() { return maxY; }

    public double getWidth() { return maxX - minX; }
    public double getHeight() { return maxY - minY; }

    public double getArea() { return getWidth() * getHeight(); }

    public boolean contains(Point point) {
        double x = point.getX();
        double y = point.getY();

        return x >= minX && x <= maxX
                && y >= minY && y <= maxY;
    }

    @Override
    public String toString() {
        return "BoundingBox{" +
                "minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                '}';
    }
}
