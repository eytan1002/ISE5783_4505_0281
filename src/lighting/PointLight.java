package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a point light in the scene.
 * It is a constant light that is not affected by the distance from the light source.
 *
 * @ author - Eytan Kantman and Ori Perlmutter
 */
public class PointLight extends Light implements LightSource {
    private Point position;
    private double Kc = 1;
    private double Kl = 0;
    private double Kq = 0;


    /**
     * size of radius around the light to create soft shadows
     */
    private Double radius;

    /**
     * constructor for point light
     *
     * @param intensity - light intensity
     * @param position  - light position
     */
    public PointLight(Color intensity, Point position, Double radius) {
        super(intensity);
        this.position = position;
        this.radius = radius;
    }

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setRadius(Double radius) {
        this.radius = radius;
        return this;
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

    /**
     * @param point - origin of the light
     * @return the distance between the light and the point
     */
    @Override
    public double getDistance(Point point) {
        return point.distance(this.position);
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

    /**
     * @param p point on the geometry that the light is on
     * @return list of vectors from the point to the light
     */
    public List<Vector> getListL(Point p) {
        List<Vector> vectors = new LinkedList();
        //grid of vectors around the light
        for (double i = -radius; i < radius; i += radius / 10) {
            for (double j = -radius; j < radius; j += radius / 10) {
                if (i != 0 && j != 0) {
                    //create a point on the grid
                    Point point = position.add(new Vector(i, 0.1d, j));
                    if (point.equals(position)) {
                        //if the point is the same as the light position,
                        // add the vector from the point to the light
                        vectors.add(p.subtract(point).normalize());
                    } else {
                        try {
                            if (point.subtract(position).dotProduct(point.subtract(position))
                                    <= radius * radius) {
                                //if the point is in the radius of the light, add the vector from the point to the light
                                vectors.add(p.subtract(point).normalize());
                            }
                        } catch (Exception e) {
                            //if the point is in the radius of the light, add the vector from the point to the light
                            vectors.add(p.subtract(point).normalize());
                        }

                    }
                }

            }
        }
        vectors.add(getL(p));
        return vectors;
    }


}
