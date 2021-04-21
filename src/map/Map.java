package map;

import objects.Item;
import team.characters.Character;

import javax.swing.*;
import java.awt.*;


public class Map {
    protected String path;
    protected String path_f;
    protected Image img;
    protected Image img_f;
    protected int koltseg;

    public Map(String path, int koltseg) {
        this.path = path + ".jpg";
        this.path_f = path + "_f.jpg";
        this.koltseg = koltseg;
        img = new ImageIcon(this.getClass().getResource(this.path)).getImage();
        img_f = new ImageIcon(this.getClass().getResource(this.path_f)).getImage();
    }

    public String getPath() {
        return path;
    }

    public Image getImg() {
        return img;
    }

    public Image getImg_f(){ return img_f;}

    public int getKoltseg(){ return koltseg; }

    public void consumeItem(Item slot) {
    }

    public Object[] getForSale() {
        return null;
    }

    public Object[] getCharSale() {
        return null;
    }

    public void buyCharacter(Character s) {
    }

    public boolean isMegtalalKincs() {
        return false;
    }

    public void setMegtalalKincs() {

    }
}
