package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    double height;

    public Cylinder(double radius, Ray ray,double height) {
        super(radius, ray);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }
    public Vector getNormal(Point point) { return null; }
}
