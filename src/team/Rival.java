package team;

/**
 * rivalis felfedezok neveit es hirnevet tartalmazza
 */

public class Rival {
    private String nev;
    private int hirnev;

    public Rival(String nev, int hirnev) {
        this.nev = nev;
        this.hirnev = hirnev;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getHirnev() {
        return hirnev;
    }

    public void setHirnev(int hirnev) {
        this.hirnev = hirnev;
    }
}

