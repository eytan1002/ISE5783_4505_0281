package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * A class representing a cylinder in 3D space, extending the Tube class.
 * The cylinder is defined by its radius, axis ray and height.
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */
public class Cylinder extends Tube {
    double height;

    /**
     * Construct a cylinder, given parameters
     *
     * @param radius of the cylinder
     *               throw exception if the radius is negative
     * @param ray
     * @param height
     */
    public Cylinder(double radius, Ray ray, double height) {
        //throw exception if the height is negative
        super(radius, ray);
        if (height < 0) {
            throw new IllegalArgumentException("height cannot be negative");
        }
        this.height = height;
    }

    /**
     * Return the height
     */
    public double getHeight() {
        return height;
    }


    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        if (point.equals(p0))
            return v;

        // projection of P-p0 on the ray:
        Vector u = point.subtract(p0);

        // distance from p0 to the o who is in from of point
        double t = alignZero(u.dotProduct(v));

        // if the point is at a base
        if (t == 0 || isZero(height - t))
            return v;

        //the other point on the axis facing the given point
        Point o = p0.add(v.scale(t));
        return point.subtract(o).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }



}
