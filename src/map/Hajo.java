package map;

import objects.Item;
import team.slots.Slot;

public class Hajo extends Map{

    private Slot[] raktar;

    public Slot[] getRaktar() {
        return raktar;
    }

    public Hajo() {
        super("/resources/hajo",0);
        this.raktar = new Slot[20];
    }

    public void elraktaroz(Item item){
        for(int i=0;i<20;i++){
            if(raktar[i]==null){
                raktar[i] = new Slot(item);
                break;
            }else{
                if(raktar[i].addItem(item)==false){
                    continue;
                }else {
                    break;
                }
            }
        }
    }

    public void consumeItem(Item item){
        for (int i = 0; i < 20; i++) {
            if (raktar[i] != null) {
                if (raktar[i].getSlots()[0].getClass() == item.getClass()) {
                    if(raktar[i].getSlots()[1]==null){
                        raktar[i]=null;
                    }else{
                        for(int j=0;j<6;j++){
                            if(raktar[i].getSlots()[j]!=null && raktar[i].getSlots()[j+1]==null){
                                raktar[i].getSlots()[j]=null;
                            }
                        }
                    }
                }
            }
        }
    }
}
