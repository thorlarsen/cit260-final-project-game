package AdventueGame;

import java.util.Random;
import java.util.Scanner;

public class GameMethods {

    public static void gameDescription () {
        System.out.println("Welcome to the Stupendous Cave Adventure!\n");
        System.out.println("You have long heard rumors of something evil originating from a cave ");
        System.out.println("a few hours walk from your home. It caused the disappearance of the ");
        System.out.println("last few remaining dragons not too long ago. ");
        System.out.println("\nNow an unreliable source is reporting that one last dragon is held ");
        System.out.println("prisoner in this cave, and it is promising wealth and power to anyone ");
        System.out.println("who is able to free it. That person need only challenge the ");
        System.out.println("creatures guarding the dragon. You have some experience with fighting so ");
        System.out.println("how bad can it be?");
        System.out.println("\nYou take up your sword, a shield, a lantern and you decide to go. The ");
        System.out.println("report also said a wizard and a cave nymph will aid you on your quest.");
        System.out.println("\nNow take courage and be on your way!");
    }

    /**
     * The RandomNum methos
     * returns a random integer between 1 and howManyNumbers
     * @param howManyNumbers as int
     * @return randomly generated number as int
     */
    public static int RandomNum( int howManyNumbers) {

        Random randomNum = new Random();
        return randomNum.nextInt(howManyNumbers) + 1;

    }

    /**
     * The roomDescription method
     * prints a description of each room as the player enters it.
     * It also handles the interactions between the player and
     * the two helpers.
     * @param roomNumber
     * @param npName
     * @param isDefeated
     */
    public static void roomDescription ( int roomNumber, String npName, boolean isDefeated ) {
        switch (roomNumber) {
            case 1:
                System.out.println("\nYou step into the cave. The air is damp and smells musty.");
                System.out.println("\nYou see a " + npName + " in here.");
                System.out.println("\nThe " + npName + " speaks:");
                System.out.println("Take heed now! You will face the four beasts guarding the dragon.");
                System.out.println("You must defeat each one in turn. If you are weary after fighting, ");
                System.out.println("return to me and I will restore you. You will also meet the nymph of ");
                System.out.println("this cave. She will give you the spell that releases the dragon.");
                System.out.println("Now, be off!");
                break;
            case 2:
                System.out.println("\nStalactities and stalagmites make creepy shadows on the walls.");
                System.out.println("But what would you do without a lantern?");
                if(!isDefeated)
                    System.out.println("\nYou see a " + npName + " in here.");
                else
                    System.out.println("\nYou see an unconscious " + npName + " in here. Maybe it's dead?");
                break;
            case 3:
                System.out.println("\nThe temperature drops, but it is so humid in here you can't tell.");
                if(!isDefeated)
                    System.out.println("\nYou see a " + npName + " in here.");
                else
                    System.out.println("\nYou see an unconscious " + npName + " in here. Maybe it's dead?");
                break;
            case 4:
                System.out.println("\nThere is a giant rock formation in here. It looks like a throne");
                System.out.println("A light appears on this throne and gets brighter. It has a human form.");
                System.out.println("\nYou see a " + npName + " in here.");
                System.out.println("The " + npName + " speaks:");
                System.out.println("You are almost there. But the last two guards are the toughest.");
                System.out.println("If you grow weary you may return to me and I will restore you.");
                System.out.println("The dragon is bound with magic chains. When you see him, say this");
                System.out.println("spell just like this:");
                System.out.println("\nLIGHT ALWAYS DEFEATS DARKNESS");
                System.out.println("\nGo now, and don't give in to fear.");
                break;
            case 5:
                System.out.println("\nLots of nooks and crannies in this room. What's hiding in them?");
                if(!isDefeated)
                    System.out.println("\nYou see a " + npName + " in here.");
                else
                    System.out.println("\nYou see an unconscious " + npName + " in here. Maybe it's dead?");
                break;
            case 6:
                System.out.println("\nYou are accosted by the worst BO you've ever smelled!");
                if(!isDefeated)
                    System.out.println("\nYou see a " + npName + " in here.");
                else
                    System.out.println("\nYou see an unconscious " + npName + " in here. Maybe it's dead?");
                break;
            case 7:
                System.out.println("\nThere is a distinct smell of brimstone in here. Could this be it?");
                System.out.println("\nYou see a " + npName + " in here.");
                break;
            default:
                System.out.println("You stand in front of the cave. It is not inviting.");
                break;
        }
    }

    /**
     * The endGameMethod
     * handles the final stages of the game once the goal is reached.
     * @param name
     */
    public static void endGame(String name) {
        Scanner input = new Scanner(System.in);
        System.out.println("The dragon is chained to the floor and walls of the cave.");
        System.out.println("Do you remember the spell that will free the dragon?");
        String spell = input.nextLine();
        String correctSpell = "LIGHT ALWAYS DEFEATS DARKNESS";
        while(!spell.equals(correctSpell)) {
            System.out.println("That's not quite it. Try again. The dragon is getting anxious.");
            spell = input.nextLine();
        }
        System.out.println("\nLight flashes and thunder crashes!");
        System.out.println("\nYou are back in front of your house. The dragon hovers above you. ");
        System.out.println("A large chest is in front of you. Don't ask how. It's magic after all.");
        System.out.println("The dragon speaks:");
        System.out.println("Thank you, " + name + ", for freeing me from that cave. The treasure before you is from ");
        System.out.println("my horde and should more than compensate you. But keep a watchful eye out. ");
        System.out.println("You only stunned the power that held me; it has not gone away. I may ask ");
        System.out.println("for you help again in the near future.");
        System.out.println("\n---GAME OVER---");
        input.close();

    }
}
