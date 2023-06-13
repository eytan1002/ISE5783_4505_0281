package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
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


    /**
     * find intersection points between ray and 3D cylinder
     *
     * @param ray ray towards the sphere
     * @return immutable list containing 0/1/2 intersection points as {@link GeoPoint}s objects
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // origin point of cylinder (on bottom base)
        Point basePoint = axisRay.getP0();
        // point across base point on top base
        Point topPoint = axisRay.getPoint(height);
        // direction vector of cylinder (orthogonal to base point)
        Vector vC = axisRay.getDir();

        // find intersection points of ray with bottom base of cylinder
        List<GeoPoint> result = new LinkedList<>();
        // crate plane that contains base point in it
        Plane basePlane = new Plane(basePoint, vC);
        // find intersection between ray and plane
        List<GeoPoint> intersectionsBase = basePlane.findGeoIntersections(ray, maxDistance);

        // if intersections were found, check that point are actually on the base of the cylinder
        //if distance from base point to intersection point holds the equation ->  distance² < from radius²
        if (intersectionsBase != null) {
            for (GeoPoint p : intersectionsBase) {
                Point pt = p.point;
                // intersection point is the base point itself
                if (pt.equals(basePoint))
                    result.add(new GeoPoint(this, basePoint));
                    // intersection point is different to base point but is on the bottom base
                else if (pt.subtract(basePoint).dotProduct(pt.subtract(basePoint)) < radius * radius)
                    result.add(new GeoPoint(this, pt));
            }
        }

        // find intersection points of ray with bottom base of cylinder
        // crate plane that contains top point in it
        Plane topPlane = new Plane(topPoint, vC);
        // find intersection between ray and plane
        List<GeoPoint> intersectionsTop = topPlane.findGeoIntersections(ray, maxDistance);
        // if intersections were found, check that point are actually on the base of the cylinder
        //if distance from top point to intersection point holds the equation ->  distance² < from radius²
        if (intersectionsTop != null) {
            for (var p : intersectionsTop) {
                Point pt = p.point;
                // intersection point is the top point itself
                if (pt.equals(topPoint))
                    result.add(new GeoPoint(this, topPoint));
                    // intersection point is different to base point but is on the bottom base
                else if (pt.subtract(topPoint).dotProduct(pt.subtract(topPoint)) < radius * radius)
                    result.add(new GeoPoint(this, pt));
            }
        }

        // if rsy intersects both bases , no other intersections possible - return the result list
        if (result.size() == 2)
            return result;

        // use tube parent class function to find intersections with the cylinder represented
        // as an infinite tube
        List<GeoPoint> intersectionsTube = super.findGeoIntersectionsHelper(ray, maxDistance);

        // if intersection points were found check that they are within the finite cylinder's boundary
        // by checking if  scalar product fo direction vector with a vector from intersection point
        // to bottom base point is positive, and scalar product of direction vector with a
        // vector from intersection point to top base point is negative
        if (intersectionsTube != null) {
            for (var p : intersectionsTube) {
                Point pt = p.point;
                if (vC.dotProduct(pt.subtract(basePoint)) > 0 && vC.dotProduct(pt.subtract(topPoint)) < 0)
                    result.add(new GeoPoint(this, pt));
            }
        }

        // return an immutable list
        int len = result.size();
        if (len > 0)
            if (len == 1)
                return List.of(result.get(0));
            else
                return List.of(result.get(0), result.get(1));

        // no intersections
        return null;
    }


}
