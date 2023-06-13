package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * interface for all lights
 *
 * @ author - Eytan Kantman and Ori Perlmuter
 */
public interface LightSource {
    /**
     * get intensity of the light from a Point
     *
     * @param p origin of the light
     * @return color of the light
     */
    public Color getIntensity(Point p);

    /**
     * get Light direction vector
     *
     * @param p satrting point
     * @return direction of light
     */
    public Vector getL(Point p);

    /**
     * get a beam of rays from a point on a geometry towards a light,
     * all the rays are constructed within the soft shadow radius boundary
     *
     * @param p point on the geometry
     * @return {@link List}of rys from the geometry to the soft shadow radius
     * @author Yona Shmerla
     */
    public List<Vector> getListL(Point p);


    double getDistance(Point point);
}
