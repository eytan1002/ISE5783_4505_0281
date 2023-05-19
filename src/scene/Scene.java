package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;
import primitives.Point;

import java.util.List;

/**
 * class Scene is the class representing the scene to be rendered
 * it contains the name of the scene, the background color, the ambient light and the geometries
 * it has setters for each of the fields, returning the object itself
 * @ author - Eytan Kantman and Ori Perlmuter
 */
public class Scene {
    public final String name;
    public Color background = Color.BLACK;

    public Geometries geometries = new Geometries();
    public AmbientLight ambientLight = AmbientLight.NONE;

    public Scene(String name) {
        this.name = name;
    }
    //setters, returning the object:
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }


}
