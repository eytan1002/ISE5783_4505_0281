package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * The class uses as an interface, we chose to make it abstract class, and it cant extend an interface
 * @author Ori Perlmutter, Eitan Kaantman
 */
public abstract class Intersectable {

    public  static class  GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * constructor
         *
         * @param geometry the geometry shape
         * @param point    the point where the ray intersect the geometry shape
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * @param o the object that we want to compare
         * @return true if the object is equal to the geometry shape and the point
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        public double getX() {
            return point.getX();
        }

        public double getY() {
            return point.getY();
        }

        public double getZ() {return point.getZ();}
    }
        public final List<GeoPoint> findGeoIntersections(Ray ray) {

        return findGeoIntersectionsHelper(ray);
        }

    /**
     *
     * @param  ray intersecting the geometry
     * @return list of intersection points
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * @param ray The ray that we want to find the intersections with the geometry shape
     * @return list of points where the ray intersect the geometry shape, using the findGeoIntersections method
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

}
