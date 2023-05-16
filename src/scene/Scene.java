package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;
import primitives.Point;

import java.util.List;

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
