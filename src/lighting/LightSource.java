package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface for all lights
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
     * get Loght direction vector
     *
     * @param p satrting point
     * @return direction of light
     */
    public Vector getL(Point p);

    double getDistance(Point point);
}
