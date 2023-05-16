package lighting;
import primitives.Color;
import primitives.Double3;

public class AmbientLight {
    private Color IA;
    private Double3 KA;
    private Color intensity;

    public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    public AmbientLight(Color ia, Double3 ka) {
        this.IA = ia;
        this.KA = ka;
        this.intensity = ia.scale(ka);
    }
    public AmbientLight(Color ia, double ka) {//for the case that ka is a scalar
        this.IA = ia;
        this.KA = new Double3(ka, ka, ka);
        this.intensity = ia.scale(ka);
    }

    public Color getIntensity() {
        return intensity;
    }
}
