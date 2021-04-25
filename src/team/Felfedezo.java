package team;

import objects.Food;
import objects.Item;
import objects.foods.Kabitoszer;
import objects.foods.Whiskey;
import team.characters.Character;
import team.characters.*;
import team.slots.Slot;

import static map.RandomNumber.randomNumber;

/**
 * Felfedezo osztaly. A jatekost reprezentalja
 */


public class Felfedezo {

    protected int arany;
    protected double energia;
    protected int mozgas;
    protected int hirnev;
    protected boolean alkoholista;
    protected int hanyadikWhisky;
    protected int hanyadikKabszi;
    protected boolean fuggo;
    protected Slot[] inventory = new Slot[20];
    protected Character[] teammates = new Character[3];
    protected Rival[] rivals = new Rival[4];

    public Felfedezo() {
        this.arany = 250;
        this.energia = 100;
        this.mozgas = 100; //%-ban merve
        this.hirnev = 0;
        this.alkoholista = false;
        this.fuggo = false;
        for (int i = 0; i < 4; i++) {
            String[] nevek = {"Szándokán", "Winnetou", "Dzseszperó", "Te"};
            Rival rival = new Rival(nevek[i], 0);
            rivals[i] = rival;
        }
        rivals[3].setHirnev(this.hirnev);
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

    public void setEnergia(double energia) {
        if (energia > 100) {
            this.energia = 100;
        } else if (energia <= 0) {
            this.energia = 0;
        } else {
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

    public void setAlkoholista(boolean alkoholista) {
        this.alkoholista = alkoholista;
    }

    public boolean isFuggo() {
        return fuggo;
    }

    public void setFuggo(boolean fuggo) {
        this.fuggo = fuggo;
    }


    /**
     * uj karaktert vesz fel
     * @param character karakter fajta
     */
    public void addCharacter(Character character) {
        for (int i = 0; i < 3; i++) {
            if (teammates[i] == null) {
                teammates[i] = character;
                this.mozgas += 15;
                break;
            }
        }
    }

    /**
     * karkater torles index alapjan
     * @param index index
     */
    public void removeCharacter(int index) {
        teammates[index] = null;
        this.mozgas -= 15;
    }

    /**
     * kitorli az utolso maradt karatert, mert csak akkor hivodik meg ha mar csak 1 maradt
     */
    public void removeCharacter() {
        for (int i = 0; i < 3; i++) {
            if (teammates[i] != null) {
                teammates[i] = null;
                this.mozgas -= 15;
                break;
            }
        }
    }

    /**
     * megszamolja hanyan tars van
     * @return hany van
     */
    public int countTeam() {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            if (teammates[i] != null) {
                k++;
            }
        }
        return k;
    }

    /**
     * megszamolja hany slot van
     * @return hany slot
     */
    public int countSlots() {
        int k = 0;
        for (int i = 0; i < 20; i++) {
            if (inventory[i] != null) {
                k++;
            }
        }
        return k;
    }

    /**
     * uj slotot ad hozza
     * @param item item tipus
     */
    public void addSlot(Item item) {
        for (int i = 0; i < 20; i++) {
            if (inventory[i] == null) {
                inventory[i] = new Slot(item);
                break;
            } else {
                if (!inventory[i].addItem(item)) {
                    continue;
                } else {
                    break;
                }
            }
        }
        //ha van szamar, akkor a 8 helyett (+2 slot) 10 lehet
        int max = 8;
        if (this.containsSzamar()) {
            max = 10;
        }
        if (countSlots() > max) {
            this.setMozgas(100 + (countSlots() - max) * 20);
        }
    }

    /**
     * kiirja a tarsakat
     */
    public void printTeam() {
        for (int i = 0; i < 3; i++) {
            if (teammates[i] != null) {
                System.out.println(teammates[i].getFajta());
            }
        }
    }


    /**
     * kezeli ha fuggo a jatekos vagy valamelyik tarsa
     * @param food kaja
     * @param charInd melyik indexrol
     */
    public void fuggoseg(Food food, int charInd) {
        if (food instanceof Whiskey) {
            hanyadikWhisky++;
            if (hanyadikWhisky >= 2) {
                int r = randomNumber(0, 100);
                if (r < 15) {
                    if (charInd < 0) {
                        this.setAlkoholista(true);
                    } else {
                        this.teammates[charInd].setFuggo(true);
                    }
                }
            }
        }
        if (food instanceof Kabitoszer) {
            hanyadikKabszi++;
            if (hanyadikKabszi >= 2) {
                int r = randomNumber(0, 100);
                if (r < 15) {
                    if (charInd < 0) {
                        this.setFuggo(true);
                    } else {
                        this.teammates[charInd].setFuggo(true);
                    }
                }
            }
        }
        if (!(food instanceof Kabitoszer) && !(food instanceof Whiskey)) {
            hanyadikKabszi = 0;
            hanyadikWhisky = 0;
            if (charInd < 0) {
                this.setFuggo(false);
                this.setAlkoholista(false);
            } else {
                this.teammates[charInd].setFuggo(false);
                this.teammates[charInd].setAlkoholista(false);
            }
        }
    }


    /**
     * elfogyasztja a kivalasztott etelt
     * @param food kaja
     * @param foodIndex indexrol
     * @param charIndex melyik karakter egye meg
     */
    public void consumeFood(Food food, int foodIndex, int charIndex) {
        if (inventory[foodIndex] != null) {
            if (inventory[foodIndex].getSlots()[1] == null) {
                fuggoseg(food, charIndex);
                inventory[foodIndex] = null;
                setEnergia(this.energia + food.getBonus());
                //ha van szamar, akkor a 8 helyett (+2 slot) 10 lehet
                int max = 8;
                if (this.containsSzamar()) {
                    max = 10;
                }
                if (countSlots() > max) {
                    this.setMozgas(this.getMozgas() - 20);
                }
            } else if (inventory[foodIndex].getSlots()[6] != null) {
                inventory[foodIndex].getSlots()[6] = null;
            } else {
                for (int j = 0; j < 6; j++) {
                    if (inventory[foodIndex].getSlots()[j] != null && inventory[foodIndex].getSlots()[j + 1] == null) {
                        fuggoseg(food, charIndex);
                        inventory[foodIndex].getSlots()[j] = null;
                        setEnergia(this.energia + food.getBonus());
                    }
                }
            }
        }
    }

    /**
     * elhasznalja a kivalasztott eszkozt
     * @param ind index
     */
    public void consumeItem(int ind) {
        if (inventory[ind] != null) {
            if (inventory[ind].getSlots()[1] == null) {
                inventory[ind] = null;
                //ha van szamar, akkor a 8 helyett (+2 slot) 10 lehet
                int max = 8;
                if (this.containsSzamar()) {
                    max = 10;
                }
                if (countSlots() > max) {
                    this.setMozgas(this.getMozgas() - 20);
                }
            } else if (inventory[ind].getSlots()[6] != null) {
                inventory[ind].getSlots()[6] = null;
            } else {
                for (int j = 0; j < 6; j++) {
                    if (inventory[ind].getSlots()[j] != null && inventory[ind].getSlots()[j + 1] == null) {
                        inventory[ind].getSlots()[j] = null;
                    }
                }
            }
        }
    }


    /**
     * csapatban van e katona
     * @return van e
     */
    public boolean containsKatona() {
        for (int i = 0; i < 3; i++) {
            if (teammates[i] instanceof Katona) {
                return true;
            }
        }
        return false;
    }

    /**
     * csapatban van e kereskedo
     * @return van e
     */
    public boolean containsKereskedo() {
        for (int i = 0; i < 3; i++) {
            if (teammates[i] instanceof Kereskedo) {
                return true;
            }
        }
        return false;
    }

    /**
     * csapatban van e szamar
     * @return van e
     */
    public boolean containsSzamar() {
        for (int i = 0; i < 3; i++) {
            if (teammates[i] instanceof Szamar) {
                return true;
            }
        }
        return false;
    }

    /**
     * csapatban van e felderito
     * @return van e
     */
    public boolean containsFelderito() {
        for (int i = 0; i < 3; i++) {
            if (teammates[i] instanceof Felderito) {
                return true;
            }
        }
        return false;
    }

    /**
     * csapatban van e saman
     * @return van e
     */
    public boolean containsSaman() {
        for (int i = 0; i < 3; i++) {
            if (teammates[i] instanceof Saman) {
                return true;
            }
        }
        return false;
    }

    /**
     * rendezi a rivalisokak hirnev mennyisege szerint
     */
    public void sortRivals() {
        for (int i = 0; i < 3; i++) {
            int index = i;
            for (int j = i + 1; j < 4; j++) {
                if (rivals[j].getHirnev() > rivals[index].getHirnev()) {
                    index = j;//searching for lowest index
                }
            }
            Rival smallerNumber = rivals[index];
            rivals[index] = rivals[i];
            rivals[i] = smallerNumber;
        }
    }

    /**
     * random megnoveli a rivalisok hirnevet
     */
    public void incrementRivals() {
        for (int i = 0; i < 4; i++) {
            int r = randomNumber(0, 100);
            if (!rivals[i].getNev().equals("Te")) {
                rivals[i].setHirnev(rivals[i].getHirnev() + 1000 + r);
            } else {
                rivals[i].setHirnev(this.hirnev);
            }
        }
        sortRivals();
    }

    /**
     * kiirja a rivalisok adatait
     * @return rivalisok string
     */
    public String printRivals() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            s.append(i + 1).append(". ").append(rivals[i].getNev()).append(":").append(rivals[i].getHirnev()).append("\n");
        }
        return s.toString();
    }

    /**
     * jatek vegen visszaadja a legjobb felfedezot
     * @return legjobb felfedezo
     */
    public String legjobbRival() {
        sortRivals();
        return rivals[0].getNev() + " : " + rivals[0].getHirnev();
    }
}
