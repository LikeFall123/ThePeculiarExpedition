package map;

public class RandomNumber {

    public static int randomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
