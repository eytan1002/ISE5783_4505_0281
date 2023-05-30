package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a tube in 3D space.
 * A tube is defined by its radius and an axis ray.
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */
public class Tube extends RadialGeometry {
    Ray axisRay;

    /**
     * Constructs a new tube object with the given radius and axis ray.
     *
     * @param radius the radius of the tube.
     * @param ray    the axis ray of the tube.
     */
    public Tube(double radius, Ray ray) {
        super(radius);
        this.axisRay = ray;
    }

    /**
     * Returns the axis ray of the tube.
     */
    public Ray getAxisRay() {
        return axisRay;
    }


    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();
        Vector p0_p = point.subtract(p0);
        double t = alignZero(v.dotProduct(p0_p));
        if (isZero(t)) {
            return p0_p.normalize();
        }
        Point o = p0.add(v.scale(t));
        if (point.equals(o)) {
            throw new IllegalArgumentException("point cannot be on the tube axis");
        }
        Vector n = point.subtract(o).normalize();
        return n;
    }

    /**
     * @param ray intersecting the geometry
     * @return null at the moment.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }


}

