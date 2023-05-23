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

    //*********Setters*********

    public Material setKs(double ks) {
        Ks = new Double3(ks);
        return this;
    }

    public Material setKd(double kd) {
        this.Kd = new Double3(kd);
        return this;
    }

    public Material setKs(Double3 ks) {
        Ks = ks;
        return this;
    }

    public Material setKd(Double3 kd) {
        this.Kd = kd;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    //Getters
    public Double3 getKd() {
        return Kd;
    }

    public Double3 getKs() {
        return Ks;
    }

    public int getShininess() {
        return nShininess;
    }

}
