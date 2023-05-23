package lighting;

import primitives.Color;

/**
 * abstract class for all lights
 */
public abstract class Light {
    private Color intensity;

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
