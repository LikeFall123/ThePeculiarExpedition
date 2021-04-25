package objects;

/**
 * Altalanos etel osztaly
 */

public class Food extends Item{

    /**
     * mennyi plusz energiat ad az etel
     */
    protected int bonus;

    public Food(String nev, int ertek, int bonus) {
        super(nev,ertek);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}
