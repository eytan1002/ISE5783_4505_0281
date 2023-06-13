package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a sphere in 3D space using its radius and center point
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */

public class Sphere extends RadialGeometry {
    Point center;

    /**
     * Constructs a new Sphere object with the given radius and center point
     *
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the center point of the sphere
     */
    public Point getCenter() {
        return center;
    }


    //      Calculates and returns the normal vector to the sphere at a given point
//      @param point the point on the sphere to calculate the normal at
//                   will @return the normal vector to the sphere at the given point
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }


    /**
     * @param ray intersecting the geometry shape
     * @return list of intersection points.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(center)) {
            if (alignZero(radius - maxDistance) > 0) {
                return null;
            }
            return List.of(new GeoPoint(this, center.add(v.scale(radius))));
        }

        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 <= 0 && t2 <= 0) {
            return null;
        }

        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
//            Point P1 = P0.add(v.scale(t1));
//            Point P2 = P0.add(v.scale(t2));
            Point P1 = ray.getPoint(t1);
            Point P2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
        }
        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
//            Point P1 = P0.add(v.scale(t1));
            Point P1 = ray.getPoint(t1);
            return List.of(new GeoPoint(this, P1));
        }
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
//            Point P2 = P0.add(v.scale(t2));
            Point P2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, P2));
        }
        return null;
    }

}