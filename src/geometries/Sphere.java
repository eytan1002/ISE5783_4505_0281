
package geometries;

import primitives.Point;
import primitives.Ray;
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
        Vector L = center.subtract(ray.getP0());
        double tm = L.dotProduct(ray.getDir());
        double d = Math.sqrt(L.lengthSquared() - tm * tm);
        if (d > getRadius())
            return null;
        double th = Math.sqrt(getRadius() * getRadius() - d * d);
        double t1 = tm - th;
        double t2 = tm + th;
        if (t1 <= 0 && t2 <= 0)
            return null;
        Vector vec1 = ray.getDir().scale(t1);
        Vector vec2 = ray.getDir().scale(t2);
        Point p1 = ray.getP0().add(vec1);
        Point p2 = ray.getP0().add(vec2);
        if (t1 <= 0)
            return List.of(p2);
        if (t2 <= 0)
            return List.of(p1);
        return List.of(p1, p2);
    }
}
