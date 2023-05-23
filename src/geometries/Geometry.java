package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * The class uses as an interface, we chose to make it abstract class, because polygon extends it, and it cant extend an interface
 *
 * @author Ori Perlmutter, Eitan Kaantman
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * Abstract method for getting the normal vector to a geometry at a given point
     *
     * @param point The point on the geometry for which to get the normal vector
     * @return The normal vector to the geometry at the given point
     */
    public abstract Vector getNormal(Point point);

    /**
     * @return The emission color of the geometry
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * @return The material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * sets the material of the geometry
     *
     * @param material The material to set
     * @return The geometry itself
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * @param emission The emission color of the geometry
     * @return The geometry itself
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

}
