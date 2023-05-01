package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

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


    @Override
    public List<Point> findIntersections(Ray ray) {
        // if the ray starts at the center of the sphere
        double tm = 0;
        double d = 0;
        if (!center.equals(ray.getP0())){ // if the ray doesn't start at the center of the sphere
            Vector L = center.subtract(ray.getP0());
            tm = L.dotProduct(ray.getDir());
            d =L.lengthSquared() - tm * tm; // d = (|L|^2 - tm^2)
            if (d < 0)
                d = -d;
            d = Math.sqrt(d);
        }
        if (d > getRadius()) // if the ray doesn't intersect the sphere
            return null;
        // computing the distance from the ray's start point to the intersection points
        double th = Math.sqrt(getRadius() * getRadius() - d * d);
        double t1 = tm - th;
        double t2 = tm + th;
        if (t1 <= 0 && t2 <= 0)
            return null;
        if (Util.alignZero(t2) == 0) // if the ray is tangent to the sphere
            return null;
        if (th == 0)
            return null;
        if (t1 <= 0){ // if the ray starts inside the sphere or the ray starts after the sphere
            return List.of(ray.findPoint(t2));
        }
        if (t2 <= 0) { //if the ray starts after the sphere
            return List.of(ray.findPoint(t1));
        }
        return List.of(ray.findPoint(t1), ray.findPoint(t2)); // if the ray intersects the sphere twice
    }
}