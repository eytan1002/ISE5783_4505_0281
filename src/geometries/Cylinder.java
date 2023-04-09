package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * A class representing a cylinder in 3D space, extending the Tube class.
 * <p>
 * The cylinder is defined by its radius, axis ray and height.
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

    /**
     * Return the normal Using tube's getNormal function, because we presume that point is on the cylinder
     */
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
}
