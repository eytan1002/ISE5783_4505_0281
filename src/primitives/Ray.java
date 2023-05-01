package primitives;

/**
 * The Ray class represents a ray in 3D space, composed of a starting point p0 and a direction vector dir.
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */
public class Ray {
    final Point p0; // the starting point of the ray
    final Vector dir; // the direction vector of the ray

    /**
     * Constructs a new Ray object with a given direction vector and starting point.
     *
     * @param v the direction vector of the ray, which gets normalized
     * @param p the starting point of the ray
     */
    public Ray(Vector v, Point p) {
        dir = v.normalize();
        p0 = p;
    }

    /**
     * Returns the starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Compares this ray to the specified object for equality.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ray other)
            return this.p0.xyz.equals(other.p0) && this.dir.equals(other.dir);
        return false;
    }

    /**
     * Returns a string representation of the ray, in the format "(p0,dir)".
     */
    @Override
    public String toString() {
        return p0.toString() + dir.toString();
    }
    public Point findPoint(double d){
        return this.p0.add(this.dir.scale(d));
    }


}