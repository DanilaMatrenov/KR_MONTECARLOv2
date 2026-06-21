package ru.university.montecarlo.config;

import java.util.List;

public class AppConfig {

    private String figureType;
    private long randomSeed;
    private List<Integer> experimentSizes;
    private GeometryConfig geometry;
    private LoggingConfig logging;
    private OutputConfig output;

    public String getFigureType() {
        return figureType;
    }

    public void setFigureType(String figureType) {
        this.figureType = figureType;
    }

    public long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(long randomSeed) {
        this.randomSeed = randomSeed;
    }

    public List<Integer> getExperimentSizes() {
        return experimentSizes;
    }

    public void setExperimentSizes(List<Integer> experimentSizes) {
        this.experimentSizes = experimentSizes;
    }

    public GeometryConfig getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryConfig geometry) {
        this.geometry = geometry;
    }

    public LoggingConfig getLogging() {
        return logging;
    }

    public void setLogging(LoggingConfig logging) {
        this.logging = logging;
    }

    public OutputConfig getOutput() {
        return output;
    }

    public void setOutput(OutputConfig output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "figureType='" + figureType + '\'' +
                ", randomSeed=" + randomSeed +
                ", experimentSizes=" + experimentSizes +
                ", geometry=" + geometry +
                ", logging=" + logging +
                ", output=" + output +
                '}';
    }

    public static class GeometryConfig {
        private double dx;
        private double dy;
        private double ex;
        private double ey;
        private double bx;
        private double by;
        private double ox;
        private double oy;
        private double circleCenterX;
        private double circleCenterY;
        private double radius;

        public double getDx() { return dx; }
        public void setDx(double dx) { this.dx = dx; }

        public double getDy() { return dy; }
        public void setDy(double dy) { this.dy = dy; }

        public double getEx() { return ex; }
        public void setEx(double ex) { this.ex = ex; }

        public double getEy() { return ey; }
        public void setEy(double ey) { this.ey = ey; }

        public double getBx() { return bx; }
        public void setBx(double bx) { this.bx = bx; }

        public double getBy() { return by; }
        public void setBy(double by) { this.by = by; }

        public double getOx() { return ox; }
        public void setOx(double ox) { this.ox = ox; }

        public double getOy() { return oy; }
        public void setOy(double oy) { this.oy = oy; }

        public double getCircleCenterX() { return circleCenterX; }
        public void setCircleCenterX(double circleCenterX) { this.circleCenterX = circleCenterX; }

        public double getCircleCenterY() { return circleCenterY; }
        public void setCircleCenterY(double circleCenterY) { this.circleCenterY = circleCenterY; }

        public double getRadius() { return radius; }
        public void setRadius(double radius) { this.radius = radius; }

        @Override
        public String toString() {
            return "GeometryConfig{" +
                    "dx=" + dx +
                    ", dy=" + dy +
                    ", ex=" + ex +
                    ", ey=" + ey +
                    ", bx=" + bx +
                    ", by=" + by +
                    ", ox=" + ox +
                    ", oy=" + oy +
                    ", circleCenterX=" + circleCenterX +
                    ", circleCenterY=" + circleCenterY +
                    ", radius=" + radius +
                    '}';
        }
    }

    public static class LoggingConfig {
        private String level;
        private String file;

        public String getLevel() { return level; }
        public void setLevel(String level) { this.level = level; }

        public String getFile() { return file; }
        public void setFile(String file) { this.file = file; }

        @Override
        public String toString() {
            return "LoggingConfig{" +
                    "level='" + level + '\'' +
                    ", file='" + file + '\'' +
                    '}';
        }
    }

    public static class OutputConfig {
        private boolean exportCsv;
        private String csvPath;

        public boolean isExportCsv() { return exportCsv; }
        public void setExportCsv(boolean exportCsv) { this.exportCsv = exportCsv; }

        public String getCsvPath() { return csvPath; }
        public void setCsvPath(String csvPath) { this.csvPath = csvPath; }

        @Override
        public String toString() {
            return "OutputConfig{" +
                    "exportCsv=" + exportCsv +
                    ", csvPath='" + csvPath + '\'' +
                    '}';
        }
    }
}
