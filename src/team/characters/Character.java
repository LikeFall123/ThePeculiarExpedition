package team.characters;

/**
 * altalanos karakter, ennek gyerekei a specialis karakterek
 */

public class Character {
    protected String fajta;
    protected boolean alkoholista;
    protected boolean fuggo;
    protected int fuggoLepes;

    public Character(String fajta) {
        this.fajta = fajta;
        this.alkoholista=false;
        this.fuggo=false;
        this.fuggoLepes=0;
    }


    public void setAlkoholista(boolean alkoholista) {
        this.alkoholista = alkoholista;
    }

    public boolean isFuggo() {
        return fuggo;
    }

    public void setFuggo(boolean fuggo) {
        this.fuggo = fuggo;
    }

    public int getFuggoLepes() {
        return fuggoLepes;
    }

    public void setFuggoLepes(int fuggoLepes) {
        this.fuggoLepes = fuggoLepes;
    }

    public String getFajta() {
        return fajta;
    }

    private String fug(){
        if(this.fuggo){
            return ":fuggo(" + this.fuggoLepes + ")";
        }
        return "";
    }

    @Override
    public String toString() {
        return "" + fajta + fug();
    }
}
