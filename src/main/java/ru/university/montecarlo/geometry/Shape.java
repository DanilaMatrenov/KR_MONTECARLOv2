package ru.university.montecarlo.geometry;

public interface Shape {
    boolean contains(Point point);
    double getExactArea();
    BoundingBox getBoundingBox();
}
