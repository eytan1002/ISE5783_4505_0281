package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * This class represents the ambient light in the scene.
 * It is a constant light that is not affected by the distance from the light source.
 *
 * @ author - Eytan Kantman and Ori Perlmuter
 */
public class AmbientLight extends Light {
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructor for AmbientLight
     *
     * @param ia - the color of the ambient light
     * @param ka - the coefficient of the ambient light
     *           calls super constructor for light intensity
     */
    public AmbientLight(Color ia, Double3 ka) {
        super(ia.scale(ka));
    }

    /**
     * Constructor for AmbientLight, for the case that ka is a scalar
     *
     * @param ia - the color of the ambient light
     * @param ka - the coefficient of the ambient light
     */
    public AmbientLight(Color ia, double ka) {//for the case that ka is a scalar
        super(ia.scale(ka));
    }
}
