package ru.university.montecarlo.experiment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.university.montecarlo.montecarlo.EstimationResult;
import ru.university.montecarlo.montecarlo.MonteCarloEstimator;

import java.util.ArrayList;
import java.util.List;

public class ExperimentRunner {

    private static final Logger logger = LoggerFactory.getLogger(ExperimentRunner.class);

    private final MonteCarloEstimator estimator;

    public ExperimentRunner(MonteCarloEstimator estimator) {
        if (estimator == null) {
            throw new IllegalArgumentException("Estimator must not be null");
        }
        this.estimator = estimator;
    }

    public List<ExperimentResult> runExperiments(List<Integer> sampleSizes) {
        if (sampleSizes == null || sampleSizes.isEmpty()) {
            throw new IllegalArgumentException("Sample sizes must not be null or empty");
        }

        List<ExperimentResult> results = new ArrayList<>();

        logger.info("Starting experiment series for {} sample sizes", sampleSizes.size());

        for (Integer sampleSize : sampleSizes) {
            if (sampleSize == null || sampleSize <= 0) {
                logger.warn("Skipping invalid sample size: {}", sampleSize);
                continue;
            }

            logger.info("Running experiment for N={}", sampleSize);

            EstimationResult estimationResult = estimator.estimate(sampleSize);

            ExperimentResult result = new ExperimentResult(
                    sampleSize.longValue(),
                    estimationResult.getEstimatedArea(),
                    estimationResult.getExactArea(),
                    estimationResult.getAbsoluteError(),
                    estimationResult.getRelativeError(),
                    estimationResult.getElapsedMilliseconds(),
                    estimationResult.getAccuracyPercent()
            );

            results.add(result);

            logger.info(
                    "Experiment completed: N={}, area={}, relError={}, timeMs={}",
                    result.getSampleCount(),
                    result.getEstimatedArea(),
                    result.getRelativeError(),
                    result.getElapsedMilliseconds()
            );
        }

        logger.info("Experiment series completed. Total results: {}", results.size());

        return results;
    }
}
