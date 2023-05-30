package geometries;

/**
 * RadialGeometry is an abstract class that represents the radius of various geometric shapes.
 * It provides a constructor for initializing the radius and a getter method for retrieving the value of the radius.
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */
public abstract class RadialGeometry extends Geometry {
    protected double radius;

    /**
     * Constructs a new RadialGeometry object with the given radius.
     *
     * @param radius the radius of the RadialGeometry object.
     */
    public RadialGeometry(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Radius cannot be negative or zero");
        this.radius = radius;
    }

    /**
     * Returns the radius value of this RadialGeometry object.
     */
    public double getRadius() {
        return radius;
    }
}
