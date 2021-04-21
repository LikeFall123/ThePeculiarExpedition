package map;

public class Barlang extends Map{

    private boolean megtalalKincs;

    public Barlang() {
        super("/resources/barlang", 0);
        this.megtalalKincs = false;
    }

    public boolean isMegtalalKincs() {
        return megtalalKincs;
    }

    public void setMegtalalKincs() {
        this.megtalalKincs = true;
    }
}
