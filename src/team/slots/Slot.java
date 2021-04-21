package team.slots;

import objects.Item;
import objects.items.Kincs;


public class Slot {
    protected Item[] slots = new Item[7];

    public Slot(Item item) {
        this.slots[0] = item;
    }

    public Item[] getSlots() {
        return slots;
    }

    public boolean addItem(Item item) {
        if(slots[0] instanceof Kincs){
            return false;
        }else if (slots[0].getClass() == item.getClass()) {
            for (int i = 1; i < 7; i++) {
                if (slots[i] == null) {
                    slots[i] = item;
                    return true;
                }
            }
        }
        return false;
    }

    private int count(){
        for(int i =0;i< slots.length;i++){
            if(slots[i]==null){
                return i;
            }
        }
        return 7;
    }

    @Override
    public String toString() {
        if(slots[0] instanceof Kincs){
            return "" + slots[0].getNev() + "(" + slots[0].getErtek() + "$)";
        }else
        return "" + slots[0].getNev() + "(" + count() + ")";
    }
}
