package objects;

public class Food extends Item{
    protected int bonus;

    public Food(String nev, int ertek, int bonus) {
        super(nev,ertek);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}
