package map;

/**
 * Ez az osztaly egy random szamot general
 */

public class RandomNumber {

    public static int randomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
