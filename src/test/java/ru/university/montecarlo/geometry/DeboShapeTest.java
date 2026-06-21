package ru.university.montecarlo.geometry;

import org.junit.jupiter.api.Test;
import ru.university.montecarlo.config.AppConfig;

import static org.junit.jupiter.api.Assertions.*;

class DeboShapeTest {

    private static final double EPSILON = 1e-6;

    private DeboShape createShape() {
        AppConfig.GeometryConfig cfg = new AppConfig.GeometryConfig();

        // Значения по умолчанию (как в application.yml)
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
    void shouldContainPointInsideArcSector() {
        DeboShape shape = createShape();

        // Точка между D(0,2) и E(2,2) на дуге радиуса 2
        Point p = new Point(1.5, 1.3);

        assertTrue(shape.contains(p));
    }

    @Test
    void shouldContainPointInsidePolygonE_B_O_D() {
        DeboShape shape = createShape();

        // Точка внутри четырёхугольника E(2,2) – B(2,0) – O(0,0) – D(0,2)
        Point p = new Point(1.0, 1.0);

        assertTrue(shape.contains(p));
    }

    @Test
    void shouldNotContainPointOutsideBoundingBox() {
        DeboShape shape = createShape();

        Point p = new Point(5.0, 5.0);

        assertFalse(shape.contains(p));
    }

    @Test
    void shouldNotContainPointOutsideShapeButInsideBoundingBox() {
        DeboShape shape = createShape();

        // Точка над дугой, но внутри bbox
        Point p = new Point(1.0, 3.0);

        assertFalse(shape.contains(p));
    }

    @Test
    void shouldReturnCorrectBoundingBox() {
        DeboShape shape = createShape();
        BoundingBox box = shape.getBoundingBox();

        assertEquals(0.0, box.getMinX(), EPSILON);
        assertEquals(2.0, box.getMaxX(), EPSILON);
        assertEquals(0.0, box.getMinY(), EPSILON);
        assertEquals(2.0, box.getMaxY(), EPSILON);
    }

    @Test
    void shouldCalculateExactArea() {
        DeboShape shape = createShape();

        double expectedArea = Math.PI / 2.0;

        assertEquals(expectedArea, shape.getExactArea(), EPSILON);
    }

}
