package ru.university.montecarlo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.university.montecarlo.config.AppConfig;
import ru.university.montecarlo.config.JsonConfigLoader;
import ru.university.montecarlo.experiment.ExperimentResult;
import ru.university.montecarlo.experiment.ExperimentRunner;
import ru.university.montecarlo.geometry.DeboShape;
import ru.university.montecarlo.geometry.Shape;
import ru.university.montecarlo.io.CsvExporter;
import ru.university.montecarlo.io.ResultPrinter;
import ru.university.montecarlo.montecarlo.MonteCarloEstimator;
import ru.university.montecarlo.montecarlo.RandomPointGenerator;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            logger.info("Application started");

            JsonConfigLoader configLoader = new JsonConfigLoader();
            AppConfig config = configLoader.loadDefault();

            logger.info("Configuration loaded: {}", config);

            Shape shape = new DeboShape(config.getGeometry());

            RandomPointGenerator pointGenerator =
                    new RandomPointGenerator(config.getRandomSeed());

            MonteCarloEstimator estimator =
                    new MonteCarloEstimator(shape, pointGenerator);

            ExperimentRunner runner =
                    new ExperimentRunner(estimator);

            List<ExperimentResult> results =
                    runner.runExperiments(config.getExperimentSizes());

            ResultPrinter printer = new ResultPrinter();
            printer.print(results);

            if (config.getOutput() != null
                    && config.getOutput().isExportCsv()) {

                CsvExporter exporter = new CsvExporter();
                exporter.export(
                        results,
                        config.getOutput().getCsvPath()
                );

                logger.info(
                        "Results exported to CSV: {}",
                        config.getOutput().getCsvPath()
                );
            }

            logger.info("Application finished successfully");

        } catch (IOException e) {
            logger.error("Failed to load configuration file", e);
            System.err.println("Configuration error: " + e.getMessage());

        } catch (Exception e) {
            logger.error("Unexpected application error", e);
            System.err.println("Application error: " + e.getMessage());
        }
    }
}
