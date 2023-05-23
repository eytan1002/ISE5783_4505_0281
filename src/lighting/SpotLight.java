package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * This class represents a point light in the scene.
 */
public class SpotLight extends PointLight {
    protected double narrowBeam = 1;//default value, so this will not affect the result

    private Vector direction;

    /**
     * constructor for point light, calls super constructor for light intensity and position
     *
     * @param intensity - light intensity
     * @param position  - light position
     * @param direction - light direction
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * using the formula from the presentation
     *
     * @param point origin of the light
     * @return
     */
    @Override
    public Color getIntensity(Point point) {
        double cos = direction.dotProduct(getL(point));
        return cos > 0 ? super.getIntensity(point).scale(Math.pow(cos, narrowBeam)) : Color.BLACK;
    }

    /**
     * for the bonus.
     *
     * @param i narrow beam value
     * @return this
     */
    public PointLight setNarrowBeam(int i) {
        this.narrowBeam = i;
        return this;
    }
}
