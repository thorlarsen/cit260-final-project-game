package AdventueGame;

import java.util.Random;

public class randomNumber {

    public static void main(String[] args) {

        System.out.println("Generates a random integer between 1-20:");
        Random randomGenerator=new Random();
        System.out.println(randomGenerator.nextInt(20) + 1);
    }
}
