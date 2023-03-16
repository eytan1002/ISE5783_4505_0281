package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**

 A class representing a cylinder in 3D space, extending the Tube class.

 The cylinder is defined by its radius, axis ray and height.
 */
public class Cylinder extends Tube{
    double height;

    /**
     * Construct a cylinder, given parameters
     * @param radius of the cylinder
     * @param ray
     * @param height
     */
    public Cylinder(double radius, Ray ray,double height) {
        super(radius, ray);
        this.height = height;
    }
    /**
     * Return the height
     */
    public double getHeight() {
        return height;
    }
    /**
     * Return the normal when we will implement is.
     */
    public Vector getNormal(Point point) { return null; }
}
