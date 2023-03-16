package geometries;
/**

 RadialGeometry is an abstract class that represents the radius of various geometric shapes.
 It provides a constructor for initializing the radius and a getter method for retrieving the value of the radius.
 */
public abstract class RadialGeometry {
    protected double radius;
    /**
     * Constructs a new RadialGeometry object with the given radius.
     * @param radius the radius of the RadialGeometry object.
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
    /**
     * Returns the radius value of this RadialGeometry object.
     */
    public double getRadius() {
        return radius;
    }
}
