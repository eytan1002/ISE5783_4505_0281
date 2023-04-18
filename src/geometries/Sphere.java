package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a sphere in 3D space using its radius and center point
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
}
