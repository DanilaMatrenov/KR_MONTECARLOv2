package ru.university.montecarlo.montecarlo;

import ru.university.montecarlo.geometry.BoundingBox;
import ru.university.montecarlo.geometry.Point;

import java.util.Random;

public class RandomPointGenerator {

    private final Random random;

    public RandomPointGenerator(long seed) {
        this.random = new Random(seed);
    }

    public Point nextPoint(BoundingBox box) {
        double x = box.getMinX() + random.nextDouble() * box.getWidth();
        double y = box.getMinY() + random.nextDouble() * box.getHeight();
        return new Point(x, y);
    }
}
