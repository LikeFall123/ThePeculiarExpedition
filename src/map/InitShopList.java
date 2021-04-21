package map;

import objects.Item;
import objects.foods.Csokolade;
import objects.foods.Gyumolcs;
import objects.foods.Hus;
import objects.foods.Whiskey;
import objects.items.Bozotvago;
import objects.items.Faklya;
import objects.items.Kotel;
import objects.items.Uveggolyo;
import team.characters.*;
import team.characters.Character;
import team.slots.Slot;

import static map.RandomNumber.randomNumber;

public class InitShopList{

    private Slot[] forSale;
    private Character[] charSale;

    public Slot[] getForSale() {
        return forSale;
    }

    public Character[] getCharSale(){
        return charSale;
    }



    public InitShopList() {
        forSale = new Slot[20];
        charSale = new Character[1];
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
        r = randomNumber(1,7);
        for(int i = 0; i<r;i++){
            elraktaroz(new Uveggolyo());
        }
        r = randomNumber(1,7);
        for(int i = 0; i<r;i++){
            elraktaroz(new Whiskey());
        }
        r = randomNumber(1,7);
        for(int i = 0; i<r;i++){
            elraktaroz(new Csokolade());
        }
        elraktaroz(new Bozotvago());
        r = randomNumber(0,100);
        if(0<=r && r<=33){
            addCharacter(new Kereskedo());
        }
        if(34<=r && r<=66){
            addCharacter(new Katona());
        }
        if(67<=r && r<=100){
            addCharacter(new Szamar());
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
