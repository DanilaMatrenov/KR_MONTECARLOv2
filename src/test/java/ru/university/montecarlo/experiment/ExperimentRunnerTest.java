package ru.university.montecarlo.experiment;

import org.junit.jupiter.api.Test;
import ru.university.montecarlo.config.AppConfig;
import ru.university.montecarlo.geometry.DeboShape;
import ru.university.montecarlo.geometry.Shape;
import ru.university.montecarlo.montecarlo.MonteCarloEstimator;
import ru.university.montecarlo.montecarlo.RandomPointGenerator;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExperimentRunnerTest {

    private Shape createShape() {
        AppConfig.GeometryConfig cfg = new AppConfig.GeometryConfig();

        // Значения по умолчанию из твоего application.yml
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
    void shouldRunAllExperiments() {
        Shape shape = createShape();
        RandomPointGenerator generator = new RandomPointGenerator(42L);
        MonteCarloEstimator estimator = new MonteCarloEstimator(shape, generator);

        ExperimentRunner runner = new ExperimentRunner(estimator);

        List<Integer> sampleSizes = Arrays.asList(1000, 5000, 10000);

        List<ExperimentResult> results = runner.runExperiments(sampleSizes);

        assertEquals(3, results.size());
    }

    @Test
    void shouldPreserveSampleSizesOrder() {
        Shape shape = createShape();
        RandomPointGenerator generator = new RandomPointGenerator(42L);
        MonteCarloEstimator estimator = new MonteCarloEstimator(shape, generator);

        ExperimentRunner runner = new ExperimentRunner(estimator);

        List<Integer> sampleSizes = Arrays.asList(1000, 5000, 10000);

        List<ExperimentResult> results = runner.runExperiments(sampleSizes);

        assertEquals(1000, results.get(0).getSampleCount());
        assertEquals(5000, results.get(1).getSampleCount());
        assertEquals(10000, results.get(2).getSampleCount());
    }

    @Test
    void shouldThrowExceptionForEmptyList() {
        Shape shape = createShape();
        RandomPointGenerator generator = new RandomPointGenerator(42L);
        MonteCarloEstimator estimator = new MonteCarloEstimator(shape, generator);

        ExperimentRunner runner = new ExperimentRunner(estimator);

        assertThrows(IllegalArgumentException.class,
                () -> runner.runExperiments(List.of()));
    }

    @Test
    void shouldReturnPositiveExecutionTimeForEachExperiment() {
        Shape shape = createShape();
        RandomPointGenerator generator = new RandomPointGenerator(42L);
        MonteCarloEstimator estimator = new MonteCarloEstimator(shape, generator);

        ExperimentRunner runner = new ExperimentRunner(estimator);

        List<ExperimentResult> results =
                runner.runExperiments(Arrays.asList(1000, 5000));

        for (ExperimentResult result : results) {
            assertTrue(result.getElapsedMilliseconds() > 0);
        }
    }

    @Test
    void shouldSkipInvalidSampleSizes() {
        Shape shape = createShape();
        RandomPointGenerator generator = new RandomPointGenerator(42L);
        MonteCarloEstimator estimator = new MonteCarloEstimator(shape, generator);

        ExperimentRunner runner = new ExperimentRunner(estimator);

        List<Integer> sampleSizes = Arrays.asList(1000, -5, null, 5000);

        List<ExperimentResult> results = runner.runExperiments(sampleSizes);

        assertEquals(2, results.size());
        assertEquals(1000, results.get(0).getSampleCount());
        assertEquals(5000, results.get(1).getSampleCount());
    }
}
