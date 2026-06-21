package ru.university.montecarlo.montecarlo;

import org.junit.jupiter.api.Test;
import ru.university.montecarlo.config.AppConfig;
import ru.university.montecarlo.geometry.DeboShape;
import ru.university.montecarlo.geometry.Shape;

import static org.junit.jupiter.api.Assertions.*;

class MonteCarloEstimatorTest {

    private Shape createShape() {
        AppConfig.GeometryConfig cfg = new AppConfig.GeometryConfig();

        cfg.setDx(0.0);  cfg.setDy(2.0);
        cfg.setEx(2.0);  cfg.setEy(2.0);
        cfg.setBx(2.0);  cfg.setBy(0.0);
        cfg.setOx(0.0);  cfg.setOy(0.0);

        cfg.setCircleCenterX(0.0);
        cfg.setCircleCenterY(0.0);
        cfg.setRadius(2.0);

        return new DeboShape(cfg);
    }

    @Test
    void shouldEstimateAreaWithAcceptableAccuracy() {
        Shape shape = createShape();
        RandomPointGenerator generator = new RandomPointGenerator(42L);
        MonteCarloEstimator estimator = new MonteCarloEstimator(shape, generator);

        EstimationResult result = estimator.estimate(500_000);

        double relativeError = result.getRelativeError();

        assertTrue(relativeError < 0.03, "Relative error should be < 3%");
    }

    @Test
    void shouldReturnPositiveElapsedTime() {
        Shape shape = createShape();
        RandomPointGenerator generator = new RandomPointGenerator(42L);
        MonteCarloEstimator estimator = new MonteCarloEstimator(shape, generator);

        EstimationResult result = estimator.estimate(20_000);

        assertTrue(result.getElapsedMilliseconds() > 0.0);
    }

    @Test
    void shouldThrowExceptionForInvalidSampleCount() {
        Shape shape = createShape();
        RandomPointGenerator generator = new RandomPointGenerator(42L);
        MonteCarloEstimator estimator = new MonteCarloEstimator(shape, generator);

        assertThrows(IllegalArgumentException.class, () -> estimator.estimate(0));
        assertThrows(IllegalArgumentException.class, () -> estimator.estimate(-10));
    }

    @Test
    void shouldProduceReasonableAreaEstimate() {
        Shape shape = createShape();
        RandomPointGenerator generator = new RandomPointGenerator(42L);
        MonteCarloEstimator estimator = new MonteCarloEstimator(shape, generator);

        long samples = 200_000;
        EstimationResult result = estimator.estimate(samples);

        double estimated = result.getEstimatedArea();
        double exact = shape.getExactArea();

        // Проверяем, что оценка не уехала слишком далеко
        assertTrue(Math.abs(estimated - exact) < exact * 0.05,
                "Monte Carlo estimate should be within 5% of exact area");
    }
}
