package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 This class represents a tube in 3D space.
 A tube is defined by its radius and an axis ray.
 */
public class Tube extends RadialGeometry {
    Ray axisRay;
    /**
     * Constructs a new tube object with the given radius and axis ray.
     * @param radius the radius of the tube.
     * @param ray the axis ray of the tube.
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
    /**
     * return null at the moment.
     * will Return the normal to the tube surface at the given point.
     * @param point the point to calculate the normal at.
     */
    public Vector getNormal(Point point) { return null; }
}
