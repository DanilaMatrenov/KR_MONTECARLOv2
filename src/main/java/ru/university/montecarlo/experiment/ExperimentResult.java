package ru.university.montecarlo.experiment;

public class ExperimentResult {

    private final long sampleCount;
    private final double estimatedArea;
    private final double exactArea;
    private final double absoluteError;
    private final double relativeError;
    private final double elapsedMilliseconds;
    private final double accuracyPercent;

    public ExperimentResult(long sampleCount,
                            double estimatedArea,
                            double exactArea,
                            double absoluteError,
                            double relativeError,
                            double elapsedMilliseconds,
                            double accuracyPercent) {
        this.sampleCount = sampleCount;
        this.estimatedArea = estimatedArea;
        this.exactArea = exactArea;
        this.absoluteError = absoluteError;
        this.relativeError = relativeError;
        this.elapsedMilliseconds = elapsedMilliseconds;
        this.accuracyPercent = accuracyPercent;
    }

    public long getSampleCount() { return sampleCount; }
    public double getEstimatedArea() { return estimatedArea; }
    public double getExactArea() { return exactArea; }
    public double getAbsoluteError() { return absoluteError; }
    public double getRelativeError() { return relativeError; }
    public double getElapsedMilliseconds() { return elapsedMilliseconds; }
    public double getAccuracyPercent() { return accuracyPercent; }

    @Override
    public String toString() {
        return String.format(
                "N=%d | estimated=%.6f | exact=%.6f | absError=%.6f | relError=%.6f | accuracy=%.2f%% | time=%.2f ms",
                sampleCount,
                estimatedArea,
                exactArea,
                absoluteError,
                relativeError,
                accuracyPercent,
                elapsedMilliseconds
        );
    }
}
