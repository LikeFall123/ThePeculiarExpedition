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

/**
 * Falu map elem
 */

public class Falu extends Map{


    private Slot[] forSale;
    private Character[] charSale;

    public Slot[] getForSale() {
        return forSale;
    }

    public Character[] getCharSale(){
        return charSale;
    }


    /**
     * Konstruktor random cuccokat elraktaroz, meg par karaktert
     */
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

    /**
     * Berakja a falu inventoryjaba
     * @param item elraktrozando elem
     */
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

    /**
     * cuccot vesz
     * @param ind indexu elemet megveszi a jatekos
     */
    public void consumeItem(int ind) {
        if (forSale[ind] != null) {
            if (forSale[ind].getSlots()[1] == null) {
                forSale[ind] = null;
            } else if (forSale[ind].getSlots()[6] != null) {
                forSale[ind].getSlots()[6] = null;
            } else {
                for (int j = 0; j < 6; j++) {
                    if (forSale[ind].getSlots()[j] != null && forSale[ind].getSlots()[j + 1] == null) {
                        forSale[ind].getSlots()[j] = null;
                    }
                }
            }
        }
    }

    /**
     * karakter hozzaadas
     * @param character
     */
    public void addCharacter(Character character){
        for(int i=0;i<7;i++){
            if(charSale[i]==null){
                charSale[i]=character;
                break;
            }
        }
    }

    /**
     * megveszi a karaktert a jatekos
     * @param character
     */
    public void buyCharacter(Character character){
        for(int i=0; i<7; i++){
            if(charSale[i].getClass()==character.getClass()){
                charSale[i]=null;
                break;
            }
        }
    }

}
