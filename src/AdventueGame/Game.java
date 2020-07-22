package AdventueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        gameDescription();
        boolean newGame = true;

        // start new game or load saved game
        if (newGame) {
            Player player1 = new Player();
            Scanner input = new Scanner(System.in);
            System.out.print("What is your name?");
            player1.setName(input.nextLine());
            System.out.println("What race will you be? human, dwarf, or elf? ");
            String race = input.nextLine();
            if (race.equals("human")) {
                player1.setOriginalHitPoints(30);
                player1.setAttackPoints(10);
            } else if (race.equals("dwarf")) {
                player1.setOriginalHitPoints(35);
                player1.setAttackPoints(15);
            } else if (race.equals("elf")) {
                player1.setOriginalHitPoints(35);
                player1.setAttackPoints(12);
            }
            player1.setRoomNumber(0);
            ArrayList<NonPlayer> nPC = new ArrayList<>();
            //mapInitialize
            int hitPoints = 0, attackPoints = 0;
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
                        hitPoints = 20;
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

            roomDescription(0, nPC.get(0).getName());

            do {
                //Move character
                boolean motion = true;
                do {
                    //Scanner keyboard = new Scanner(System.in);
                    System.out.println("What would you like to do?");
                    System.out.print("move (f)orward, move (b)ackward, (s)ave game, or e(x)it: ");
                    char decision = input.next().charAt(0);
                    switch (decision) {
                        case 'f':
                            player1.move(1);
                            break;
                        case 'b':
                            player1.move(-1);
                            break;
                        case 's':
                            gameSave();
                            break;
                        case 'x':
                            input.close();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Please enter f, b, s or x.");
                            motion = false;
                            break;
                    }
                } while (!motion);
                int roomNum = player1.getRoomNumber();
                roomDescription(player1.getRoomNumber(), nPC.get(roomNum).getName());

                //A helper will restore HP to the player, if the player needs it.
                if(nPC.get(roomNum).isHelper() && player1.getHitPoints() < player1.getOriginalHitPoints()) {
                    player1.setHitPoints(player1.getOriginalHitPoints());
                    System.out.println("Your HP are restored to " + player1.getHitPoints());
                } else {
                    boolean fight = !nPC.get(roomNum).isHelper();
                    while(fight && player1.getHitPoints() >= 0 && !nPC.get(roomNum).isDefeated()) {
                        System.out.print("Do you want to (f)ight or (r)un? ");
                        char decision = input.next().charAt(0);
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
            System.out.println("This will be the game description.");
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

        public static void roomDescription ( int roomNumber, String npName ) {
            switch (roomNumber) {
                case 1:
                    System.out.println("You step into the cave. The air is damp and smells musty.");
                    System.out.println("You see a " + npName + " in here.");
                    break;
                case 2:
                    System.out.println("Description for room number 2");
                    System.out.println("You see a " + npName + " in here.");
                    break;
                case 3:
                    System.out.println("Description for room number 3");
                    System.out.println("You see a " + npName + " in here.");
                    break;
                case 4:
                    System.out.println("Description for room number 4");
                    System.out.println("You see a " + npName + " in here.");
                    break;
                case 5:
                    System.out.println("Description for room number 5");
                    System.out.println("You see a " + npName + " in here.");
                    break;
                case 6:
                    System.out.println("Description for room number 6");
                    System.out.println("You see a " + npName + " in here.");
                    break;
                case 7:
                    System.out.println("Description for room number 7");
                    System.out.println("You see a " + npName + " in here.");
                    break;
                default:
                    System.out.println("Description for outside of cave");
                    break;
            }
        }

    }