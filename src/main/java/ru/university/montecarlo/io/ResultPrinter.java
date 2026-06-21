package ru.university.montecarlo.io;

import ru.university.montecarlo.experiment.ExperimentResult;

import java.util.List;

public class ResultPrinter {

    public void print(List<ExperimentResult> results) {
        System.out.println("N\tEstimated\tExact\tAbsError\tRelError\tAccuracy%\tTime(ms)");
        for (ExperimentResult r : results) {
            System.out.printf(
                    "%d\t%.6f\t%.6f\t%.6f\t%.6f\t%.2f\t%.2f%n",
                    r.getSampleCount(),
                    r.getEstimatedArea(),
                    r.getExactArea(),
                    r.getAbsoluteError(),
                    r.getRelativeError(),
                    r.getAccuracyPercent(),
                    r.getElapsedMilliseconds()
            );
        }
    }
}
