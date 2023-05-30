package primitives;

/**
 * class Material is the class representing the material of a geometry
 * it contains the diffuse component, the specular component and the shininess
 */
public class Material {
    /**
     * Kd - diffuse component, represents the scattering of light rays to all directions from the surface
     */
    private Double3 Kd = Double3.ZERO;

    /**
     * Ks - specular component, represents the reflectance of the light source over the surface
     */
    private Double3 Ks = Double3.ZERO;

    /**
     * Shininess - how shiny the material is
     */
    private int nShininess = 0;

    /**
     * Kr - reflection component, represents the reflectance of the light source over the surface
     */
    private Double3 Kr = Double3.ZERO;
    /**
     * Kt - transparency component, represents the transparency of the material
     */
    private Double3 Kt = Double3.ZERO;

    //Getters
    public Double3 getKd() {
        return Kd;
    }

    public Material setKd(double kd) {
        this.Kd = new Double3(kd);
        return this;
    }

    public Material setKd(Double3 kd) {
        this.Kd = kd;
        return this;
    }

    public Double3 getKs() {
        return Ks;
    }

    public Material setKs(double ks) {
        Ks = new Double3(ks);
        return this;
    }

    public Material setKs(Double3 ks) {
        Ks = ks;
        return this;
    }

    public Double3 getKr() {
        return Kr;
    }

    public Material setKr(double kr) {
        this.Kr = new Double3(kr);
        return this;
    }

    public Material setKr(Double3 kr) {
        this.Kr = kr;
        return this;
    }

    public Double3 getKt() {
        return Kt;
    }

    public Material setKt(double kt) {
        this.Kt = new Double3(kt);
        return this;
    }

    public Material setKt(Double3 kt) {
        this.Kt = kt;
        return this;
    }

    public int getShininess() {
        return nShininess;
    }

    //*********Setters*********
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
