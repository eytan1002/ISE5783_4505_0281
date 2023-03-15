package geometries;

public abstract class RadialGeometry {
    protected double radius;

    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
