package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
    Ray axisRay;

    public Tube(double radius, Ray ray) {
        super(radius);
        this.axisRay = ray;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public Vector getNormal(Point point) { return null; }
}
