package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * This class represents the directional light in the scene.
 * It is a constant light that is not affected by the distance from the light source.
 *
 * @ author - Eytan Kantman and Ori Perlmuter
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * @param intensity - light intensity
     * @param direction - light direction
     *                  constructor for directional light, calls super constructor for light intensity
     */
    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * @param point - origin of the light
     * @return color of the light at the given point
     */
    @Override
    public Color getIntensity(Point point) {
        return getIntensity();// same intensity for all points
    }

    /**
     * @param point satrting point of the ray
     * @return the value of the light vector at the given point
     */
    @Override
    public Vector getL(Point point) {
        return direction.normalize(); //that is the direction of the light, the vector from the point to the light
    }

    /*
    returns a list of vectors from the point to the light
     */
    @Override
    public List<Vector> getListL(Point p) {
        return List.of(getL(p));

    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
