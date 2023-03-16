package geometries;

import primitives.Point;
import primitives.Vector;
/**

 The Plane class represents a plane in 3D space, defined by a point on the plane and a normal vector to the plane.
 The plane can also be defined by three non-collinear points in space.
 */
public class Plane {
    Point q0;
    Vector normal;
    /**
     * Constructs a plane by a point on the plane and a normal vector to the plane.
     * @param q0     A point on the plane.
     * @param normal A normal vector to the plane.
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }
    /**
     * Constructs a plane by three non-collinear points in space By creating 2 vectors on it and calc their cross product vector
     * @param p1 A point on the plane.
     * @param p2 A second point on the plane.
     * @param p3 A third point on the plane.
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        Vector nV = v1.crossProduct(v2);

        this.normal = nV.normalize();
        this.q0 = p2;
    }
    /**
     * Returns the normal vector to the plane.
     */
    public Vector getNormal(){
        return normal.normalize();
    }
    /**
     * Returns the normal vector to the plane at a specific point.
     * @param point The point at which the normal vector is requested.
     * @return The normal vector to the plane.
     */
    public Vector getNormal(Point point){
        return normal.normalize();
    }
}
