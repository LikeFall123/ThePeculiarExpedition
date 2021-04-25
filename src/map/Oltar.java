package map;

/**
 * Oltar, amiben kincs tallahato
 */

public class Oltar extends Map{

    private boolean megtalalKincs;

    public Oltar() {
        super("/resources/oltar",0);
        this.megtalalKincs = false;
    }

    public boolean isMegtalalKincs() {
        return megtalalKincs;
    }

    public void setMegtalalKincs() {
        this.megtalalKincs = true;
    }
}
