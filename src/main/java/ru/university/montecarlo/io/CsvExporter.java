package ru.university.montecarlo.io;

import ru.university.montecarlo.experiment.ExperimentResult;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter {

    public void export(List<ExperimentResult> results, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("N,Estimated,Exact,AbsError,RelError,AccuracyPercent,TimeMs\n");
            for (ExperimentResult r : results) {
                writer.write(String.format(
                        "%d,%.6f,%.6f,%.6f,%.6f,%.2f,%.2f%n",
                        r.getSampleCount(),
                        r.getEstimatedArea(),
                        r.getExactArea(),
                        r.getAbsoluteError(),
                        r.getRelativeError(),
                        r.getAccuracyPercent(),
                        r.getElapsedMilliseconds()
                ));
            }
        }
    }
}
