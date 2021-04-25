package map;

import team.characters.Character;
import javax.swing.*;
import java.awt.*;

/**
 * Altalanos map elem, az osszes tobbi ebbol oroklodik
 */

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


    public Image getImg() {
        return img;
    }

    public Image getImg_f(){ return img_f;}

    public int getKoltseg(){ return koltseg; }

    /**
     * Ezt csak overridolja egy gyerekosztaly
     * @param ind
     */

    public void consumeItem(int ind) { }

    /**
     * Ezt csak overridolja egy gyerekosztaly
     * @return
     */

    public Object[] getForSale() {
        return null;
    }

    /**
     * Ezt csak overridolja egy gyerekosztaly
     * @return
     */
    public Object[] getCharSale() {
        return null;
    }

    /**
     * Ezt csak overridolja egy gyerekosztaly
     * @param s
     */
    public void buyCharacter(Character s) { }

    /**
     * Ezt csak overridolja egy gyerekosztaly
     * @return
     */
    public boolean isMegtalalKincs() {
        return false;
    }

    /**
     * Ezt csak overridolja egy gyerekosztaly
     */
    public void setMegtalalKincs() { }
}
