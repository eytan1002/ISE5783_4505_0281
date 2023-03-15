package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane {
    Point q0;
    Vector normal;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        Vector nV = v1.crossProduct(v2);

        this.normal = nV.normalize();
        this.q0 = p2;
    }
    public Vector getNormal(){
        return normal.normalize();
    }

    public Vector getNormal(Point point){
        return normal.normalize();
    }
}
