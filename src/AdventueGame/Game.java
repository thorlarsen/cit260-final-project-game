package AdventueGame;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static final String P1FILE = "p1-data.file";
    public static final String NPFILE = "np-data.file";

    public static void main(String[] args) {
        GameMethods.gameDescription();
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Are you starting a (n)ew game or (c)ontinuing a saved game? ");
        char decision = keyboard.next().charAt(0);
        while(decision != 'n' && decision != 'c') {
            System.out.println("Please type 'n' or 'c'.");
            decision = keyboard.next().charAt(0);
        }

                // start new game and initialize the player and non players
        Player player1 = new Player();
        ArrayList<Character> nPC = new ArrayList<>();
        if (decision == 'n') {
            //Initialize a new player
            GameMethods.initNewPlayer(player1);

            //Initialize the playing map
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
                        name = "Acromantula";
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
            }
        } else {
            // This reads the files that are output when seleicting 's' during the move phase
            // First is the player1 object.
            try {
                File p1File = new File(P1FILE);
                Scanner input = new Scanner(p1File);
                player1.setName(input.next());
                player1.setHitPoints(input.nextInt());
                player1.setAttackPoints(input.nextInt());
                player1.setRoomNumber(input.nextInt());
                player1.setRace(input.next());
                player1.setOriginalHitPoints(input.nextInt());
                input.close();
            }
            catch (FileNotFoundException ex) {
                System.out.println("Could not find file.");
            }
            // Now we read the non player array list.
            // Now that it's done I am sure that I could have done this with one file.
            try {
                File npFile = new File(NPFILE);
                Scanner input = new Scanner(npFile);
                String name;
                int hitPoints, attackPoints, roomNumber;
                boolean helper, defeated;
                for (int i = 0; i < 8; i++) {
                    name = input.next();
                    hitPoints = input.nextInt();
                    attackPoints = input.nextInt();
                    roomNumber = input.nextInt();
                    helper = input.nextBoolean();
                    defeated = input.nextBoolean();
                    nPC.add(new NonPlayer(hitPoints, attackPoints, helper));
                    nPC.get(i).setName(name);
                    nPC.get(i).setRoomNumber(roomNumber);
                    if (defeated) {
                        nPC.get(i).setDefeated();
                    }
                }
                input.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Could not find file");
            }
        }

        // This is the start of the main play round
        System.out.println("\nLet's begin!");
        GameMethods.roomDescription(player1.getRoomNumber(), nPC.get(player1.getRoomNumber()).getName(), nPC.get(player1.getRoomNumber()).isDefeated());
        do {
            //Move character
            boolean motion = true;
            do {
                System.out.println("\nWhat would you like to do?");
                System.out.print("move (f)orward, move (b)ackward, (s)ave game, or e(x)it: ");
                decision = keyboard.next().charAt(0);
                switch (decision) {
                    case 'f':
                        // The player cannot move forward if his HP drops to zero.
                        if(player1.getHitPoints() <= 0) {
                            System.out.println("I'm sorry, you can't move toward an opponent until your HP ");
                            System.out.println("is restored. You can only only move back to the Nymph or the Wizard.");
                        } else
                            player1.move(1);
                        break;
                    case 'b':
                        // We cannot allow the player to move backward from the entrance of the cave.
                        if(player1.getRoomNumber() == 0)
                            System.out.println("It took you hours to get here. Do you really want to go back?\n");
                        else
                            player1.move(-1);
                        break;
                    case 's':
                        // Save text reprensentations of all the objects.
                        // They'll be read in at the start of the game should the player choose
                        try {
                            File p1File = new File(P1FILE);
                            PrintWriter outputP1ToFile = new PrintWriter(p1File);
                            outputP1ToFile.println(player1.toString());
                            outputP1ToFile.close();
                        }
                        catch (IOException ex) {
                            System.out.println("There was a problem saving the player1 file.");
                        }

                        try {
                            File npFile = new File(NPFILE);
                            PrintWriter outputNpToFile = new PrintWriter(npFile);
                            for (Character np : nPC) {
                                outputNpToFile.println(np.toString());
                            }
                            outputNpToFile.close();
                        } catch (IOException ex) {
                            System.out.println("There was a problem saving non player file.");
                        }
                        System.out.println("Game saved.");
                        break;
                    case 'x':
                        // Exit the game.
                        // Since the main play happens inside a large while loop
                        // we make the decision to exit the loop using System.exit.
                        keyboard.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please enter f, b, s or x.");
                        motion = false;
                        break;
                }
            // This controls the motion once the action has been selected
            // Only f and b go somewhere. S and x bypass this block and
            // go back to the motion selection.
            } while (!motion);

            int roomNum = player1.getRoomNumber(); // This was added for convenience.
            GameMethods.roomDescription(player1.getRoomNumber(), nPC.get(roomNum).getName(), nPC.get(roomNum).isDefeated());

            // Once the player reaches the goal, this takes him to the end.
            if(roomNum == 7) {
               GameMethods.endGame(player1.getName());
               keyboard.close();
               System.exit(0);
            }
            //A helper will restore HP to the player, if the player needs it.
            if(nPC.get(roomNum).isHelper() && player1.getHitPoints() < player1.getOriginalHitPoints()) {
                player1.setHitPoints(player1.getOriginalHitPoints());
                System.out.println("Your HP are restored to " + player1.getHitPoints());
            } else {
                //This is the start of the fight rounds.
                //We don't want to fight the Wizard, the Nymph, or the dragon.
                boolean fight = !nPC.get(roomNum).isHelper();

                //The player has to be healthy enought to fight, and the opponent
                //can't be defeated. If the opponent has been defeated, the player
                //will know when he enters the room.
                while(fight && player1.getHitPoints() >= 0 && !nPC.get(roomNum).isDefeated()) {
                    System.out.println("\nYou have " + player1.getHitPoints() + " hitpoints.");
                    System.out.print("Do you want to (f)ight or (r)un? ");
                    decision = keyboard.next().charAt(0);
                    while(decision != 'f' && decision != 'r') {
                        System.out.println("Please enter 'f' or 'r'.");
                        decision = keyboard.next().charAt(0);
                    }

                    //The player will know if his hit points are low. That's why he can run and go back
                    // to one of the helpers.
                    if(decision == 'r') {
                        player1.move(-1);
                        roomNum = player1.getRoomNumber();
                        GameMethods.roomDescription(player1.getRoomNumber(), nPC.get(roomNum).getName(), nPC.get(roomNum).isDefeated());
                        fight = false;

                    } else {
                        //player fight round
                        player1.fight(nPC.get(roomNum));

                        //non player fight round
                        if(!nPC.get(roomNum).isDefeated())
                            nPC.get(roomNum).fight(player1);
                        if(player1.getHitPoints() <= 0) {
                        //try to put this into a player.retreat method
                            player1.move(-1);
                            roomNum = player1.getRoomNumber();
                            System.out.println("You can no longer fight and you must retreat.");
                            GameMethods.roomDescription(player1.getRoomNumber(), nPC.get(roomNum).getName(), nPC.get(roomNum).isDefeated());
                        }
                    }
                }
            }

        } while (true);
    }
}