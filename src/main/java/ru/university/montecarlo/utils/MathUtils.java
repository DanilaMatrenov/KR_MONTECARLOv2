package ru.university.montecarlo.utils;

public final class MathUtils {

    private MathUtils() {}

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static boolean nearlyEqual(double a, double b, double eps) {
        return Math.abs(a - b) < eps;
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double triangleArea(
            double x1, double y1,
            double x2, double y2,
            double x3, double y3
    ) {
        return Math.abs(
                x1 * (y2 - y3) +
                        x2 * (y3 - y1) +
                        x3 * (y1 - y2)
        ) / 2.0;
    }
}
