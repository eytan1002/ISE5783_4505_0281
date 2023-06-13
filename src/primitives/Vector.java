package primitives;

/**
 * The Vector class represents a mathematical vector in 3D space.
 * It is a subclass of the Point class and inherits its xyz field.
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */
public class Vector extends Point {
    /**
     * Constructs a new Vector with the given x, y, and z coordinates.
     *
     * @param x the x coordinate of the vector
     * @param y the y coordinate of the vector
     * @param z the z coordinate of the vector
     * @throws IllegalArgumentException if the vector is Vector(0,0,0)
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    /**
     * Constructs a new Vector with the given Double3.
     *
     * @param double3 the Double3 representing the vector
     * @throws IllegalArgumentException if the vector is Vector(0,0,0)
     */
    public Vector(Double3 double3) {
        super(double3);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector cannot be Vector(0,0,0)");
    }

    /**
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * @return the square of the length of the vector
     */
    public double lengthSquared() {
        double x = xyz.d1;
        double y = xyz.d2;
        double z = xyz.d3;

        return x * x + y * y + z * z;
    }

    /**
     * Returns the normalized vector (unit vector) in the same direction as the original vector.
     */
    public Vector normalize() {
        double len = length();
        return new Vector(xyz.d1 / len, xyz.d2 / len, xyz.d3 / len);
    }

    /**
     * Returns a new vector that is the result of adding the given vector to this vector.
     *
     * @param vector the vector to add to this vector
     * @throws IllegalArgumentException if the resulting vector is Vector(0,0,0)
     */
    public Vector add(Vector vector) {
        // make sure the resulting vector is not Vector(0,0,0)
        Double3 value = xyz.add(vector.xyz);
        if (value.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector cannot be Vector(0,0,0)");
        return new Vector(value);
    }

    /**
     * Returns a new vector that is the result of scaling this vector by the given scalar.
     *
     * @param d the scalar to multiply the vector by
     * @throws IllegalArgumentException if the resulting vector is Vector(0,0,0)
     */
    public Vector scale(double d) {
        if (d == 0) // make sure the resulting vector is not Vector(0,0,0)
            throw new IllegalArgumentException("Vector cannot be Vector(0,0,0)");
        return new Vector(xyz.scale(d));
    }

    /**
     * Returns the dot product of this vector and the given vector.
     *
     * @param vector the vector to calculate the dot product with
     */
    public double dotProduct(Vector vector) {
        return this.xyz.d1 * vector.xyz.d1 + this.xyz.d2 * vector.xyz.d2 + this.xyz.d3 * vector.xyz.d3;
    }

    /**
     * Returns the cross product of this vector and the specified vector.
     *
     * @param vector the vector to compute the cross product with
     * @throws IllegalArgumentException if the resulting vector is Vector(0,0,0)
     */
    public Vector crossProduct(Vector vector) {
        double x = this.xyz.d2 * vector.xyz.d3 - this.xyz.d3 * vector.xyz.d2;
        double y = this.xyz.d3 * vector.xyz.d1 - this.xyz.d1 * vector.xyz.d3;
        double z = this.xyz.d1 * vector.xyz.d2 - this.xyz.d2 * vector.xyz.d1;
        if (x == 0 && y == 0 && z == 0)
            throw new IllegalArgumentException("Vector cannot be Vector(0,0,0)");
        return new Vector(x, y, z);
    }

    /**
     * @return a string representation of this vector in the form "(x,y,z)"
     */
    @Override
    public String toString() {
        return "(" + xyz.d1 + "," + xyz.d2 + "," + xyz.d3 + ")";
    }

    @Override
    /**
     * Compares this vector to the specified object. The result is true if
     * and only if the argument is not null and is a Vector object that represents the same sequence
     * of coordinates as this object
     * @param o the object to compare this vector against
     * @return true if the given object represents a Vector equivalent to this vector, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);
    }

    /**
     * X Axis
     */
    public static final Vector X_AXIS = new Vector(1, 0, 0);
    /**
     * Y Axis
     */
    public static final Vector Y_AXIS = new Vector(0, 1, 0);
    /**
     * Z Axis
     */
    public static final Vector Z_AXIS = new Vector(0, 0, 1);


    public Vector vectorRotate(Vector axis, double theta) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        double u = axis.getX();
        double v = axis.getY();
        double w = axis.getZ();

        double v1 = u * x + v * y + w * z;

        double thetaRad = Math.toRadians(theta);
        double xPrime = u * v1 * (1d - Math.cos(thetaRad))
                + x * Math.cos(thetaRad)
                + (-w * y + v * z) * Math.sin(thetaRad);

        double yPrime = v * v1 * (1d - Math.cos(thetaRad))
                + y * Math.cos(thetaRad)
                + (w * x - u * z) * Math.sin(thetaRad);

        double zPrime = w * v1 * (1d - Math.cos(thetaRad))
                + z * Math.cos(thetaRad)
                + (-v * x + u * y) * Math.sin(thetaRad);

        return new Vector(xPrime, yPrime, zPrime);
    }

}
