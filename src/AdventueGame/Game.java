package AdventueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        gameDescription();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Are you starting a (n)ew game or (c)ontinuing a saved game?");
        char decision = keyboard.next().charAt(0);
        while(decision != 'n' && decision != 'c') {
            System.out.println("Please type 'n' or 'c'.");
            decision = keyboard.next().charAt(0);
        }

        // start new game and initialize the player and non players
        if (decision == 'n') {
            Player player1 = new Player();
            System.out.print("What is your name?");
            String player1Name = keyboard.nextLine();
            player1.setName(player1Name);
            System.out.println("\nNext choose your race. Humans have fewer hitpoints than the others, ");
            System.out.println("but they have the strongest attack. Dwarves have more hitpoints than ");
            System.out.println("humans, but their attacks are weaker. Elves have the most hitpoints; ");
            System.out.println("their attack is stronger than dwarves but weaker than humans.");
            System.out.println("\nWhat race will you be? (h)uman, (d)warf, or (e)lf? ");
            char race = keyboard.next().charAt(0);
            while(race != 'h' && race  != 'd' && race != 'e'){
                System.out.println("Please enter 'h', 'd', or 'e'.");
                race = keyboard.next().charAt(0);
            }
            switch (race) {
                case 'h':
                    player1.setRace("human");
                    break;
                case 'd':
                    player1.setRace("dwarf");
                    break;
                case 'e':
                    player1.setRace("elf");
                    break;
            }
            player1.setRoomNumber(0);
            ArrayList<NonPlayer> nPC = new ArrayList<>();
            //mapInitialize
            int hitPoints, attackPoints;
            String name;
            boolean helper = false;
            for (int i = 0; i < 8; i++) {
                switch (i) {
                    case 1:
                        name = "Wizard";
                        hitPoints = 100;
                        attackPoints = 0;
                        helper = true;
                        break;
                    case 2:
                        name = "Thief";
                        hitPoints = 25;
                        attackPoints = 7;
                        break;
                    case 3:
                        name = "Giant Snake";
                        hitPoints = 20;
                        attackPoints = 5;
                        break;
                    case 4:
                        name = "Nymph";
                        hitPoints = 100;
                        attackPoints = 0;
                        helper = true;
                        break;
                    case 5:
                        name = "Wolf";
                        hitPoints = 30;
                        attackPoints = 5;
                        break;
                    case 6:
                        name = "Troll";
                        hitPoints = 40;
                        attackPoints = 12;
                        break;
                    case 7:
                        name = "Dragon";
                        hitPoints = 100;
                        attackPoints = 0;
                        helper = true;
                        break;
                    default:
                        name = "dummy";
                        hitPoints = 0;
                        attackPoints = 0;
                        helper = true;
                        break;
                }
                nPC.add(new NonPlayer(hitPoints, attackPoints, helper));
                nPC.get(i).setName(name);
                nPC.get(i).setRoomNumber(i);
                helper = false;
            } /* else {
                gameRestore();
            } */

            roomDescription(0, nPC.get(0).getName(), true);

            do {
                //Move character
                boolean motion = true;
                do {
                    System.out.println("What would you like to do?");
                    System.out.print("move (f)orward, move (b)ackward, (s)ave game, or e(x)it: ");
                    decision = keyboard.next().charAt(0);
                    switch (decision) {
                        case 'f':
                            player1.move(1);
                            break;
                        case 'b':
                            player1.move(-1);
                            break;
                        case 's':
                            gameSave();
                            motion = false;
                            break;
                        case 'x':
                            keyboard.close();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Please enter f, b, s or x.");
                            motion = false;
                            break;
                    }
                } while (!motion);
                int roomNum = player1.getRoomNumber();
                roomDescription(player1.getRoomNumber(), nPC.get(roomNum).getName(), nPC.get(roomNum).isDefeated());

                //A helper will restore HP to the player, if the player needs it.
                if(nPC.get(roomNum).isHelper() && player1.getHitPoints() < player1.getOriginalHitPoints()) {
                    player1.setHitPoints(player1.getOriginalHitPoints());
                    System.out.println("Your HP are restored to " + player1.getHitPoints());
                } else {
                    boolean fight = !nPC.get(roomNum).isHelper();
                    while(fight && player1.getHitPoints() >= 0 && !nPC.get(roomNum).isDefeated()) {
                        System.out.println("\nYou have " + player1.getHitPoints() + " hitpoints.");
                        System.out.print("Do you want to (f)ight or (r)un? ");
                        decision = keyboard.next().charAt(0);
                        while(decision != 'f' && decision != 'r') {
                            System.out.println("Please enter 'f' or 'r'.");
                            decision = keyboard.next().charAt(0);
                        }
                        if(decision == 'r') {
                            player1.move(-1);
                            fight = false;
                        } else {
                            //player fight round
                            System.out.println("You attack first.");
                            System.out.println("The " + nPC.get(roomNum).getName() + " has " + nPC.get(roomNum).getHitPoints() + " HP.");
                            if (RandomNum(20) == 20) {
                                System.out.println("You get a knockout hit!!");
                                nPC.get(roomNum).setDefeated();
                                System.out.println("You defeated the " + nPC.get(roomNum).getName());
                            } else {
                                System.out.println("You hit and do " + player1.getAttackPoints() + " points of damage");
                                nPC.get(roomNum).setHitPoints(nPC.get(roomNum).getHitPoints() - player1.getAttackPoints());
                                if(nPC.get(roomNum).getHitPoints() <= 0) {
                                    nPC.get(roomNum).setDefeated();
                                    System.out.println("You defeated the " + nPC.get(roomNum).getName());
                                }
                            }
                            //opponent fight round
                            if(!nPC.get(roomNum).isDefeated()) {
                                System.out.println("The " + nPC.get(roomNum).getName() + " attacks");
                                if (RandomNum(3) == 3) {
                                    System.out.println("and misses! No damage.");
                                } else {
                                    System.out.println("and does " + nPC.get(roomNum).getAttackPoints() + " points of damage.");
                                    player1.setHitPoints(player1.getHitPoints() - nPC.get(roomNum).getAttackPoints());
                                    if(player1.getHitPoints() <= 0) {
                                        player1.move(-1);
                                        System.out.println("You can no longer fight and you must retreat.");
                                    }
                                }
                            }
                        }
                    }

                }

            } while (true);

        }


    }

    /* This method will print the game description and instructions.
     * The goal of the game is to face challenges and work toward
     * the goal of freeing the dragon from capitivity. The player
     * will receive hints from helpers */
    public static void gameDescription () {
            System.out.println("Welcome to the Stupendous Cave Adventure!\n");
            System.out.println("You have long heard rumors the something evil originating from a cave ");
            System.out.println("nearby your home. It caused the disappearance of the ");
            System.out.println("last few remaining dragons not too long ago. ");
            System.out.println("\nNow an unreliable source is reporting that one last dragon is held ");
            System.out.println("prisoner in this cave, and it is promising wealth and power to anyone ");
            System.out.println("who is able to free it. That person need only challenge the ");
            System.out.println("creatures guarding the dragon. You have some experience with fighting so ");
            System.out.println("how bad can it be?");
            System.out.println("\nYou take up your sword, a shield, a lantern and you decided to go on ");
            System.out.println("this quest. A wizard and a cave nymph will aid you on your quest.");
            System.out.println("\nNow take courage and be on your way!");
        }


        public static void gameSave () {
            /* This will create a text file that will contain
             * text representation of the game's current state.
             */
            System.out.println("Save game placeholder.");
        }

        public static void gameRestore () {
            /*This will read in the text from the save file and
             * it will initialize the game instead of gameInitialize.
             */
            System.out.println("Restore game placeholder.");
        }

        public static int RandomNum( int howManyNumbers) {

            Random randomNum = new Random();
            return randomNum.nextInt(howManyNumbers) + 1;

        }

        public static void fight () {
            System.out.println("Fight method");
        }

        public static void roomDescription ( int roomNumber, String npName, boolean isDefeated ) {
            switch (roomNumber) {
                case 1:
                    System.out.println("You step into the cave. The air is damp and smells musty.");
                    System.out.println("You see a " + npName + " in here.");
                    System.out.println("The " + npName + " speaks:");
                    System.out.println("Take heed now! You will face the four beasts guarding the dragon.");
                    System.out.println("You must defeat each one in turn. If you are weary after fighting, ");
                    System.out.println("return to me and I will restore you. You will also meet the nymph of ");
                    System.out.println("this cave. She will give you the spell that releases the dragon.");
                    System.out.println("Now, be off!");
                    break;
                case 2:
                    System.out.println("Stalactities and stalagmites make creepy shadows on the walls.");
                    System.out.println("But what would you do without a lantern?");
                    if(!isDefeated)
                        System.out.println("You see a " + npName + " in here.");
                    else
                        System.out.println("You see an unconscious " + npName + " in here. Maybe it's dead?");
                    break;
                case 3:
                    System.out.println("The temperature drops, but it is so humid in here you can't tell.");
                    if(!isDefeated)
                        System.out.println("You see a " + npName + " in here.");
                    else
                        System.out.println("You see an unconscious " + npName + " in here. Maybe it's dead?");
                    break;
                case 4:
                    System.out.println("There is a giant rock formation in here. It looks like a throne");
                    System.out.println("A light appears on this throne and gets brighter. It has a human form.");
                    System.out.println("You see a " + npName + " in here.");
                    System.out.println("The " + npName + " speaks:");
                    System.out.println("You are almost there. But the last two guards are the toughest.");
                    System.out.println("After fighting them, you may return to me and I will restore you.");
                    System.out.println("The dragon is bound with magic chains. When you see him, say this");
                    System.out.println("spell just like this:");
                    System.out.println("\nLIGHT ALWAYS DEFEATS DARKNESS");
                    System.out.println("\nGo now, and don't give in to fear.");
                    break;
                case 5:
                    System.out.println("Lots of nooks and crannies in this room. What's hiding in them?");
                    if(!isDefeated)
                        System.out.println("You see a " + npName + " in here.");
                    else
                        System.out.println("You see an unconscious " + npName + " in here. Maybe it's dead?");
                    break;
                case 6:
                    System.out.println("You are accosted by the worst BO you've ever smelled!");
                    if(!isDefeated)
                        System.out.println("You see a " + npName + " in here.");
                    else
                        System.out.println("You see an unconscious " + npName + " in here. Maybe it's dead?");
                    break;
                case 7:
                    System.out.println("There is a distinct smell of brimstone in here. Could this be it?");
                    System.out.println("You see a " + npName + " in here.");
                    break;
                default:
                    System.out.println("You stand in front of the cave. It is not inviting.");
                    break;
            }
        }

    }