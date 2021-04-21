package team.characters;

public class Character {
    protected String fajta;

    public Character(String fajta) {
        this.fajta = fajta;
    }

    public String getFajta() {
        return fajta;
    }

    @Override
    public String toString() {
        return "" + fajta;
    }
}
