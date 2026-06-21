package ru.university.montecarlo.geometry;

public class CircularArc {

    private final Point center;
    private final double radius;
    private final double startAngle;
    private final double endAngle;

    public CircularArc(Point center, double radius, double startAngle, double endAngle) {
        if (center == null) {
            throw new IllegalArgumentException("Center must not be null");
        }
        if (radius <= 0.0) {
            throw new IllegalArgumentException("Radius must be greater than zero");
        }

        this.center = center;
        this.radius = radius;
        this.startAngle = normalizeAngle(startAngle);
        this.endAngle = normalizeAngle(endAngle);
    }

    public Point getCenter() { return center; }
    public double getRadius() { return radius; }
    public double getStartAngle() { return startAngle; }
    public double getEndAngle() { return endAngle; }

    public boolean isInsideCircle(Point point) {
        double dx = point.getX() - center.getX();
        double dy = point.getY() - center.getY();
        return dx * dx + dy * dy <= radius * radius;
    }

    public boolean isInsideSector(Point point) {
        if (!isInsideCircle(point)) {
            return false;
        }

        double dx = point.getX() - center.getX();
        double dy = point.getY() - center.getY();
        double angle = normalizeAngle(Math.atan2(dy, dx));

        if (startAngle <= endAngle) {
            return angle >= startAngle && angle <= endAngle;
        }
        return angle >= startAngle || angle <= endAngle;
    }

    private double normalizeAngle(double angle) {
        double twoPi = 2.0 * Math.PI;
        double normalized = angle % twoPi;
        if (normalized < 0) {
            normalized += twoPi;
        }
        return normalized;
    }

    @Override
    public String toString() {
        return "CircularArc{" +
                "center=" + center +
                ", radius=" + radius +
                ", startAngle=" + startAngle +
                ", endAngle=" + endAngle +
                '}';
    }
}
