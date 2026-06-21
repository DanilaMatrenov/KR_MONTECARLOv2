package ru.university.montecarlo.montecarlo;

public class EstimationResult {

    private final double estimatedArea;
    private final double exactArea;
    private final double absoluteError;
    private final double relativeError;
    private final double elapsedMilliseconds;
    private final double accuracyPercent;

    public EstimationResult(double estimatedArea,
                            double exactArea,
                            double absoluteError,
                            double relativeError,
                            double elapsedMilliseconds,
                            double accuracyPercent) {
        this.estimatedArea = estimatedArea;
        this.exactArea = exactArea;
        this.absoluteError = absoluteError;
        this.relativeError = relativeError;
        this.elapsedMilliseconds = elapsedMilliseconds;
        this.accuracyPercent = accuracyPercent;
    }

    public double getEstimatedArea() { return estimatedArea; }
    public double getExactArea() { return exactArea; }
    public double getAbsoluteError() { return absoluteError; }
    public double getRelativeError() { return relativeError; }
    public double getElapsedMilliseconds() { return elapsedMilliseconds; }
    public double getAccuracyPercent() { return accuracyPercent; }
}
