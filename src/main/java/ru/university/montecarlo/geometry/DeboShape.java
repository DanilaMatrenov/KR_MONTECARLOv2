package ru.university.montecarlo.geometry;

import ru.university.montecarlo.config.AppConfig;

public class DeboShape implements Shape {

    private final Point d;
    private final Point e;
    private final Point b;
    private final Point o;

    private final Point center;
    private final double radius;

    private final CircularArc arcDE;
    private final LineSegment segEB;
    private final LineSegment segBO;
    private final LineSegment segOD;

    private final BoundingBox boundingBox;

    public DeboShape(AppConfig.GeometryConfig cfg) {

        this.d = new Point(cfg.getDx(), cfg.getDy());
        this.e = new Point(cfg.getEx(), cfg.getEy());
        this.b = new Point(cfg.getBx(), cfg.getBy());
        this.o = new Point(cfg.getOx(), cfg.getOy());

        this.center = new Point(cfg.getCircleCenterX(), cfg.getCircleCenterY());
        this.radius = cfg.getRadius();

        // Углы дуги D → E
        double startAngle = Math.atan2(d.getY() - center.getY(), d.getX() - center.getX());
        double endAngle   = Math.atan2(e.getY() - center.getY(), e.getX() - center.getX());

        // Дуга всегда короткая
        this.arcDE = new CircularArc(center, radius, startAngle, endAngle);

        this.segEB = new LineSegment(e, b);
        this.segBO = new LineSegment(b, o);
        this.segOD = new LineSegment(o, d);

        this.boundingBox = new BoundingBox(
                Math.min(Math.min(d.getX(), e.getX()), Math.min(b.getX(), o.getX())),
                Math.max(Math.max(d.getX(), e.getX()), Math.max(b.getX(), o.getX())),
                Math.min(Math.min(d.getY(), e.getY()), Math.min(b.getY(), o.getY())),
                Math.max(Math.max(d.getY(), e.getY()), Math.max(b.getY(), o.getY()))
        );
    }

    @Override
    public boolean contains(Point p) {

        if (!boundingBox.contains(p)) {
            return false;
        }

        // 1) Если точка внутри сектора дуги D–E — она внутри фигуры
        if (arcDE.isInsideSector(p)) {
            return true;
        }

        // 2) Иначе проверяем попадание в многоугольник E–B–O–D
        return isInsidePolygon(p);
    }

    private boolean isInsidePolygon(Point p) {
        Point[] poly = {e, b, o, d};
        double area = polygonArea(poly);

        double a1 = polygonArea(new Point[]{p, b, o, d});
        double a2 = polygonArea(new Point[]{e, p, o, d});
        double a3 = polygonArea(new Point[]{e, b, p, d});
        double a4 = polygonArea(new Point[]{e, b, o, p});

        return Math.abs(area - (a1 + a2 + a3 + a4)) < 1e-6;
    }

    private double polygonArea(Point[] pts) {
        double sum = 0.0;
        for (int i = 0; i < pts.length; i++) {
            Point p1 = pts[i];
            Point p2 = pts[(i + 1) % pts.length];
            sum += p1.getX() * p2.getY() - p2.getX() * p1.getY();
        }
        return Math.abs(sum) / 2.0;
    }

    @Override
    public double getExactArea() {

        // Треугольник D–E–O
        double areaDEO = triangleArea(d, e, o);

        // Треугольник E–B–O
        double areaEBO = triangleArea(e, b, o);

        // Угол дуги D–E
        double angle = Math.abs(arcDE.getEndAngle() - arcDE.getStartAngle());
        if (angle > Math.PI) {
            angle = 2 * Math.PI - angle; // короткая дуга
        }

        // Площадь сектора
        double sector = 0.5 * radius * radius * angle;

        // Площадь сегмента = сектор - треугольник DEO
        double segment = sector - areaDEO;

        // Итоговая площадь DEBO
        return areaEBO + segment;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
    private double triangleArea(Point a, Point b, Point c) {
        return Math.abs(
                a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())
        ) / 2.0;
    }

    @Override
    public String toString() {
        return "DeboShape{" +
                "d=" + d +
                ", e=" + e +
                ", b=" + b +
                ", o=" + o +
                ", center=" + center +
                ", radius=" + radius +
                '}';
    }
}
