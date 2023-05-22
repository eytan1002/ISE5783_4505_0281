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
    private final String name;
    private final Color background;

    private final Geometries geometries;
    private final AmbientLight ambientLight;

    public Geometries getGeometries() {
        return geometries;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Color getBackground() {
        return background;
    }

    private Scene (SceneBuilder builder){
        this.name = builder.name;
        this.ambientLight = builder.ambientLight;
        this.background = builder.background;
        this.geometries = builder.geometries;
    }

    public static class SceneBuilder{
        private final String name;
        private Color background = Color.BLACK;

        private Geometries geometries = new Geometries();
        private AmbientLight ambientLight = AmbientLight.NONE;

        public SceneBuilder(String name){
            this.name = name;
        }
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }
        public Scene build(){
            return new Scene(this);
        }
    }


}
