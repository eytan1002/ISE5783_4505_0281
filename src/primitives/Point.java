package primitives;

import java.util.Objects;

/**
 * This class represent a point object.
 *
 * @autor Ori perlmutter, Eytan Kantman
 */
public class Point {
    public static final Point ZERO = new Point(0, 0, 0);
    final Double3 xyz;

    /**
     * Constructs a new Point object with the given coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param z The z-coordinate of the point.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a new Point object with the given Double3 object.
     *
     * @param double3 The Double3 object containing the coordinates of the point.
     */
    Point(Double3 double3) {
        this(double3.d1, double3.d2, double3.d3);
    }

    /**
     * Checks whether this Point object is equal to another object.
     *
     * @param o The object to compare with this Point object.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    /**
     * @return The hash code value for this Point object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * @return A string representation of this Point object.
     */
    @Override
    public String toString() {
        return "Point: " + xyz;
    }

    /**
     * @param other The other Point object.
     * @return The distance between this Point object and the other Point object.
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * @param other The other Point object.
     * @return The squared distance between this Point object and the other Point object.
     */
    public double distanceSquared(Point other) {
        Double dx = other.xyz.d1 - xyz.d1;
        Double dy = other.xyz.d2 - xyz.d2;
        Double dz = other.xyz.d3 - xyz.d3;

        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Adds a vector to this Point object.
     *
     * @param vector The vector to add.
     * @return A new Point object representing the result of the addition.
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Subtracts another Point object from this Point object.
     *
     * @param point The Point object to subtract.
     * @return A new Vector object representing the result of the subtraction.
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    public double getX() {
        return xyz.d1;
    }

    public double getY() {
        return xyz.d2;
    }

    public double getZ() {
        return xyz.d3;
    }
}
