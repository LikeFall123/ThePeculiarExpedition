package team;

import objects.Food;
import objects.Item;
import objects.foods.Kabitoszer;
import objects.foods.Whiskey;
import team.characters.Character;
import team.characters.*;
import team.slots.Slot;

import static map.RandomNumber.randomNumber;


public class Felfedezo {

    protected int arany;
    protected double energia;
    protected int mozgas;
    protected int hirnev;
    protected boolean alkoholista;
    protected boolean fuggo;
    protected Slot[] inventory = new Slot[20];
    protected Character[] teammates = new Character[3];
    //protected Rival[] rivals = new Rival[3];
    private String[] nevek = {"Szándokán","Winnetou","Jónás Indián"};

    public Felfedezo() {
        this.arany = 250;
        this.energia = 100;
        this.mozgas = 100; //%-ban merve
        this.hirnev=0;
        this.alkoholista=false;
        this.fuggo=false;
        int r = randomNumber(0,100);
//        for(int i=0;i<3;i++){
//            rivals[i].setNev(nevek[i]);
//            rivals[i].setHirnev(1000+r);
//        }
    }

    public int getArany() {
        return arany;
    }

    public void setArany(int arany) {
        this.arany = arany;
    }

    public double getEnergia() {
        return energia;
    }

    public void setEnergia(double energia){
        if(energia>100){
            this.energia=100;
        }else if(energia<=0){
            this.energia=0;
        }else{
            this.energia = energia;
        }
    }

    public int getMozgas() {
        return mozgas;
    }

    public void setMozgas(int mozgas) {
        this.mozgas = mozgas;
    }

    public Slot[] getInventory() {
        return inventory;
    }

    public Character[] getTeammates() {
        return teammates;
    }

    public int getHirnev() {
        return hirnev;
    }

    public void setHirnev(int hirnev) {
        this.hirnev = hirnev;
    }

    public boolean isAlkoholista() {
        return alkoholista;
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

    public void addCharacter(Character character){
        for(int i=0;i<3;i++){
            if(teammates[i]==null){
                teammates[i]=character;
                this.mozgas+=15;
                break;
            }
        }
    }

    public void removeCharacter(Character character){ //torles charakter alapjan
        for(int i = 0; i<3;i++){
            if(teammates[i]==character){
                teammates[i]=null;
            }
        }
    }
    public void removeCharacter(int index){ //torles index alapjan
        teammates[index]=null;
    }

    public void removeCharacter(){ // kitorli az utolso maradt karatert, mert csak akkor hivodik meg ha mar csak 1 maradt
        for(int i = 0; i<3;i++){
            if(teammates[i]!=null){
                teammates[i]=null;
                break;
            }
        }
    }

    public int countTeam(){
        int k = 0;
        for(int i=0;i<3;i++){
            if(teammates[i]!=null){
                k++;
            }
        }
        return k;
    }

    public int countSlots(){
        int k=0;
        for(int i=0;i<20;i++){
            if(inventory[i]!=null){
                k++;
            }
        }
        return k;
    }

    public void addSlot(Item item){
        for(int i=0;i<20;i++){
            if(inventory[i]==null){
                inventory[i] = new Slot(item);
                break;
            }else{
                if(inventory[i].addItem(item)==false){
                    continue;
                }else {
                    break;
                }
            }
        }
        //ha van szamar, akkor a 8 helyett (+2 slot) 10 lehet
        int max = 8;
        if(this.containsSzamar()){
            max = 10;
        }
        if(countSlots()>8){
            this.setMozgas(100+(countSlots()-7)*20);
        }
    }

    public void printTeam(){
        for(int i=0;i<3;i++){
            if(teammates[i]!=null){
                System.out.println(teammates[i].getFajta());
            }
        }
    }

    public void printInventory(){
        for(int i=0;i<20;i++){
            int num = 0;
            if(inventory[i]!=null){
                for(int j=0;j<7;j++){
                    if(inventory[i].getSlots()[j]!=null){
                        num++;
                    }
                }
                System.out.println(inventory[i].getSlots()[0].getNev() + " " + num);
            }
        }
    }

    public void fuggoseg(Food food){
        if(food instanceof Whiskey){
            int r = randomNumber(0,100);
            if(r<15){
                this.setAlkoholista(true);
            }
        }
        if(food instanceof Kabitoszer){
            int r = randomNumber(0,100);
            if(r<15){
                this.setFuggo(true);
            }
        }
        if(!(food instanceof Kabitoszer) && !(food instanceof Whiskey)){
            this.setFuggo(false);
        }
    }

    public void consumeFood(Food food) {
        for (int i = 0; i < 20; i++) {
            if (inventory[i] != null) {
                if (inventory[i].getSlots()[0].getClass() == food.getClass()) {
                    if(inventory[i].getSlots()[1]==null){
                        fuggoseg(food);
                        inventory[i]=null;
                        setEnergia(this.energia+food.getBonus());
                    }else{
                        for(int j=0;j<6;j++){
                            if(inventory[i].getSlots()[j]!=null && inventory[i].getSlots()[j+1]==null){
                                fuggoseg(food);
                                inventory[i].getSlots()[j]=null;
                                setEnergia(this.energia+food.getBonus());
                            }
                        }
                    }
                }
            }
        }
        //ha van szamar, akkor a 8 helyett (+2 slot) 10 lehet
        int max = 8;
        if(this.containsSzamar()){
            max = 10;
        }
        if(countSlots()>8){
            this.setMozgas(100+(countSlots()-7)*20);
        }
    }

    public void consumeItem(Item item){
        for (int i = 0; i < 20; i++) {
            if (inventory[i] != null) {
                if (inventory[i].getSlots()[0].getClass() == item.getClass()) {
                    if(inventory[i].getSlots()[1]==null){
                        inventory[i]=null;
                        break;
                    }else{
                        for(int j=0;j<6;j++){
                            if(inventory[i].getSlots()[j]!=null && inventory[i].getSlots()[j+1]==null){
                                inventory[i].getSlots()[j]=null;
                            }
                        }
                    }
                }
            }
        }
        //ha van szamar, akkor a 8 helyett (+2 slot) 10 lehet
        int max = 8;
        if(this.containsSzamar()){
            max = 10;
        }
        if(countSlots()>8){
            this.setMozgas(100+(countSlots()-7)*20);
        }
    }

    public void consumeItem(int ind){
        inventory[ind]=null;
        if(countSlots()>7){
            this.setMozgas(100+(countSlots()-7)*20);
        }
    }

    public boolean containsKatona(){
        for(int i=0;i<3;i++){
            if(teammates[i] instanceof Katona){
                return true;
            }
        }
        return false;
    }
    public boolean containsKereskedo(){
        for(int i=0;i<3;i++){
            if(teammates[i] instanceof Kereskedo){
                return true;
            }
        }
        return false;
    }
    public boolean containsSzamar(){
        for(int i=0;i<3;i++){
            if(teammates[i] instanceof Szamar){
                return true;
            }
        }
        return false;
    }
    public boolean containsFelderito(){
        for(int i=0;i<3;i++){
            if(teammates[i] instanceof Felderito){
                return true;
            }
        }
        return false;
    }
    public boolean containsSaman(){
        for(int i=0;i<3;i++){
            if(teammates[i] instanceof Saman){
                return true;
            }
        }
        return false;
    }
}
