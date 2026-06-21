package ru.university.montecarlo.geometry;

public class LineSegment {

    private final Point start;
    private final Point end;

    public LineSegment(Point start, Point end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Points must not be null");
        }
        if (start.equals(end)) {
            throw new IllegalArgumentException("Segment points must be different");
        }

        this.start = start;
        this.end = end;
    }

    public Point getStart() { return start; }
    public Point getEnd() { return end; }

    public double length() {
        return start.distanceTo(end);
    }

    public double getY(double x) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();

        if (Double.compare(x1, x2) == 0) {
            throw new IllegalStateException("Vertical segment: Y cannot be expressed as function of X");
        }

        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }

    public boolean contains(Point point, double epsilon) {
        double x = point.getX();
        double y = point.getY();

        double cross =
                (x - start.getX()) * (end.getY() - start.getY()) -
                        (y - start.getY()) * (end.getX() - start.getX());

        if (Math.abs(cross) > epsilon) {
            return false;
        }

        double dot =
                (x - start.getX()) * (end.getX() - start.getX()) +
                        (y - start.getY()) * (end.getY() - start.getY());

        if (dot < 0) {
            return false;
        }

        double squaredLength =
                (end.getX() - start.getX()) * (end.getX() - start.getX()) +
                        (end.getY() - start.getY()) * (end.getY() - start.getY());

        return dot <= squaredLength + epsilon;
    }

    @Override
    public String toString() {
        return "LineSegment{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
