package ru.university.montecarlo.montecarlo;

import ru.university.montecarlo.geometry.BoundingBox;
import ru.university.montecarlo.geometry.Point;
import ru.university.montecarlo.geometry.Shape;

public class MonteCarloEstimator {

    private final Shape shape;
    private final RandomPointGenerator generator;

    public MonteCarloEstimator(Shape shape, RandomPointGenerator generator) {
        if (shape == null || generator == null) {
            throw new IllegalArgumentException("Shape and generator must not be null");
        }
        this.shape = shape;
        this.generator = generator;
    }

    public EstimationResult estimate(long sampleCount) {
        if (sampleCount <= 0) {
            throw new IllegalArgumentException("Sample count must be greater than zero");
        }

        BoundingBox box = shape.getBoundingBox();
        double rectArea = box.getArea();

        long insideCount = 0;

        long start = System.nanoTime();

        for (long i = 0; i < sampleCount; i++) {
            Point p = generator.nextPoint(box);
            if (shape.contains(p)) {
                insideCount++;
            }
        }

        long end = System.nanoTime();

        double estimatedArea = rectArea * ((double) insideCount / (double) sampleCount);
        double exactArea = shape.getExactArea();
        double absoluteError = Math.abs(exactArea - estimatedArea);
        double relativeError = absoluteError / exactArea;
        double accuracyPercent = (1.0 - relativeError) * 100.0;
        double elapsedMs = (end - start) / 1_000_000.0;

        return new EstimationResult(
                estimatedArea,
                exactArea,
                absoluteError,
                relativeError,
                elapsedMs,
                accuracyPercent
        );
    }
}
