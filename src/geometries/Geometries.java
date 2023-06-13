package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The class represents a collection of geometry shapes, and it implements the Intersectable interface.
 */
public class Geometries extends Intersectable {
    List<Intersectable> geometries;

    /**
     * Default constructor that creates an empty list of geometries.
     */
    public Geometries() {
        geometries = new LinkedList<Intersectable>();
    }

    /**
     * Constructor that gets a list of geometries and adds them to the geometries list.
     *
     * @param geometries The geometries to add to the geometries list.
     */
    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    /**
     * Adds a geometry to the geometries list.
     *
     * @param geometries The geometric objects to add to the geometries list.
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }


    /**
     * @param ray The ray that we want to find the intersections with the geometry shape
     * @return list of points where the ray intersect the geometry shape
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;   // intersection points

        //for each geometry in intersect-able collection check intersection points
        for (var item : geometries) {

            // get intersection point for each specific item, (item can be either geometry/nested composite of geometries)
            List<GeoPoint> itemList = item.findGeoIntersections(ray, maxDistance);

            // points were found , add to composite's total intersection points list
            if (itemList != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemList);
            }
        }
        // return list of points - null if no intersection points were found
        return result;

    }


}
