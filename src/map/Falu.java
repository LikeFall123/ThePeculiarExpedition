package map;

import objects.Item;
import objects.foods.Gyumolcs;
import objects.foods.Hus;
import objects.items.Faklya;
import objects.items.Kotel;
import team.characters.*;
import team.characters.Character;
import team.slots.Slot;

import static map.RandomNumber.randomNumber;

public class Falu extends Map{

    private Slot[] forSale;
    private Character[] charSale;

    public Slot[] getForSale() {
        return forSale;
    }

    public Character[] getCharSale(){
        return charSale;
    }



    public Falu() {
        super("/resources/falu" ,0);
        forSale = new Slot[20];
        charSale = new Character[7];
        int r = randomNumber(1,7);
        for(int i = 0; i<r;i++){
            elraktaroz(new Kotel());
        }
        r = randomNumber(1,7);
        for(int i = 0; i<r;i++){
            elraktaroz(new Faklya());
        }
        r = randomNumber(1,7);
        for(int i = 0; i<r;i++){
            elraktaroz(new Gyumolcs());
        }
        r = randomNumber(1,7);
        for(int i = 0; i<r;i++){
            elraktaroz(new Hus());
        }
        addCharacter(new Kereskedo());
        addCharacter(new Katona());
        addCharacter(new Szamar());
        r = randomNumber(0,100);
        if(r<20){
            addCharacter(new Felderito());
        }
        r = randomNumber(0,100);
        if(r<20){
            addCharacter(new Saman());
        }
        r = randomNumber(0,100);
        if(r<20){
            addCharacter(new Bolcs());
        }
    }

    public void elraktaroz(Item item){
        for(int i=0;i<20;i++){
            if(forSale[i]==null){
                forSale[i] = new Slot(item);
                break;
            }else{
                if(forSale[i].addItem(item)==false){
                    continue;
                }else {
                    break;
                }
            }
        }
    }

    public void consumeItem(Item item){
        for (int i = 0; i < 20; i++) {
            if (forSale[i] != null) {
                if (forSale[i].getSlots()[0].getClass() == item.getClass()) {
                    if(forSale[i].getSlots()[1]==null){
                        forSale[i]=null;
                    }else{
                        for(int j=0;j<6;j++){
                            if(forSale[i].getSlots()[j]!=null && forSale[i].getSlots()[j+1]==null){
                                forSale[i].getSlots()[j]=null;
                            }
                        }
                    }
                }
            }
        }
    }

    public void addCharacter(Character character){
        for(int i=0;i<7;i++){
            if(charSale[i]==null){
                charSale[i]=character;
                break;
            }
        }
    }

    public void buyCharacter(Character character){
        for(int i=0; i<7; i++){
            if(charSale[i].getClass()==character.getClass()){
                charSale[i]=null;
                break;
            }
        }
    }

}
