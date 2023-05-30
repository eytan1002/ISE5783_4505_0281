package lighting;

import primitives.Color;

/**
 * abstract class for all lights
 *
 * @ author - Eytan Kantman and Ori Perlmuter
 */
public abstract class Light {
    private final Color intensity;

    /**
     * constructor for light
     *
     * @param intensity light intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for light intensity
     *
     * @return light intensity
     */
    public Color getIntensity() {
        return intensity;
    }

}
