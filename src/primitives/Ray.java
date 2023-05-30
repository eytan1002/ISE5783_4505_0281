package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

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
     * @param direction vector of the ray, which gets normalized
     * @param p         the starting point of the ray
     */
    public Ray(Point p, Vector direction) {
        dir = direction.normalize();
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
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * Returns a string representation of the ray, in the format "(p0,dir)".
     */
    @Override
    public String toString() {
        return p0.toString() + dir.toString();
    }

    public Point findPoint(double d) {
        return this.p0.add(this.dir.scale(d));
    }


    /**
     * for the moment, there are 2 similar funcs, one for GeoPoint and one for Point.
     *
     * @param intersections, a list of intersections.
     * @return the closest Geopoint to the ray's starting point, or null if the list is empty.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
        if (intersections == null || intersections.isEmpty())
            return null;
        //compute the distance between the ray's starting point and the first point in the list
        double minDistance = Double.MAX_VALUE;
        GeoPoint minPoint = intersections.get(0);
        for (GeoPoint gp : intersections) {
            double dist = gp.point.distanceSquared(p0);
            if (dist < minDistance) {
                minDistance = dist;
                minPoint = gp;
            }
        }
        return minPoint;
    }

    /**
     * @param points, a list of intersections.
     * @return the closest point to the ray's starting point, or null if the list is empty.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream()
                .map(p -> new GeoPoint(null, p))
                .toList()).point;
    }

}