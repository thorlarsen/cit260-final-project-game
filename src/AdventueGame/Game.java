        package AdventueGame;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.Random;
        import java.util.Scanner;

        public class Game {

            public static final String P1FILE = "p1-data.file";
            public static final String NPFILE = "np-data.file";

            public static void main(String[] args) {
                gameDescription();
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
                    System.out.print("What is your name?");
                    String player1Name = keyboard.next();
                    //Player player1 = new Player();
                    player1.setName(player1Name);
                    System.out.println("\nNext choose your race. Humans have fewer hitpoints than the others, ");
                    System.out.println("but they have the strongest attack. Dwarves have more hitpoints than ");
                    System.out.println("humans, but their attacks are weaker. Elves have the most hitpoints; ");
                    System.out.println("their attack is stronger than dwarves but weaker than humans.");
                    System.out.print("\nWhat race will you be? (h)uman, (d)warf, or (e)lf? ");
                    char race = keyboard.next().charAt(0);
                    while (race != 'h' && race != 'd' && race != 'e') {
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
                roomDescription(player1.getRoomNumber(), nPC.get(player1.getRoomNumber()).getName(), nPC.get(player1.getRoomNumber()).isDefeated());

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
                        roomDescription(player1.getRoomNumber(), nPC.get(roomNum).getName(), nPC.get(roomNum).isDefeated());

                        // Once the player reaches the goal, this takes him to the end.
                        if(roomNum == 7) {
                            endGame(player1.getName());
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
                                    roomDescription(player1.getRoomNumber(), nPC.get(roomNum).getName(), nPC.get(roomNum).isDefeated());
                                    fight = false;

                                } else {
                                    //player fight round
                                    System.out.println("You attack.");
                                    System.out.println("The " + nPC.get(roomNum).getName() + " has " + nPC.get(roomNum).getHitPoints() + " HP.");
                                    // 5% chance of a mortal blow at the beginning of each fight turn.
                                    // When that happens the opponent is instantly defeated.
                                    if (RandomNum(20) == 20) {
                                        System.out.println("You did a mortal blow!!");
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
                                        // Has a 1 in 3 chance of missing the player, and he'll do no damge if he misses.
                                        if (RandomNum(3) == 3) {
                                            System.out.println("and misses! No damage.");
                                        } else {
                                            System.out.println("and does " + nPC.get(roomNum).getAttackPoints() + " points of damage.");
                                            player1.setHitPoints(player1.getHitPoints() - nPC.get(roomNum).getAttackPoints());
                                            if(player1.getHitPoints() <= 0) {
                                                player1.move(-1);
                                                roomNum = player1.getRoomNumber();
                                                System.out.println("You can no longer fight and you must retreat.");
                                                roomDescription(player1.getRoomNumber(), nPC.get(roomNum).getName(), nPC.get(roomNum).isDefeated());
                                            }
                                        }
                                    }
                                }
                            }

                    }

                } while (true);

            }


            /* This method will print the game description and instructions.
             * The goal of the game is to face challenges and work toward
             * the goal of freeing the dragon from capitivity. The player
             * will receive hints from helpers */
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