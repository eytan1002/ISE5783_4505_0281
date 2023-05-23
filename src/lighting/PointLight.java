package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This class represents a point light in the scene.
 * It is a constant light that is not affected by the distance from the light source.
 */
public class PointLight extends Light implements LightSource {
    private Point position;
    private double Kc = 1;
    private double Kl = 0;
    private double Kq = 0;

    /**
     * constructor for point light
     * @param intensity - light intensity
     * @param position - light position
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * using the formula from the presentation
     */
    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        double denominator = Kc + Kl * d + Kq * d * d;
        return getIntensity().reduce(denominator);

    }

    /**
     * using the formula from the presentation
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();

    }


    public PointLight setKc(double kc) {
        this.Kc = kc;
        return this;
    }

    public PointLight setKl(double kl) {
        this.Kl = kl;
        return this;
    }

    public PointLight setKq(double kq) {
        this.Kq = kq;
        return this;
    }

}
