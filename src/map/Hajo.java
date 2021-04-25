package map;

import objects.Item;
import team.slots.Slot;

/**
 * Hajo, egy map elem. De van sajat raktara
 */

public class Hajo extends Map {

    private Slot[] raktar;

    public Slot[] getRaktar() {
        return raktar;
    }

    public Hajo() {
        super("/resources/hajo", 0);
        this.raktar = new Slot[20];
    }

    /**
     * Elraktarozza a jatekos ezt az elemet
     * @param item elem
     */
    public void elraktaroz(Item item) {
        for (int i = 0; i < 20; i++) {
            if (raktar[i] == null) {
                raktar[i] = new Slot(item);
                break;
            } else {
                if (!raktar[i].addItem(item)) {
                    continue;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Kiveszi a jatekos ezt az elemet
     * @param ind index
     */
    public void consumeItem(int ind) {
        if (raktar[ind] != null) {
            if (raktar[ind].getSlots()[1] == null) {
                raktar[ind] = null;
            } else if (raktar[ind].getSlots()[6] != null) {
                raktar[ind].getSlots()[6] = null;
            } else {
                for (int j = 0; j < 6; j++) {
                    if (raktar[ind].getSlots()[j] != null && raktar[ind].getSlots()[j + 1] == null) {
                        raktar[ind].getSlots()[j] = null;
                    }
                }
            }
        }
    }
}
