package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * The Plane class represents a plane in 3D space, defined by a point on the plane and a normal vector to the plane.
 * The plane can also be defined by three non-collinear points in space.
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */
public class Plane extends Geometry {
    Point q0;
    Vector normal;

    /**
     * Constructs a plane by a point on the plane and a normal vector to the plane.
     *
     * @param q0     A point on the plane.
     * @param normal A normal vector to the plane.
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a plane by three non-collinear points in space By creating 2
     * vectors on it and calc their cross product vector
     *
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

    // getting the plane's normal
    public Vector getNormal() {
        return normal.normalize();
    }

    //@param point The point at which the normal vector is requested.
    //since the normal vector of a plane is orthogonal to the plane, it is the same at any point on the plane
    public Vector getNormal(Point point) {
        //if point is not on the plane, throw exception
        if (!isZero((getNormal()).dotProduct(point.subtract(q0))))
            throw new IllegalArgumentException("Point is not on the plane");
        return normal.normalize();
    }

    /**
     * @param ray intersecting the geometry shape
     * @return list of intersection points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // if the ray and plane are parallel (i.e., dot product between their normal vectors is 0),
        // then there is no intersection between them
        double denominator = this.getNormal().dotProduct(ray.getDir());
        if (isZero(denominator)) {
            return null;
        }
        // if the ray's starting point is on the plane, then the ray not intersects the plane at the starting point
        if (q0.equals(ray.getP0()))
            return null;
        // t represents the distance between the ray's starting point and the intersection point
        double t = (this.getNormal().dotProduct(q0.subtract(ray.getP0()))) / denominator;
        if (t > 0) {
            return List.of(new GeoPoint(this, ray.findPoint(t)));
        }
        return null;
    }
}