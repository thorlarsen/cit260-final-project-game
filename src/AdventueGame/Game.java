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
            ArrayList<Character> nonPlayers = new ArrayList<>();
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
                nonPlayers.add(new NonPlayer(hitPoints, attackPoints, helper));
                nonPlayers.get(i).setName(name);
                nonPlayers.get(i).setRoomNumber(i);
                helper = false;
            } /* else {
                gameRestore();
            } */

            roomDescription(0, nonPlayers.get(0).getName());

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
                int newRoomNumber = player1.getRoomNumber();
                roomDescription(player1.getRoomNumber(), nonPlayers.get(newRoomNumber).getName());
                //Interact with non player
                    // if helper then no fight - hint is part of the room description
                        //reset hp

                   // if !helper then fight


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

        public static int random ( int howManyNumbers){

            Random randomNum = new Random(howManyNumbers);
            return randomNum.nextInt();

        }

        public static void fight () {
            System.out.println("Fight method");
        }

        public static void roomDescription ( int roomNumber, String npName ) {
            switch (roomNumber) {
                case 1:
                    System.out.println("You step into the cave. It feels damp and smells musty.");
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