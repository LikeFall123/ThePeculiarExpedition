package objects;

/**
 * atalanos dolog, lehet eszkoz, kincs, vagy etel
 */

public class Item {
    protected String nev;
    protected int ertek; //mennyibe kerul

    public Item(String nev, int ertek) {
        this.nev = nev;
        this.ertek = ertek;
    }

    public String getNev() {
        return nev;
    }

    public int getErtek() {
        return ertek;
    }

    @Override
    public String toString() {
        return  "" + nev;
    }
}
