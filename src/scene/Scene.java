package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * class Scene is the class representing the scene to be rendered
 * it contains the name of the scene, the background color, the ambient light and the geometries
 * it has setters for each of the fields, returning the object itself
 *
 * @ author - Eytan Kantman and Ori Perlmuter
 */
public class Scene {
    private final String name;
    private final Color background;

    private final Geometries geometries;
    private final AmbientLight ambientLight;
    private final List<LightSource> lights;

    /**
     * @param builder - the builder for the scene
     */
    private Scene(SceneBuilder builder) {
        this.name = builder.name;
        this.ambientLight = builder.ambientLight;
        this.background = builder.background;
        this.geometries = builder.geometries;
        this.lights = builder.lights;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public List<LightSource> getLights() {
        return lights;
    }

    public Color getBackground() {
        return background;
    }

    /**
     * class SceneBuilder is the builder for the scene, it has setters for each of the fields,
     * returning the object itself.
     */
    public static class SceneBuilder {
        private final String name;
        private Color background = Color.BLACK;
        private List<LightSource> lights = new LinkedList<>();
        private Geometries geometries = new Geometries();
        private AmbientLight ambientLight = AmbientLight.NONE;

        public SceneBuilder(String name) {
            this.name = name;
        }

        /// setters
        /// chaining methods to set all the fields
        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
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

        public Scene build() {
            return new Scene(this);
        }
    }


}
