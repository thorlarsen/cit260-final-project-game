package AdventueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AdventureGame {

    public static void main(String[] args) {
        gameDescription();
        boolean newGame = true;

        // start new game or load saved game
        if (newGame) {
            Player player = new Player();
            gameInitialize(player);
            ArrayList<Character> nonPlayers = new ArrayList<>();
            mapInitialize(nonPlayers);
        } else {
            gameRestore();
        }

        boolean gameOver = false;
         /* while !gameOver
         * prompt player to move(f or b), (s)ave, or e(x)it
         *      ++First turn player can only go forward or save
         *
         *      if player response == s
         *          saveGame()
         *          resume game
         *
        /*
         *      if player response == x
         *          exitGame()
         *
         *     if the player moves into a room occupied by an opponent
         *     player chooses fight or run at start of each round
         *
         *      if fight
         *           while player.hitpoints > 0 or nonPlayer.hitpoints > 0
         *             +the player gets the first hit
         *               if random(20) = 20 then player.superAttack()
         *                  ++the first hit has a 5% chance of being a super hit
         *                  +++the opponent will be immediately knocked
         *              else player.attack(player.getAttackPoints)
         *              ++the player won't miss
         *              ++each hit does player.getAttackPoints amount of damage
         *              opponent hitpoints = opponet.hitpoints - player.attackpoints
         *
         *              opponent.attack()
         *              +the opponent has a 2/3 chance of hitting
         *              if random(3) < 3
         *                  player hitpoints = player hitpoints - opponent attackpoints
         *              ++
         *
         *
         *          +if the opponent's hit point == 0
         *              ++the opponent magically disappears
         *              player.move(f or b)
         *          +else if player hit points == 0
         *              player.move(last room where there is a helper)
         *                  setHitpoints(full)
         *
         *       else run
         *         player.move(last room where there is a helper)
         *         setHitpoints(full)         *
         *         player.move(f or b) or saveGame
         *
         *      if player.move goes to an empty room
         *          print "this room is empty"
         *
         *      if room is occupied by a wizard or nymph
         *          print words of encouragement
         *          offer hint on how to free the dragon
         *          restore hitpoints to full
         *
         *      if room is the last one it will be occupied by the dragon
         *          print dragon's speech
         *          Dragon test
         *          user responds with first hint
         *          while incorrect
         *              dragon gets more anxious
         *              player tries again
         *
         *          Dragon asks how to free him
         *          user responds with spell to free dragon
         *          while incorrect
         *              dragon gives a dirty look
         *              player tries again
         *
         *          Dragon is freed
         *          praises and accolades for the player
         *
         *          Game ends
         *
         */
    }


    public static void gameDescription() {
        /* This method will print the game description and instructions.
         * The goal of the game is to face challenges and work toward
         * the goal of freeing the dragon from capitivity. The player
         * will receive hints from helpers
         */
        System.out.println("This will be the game description.");
    }

    public static void gameInitialize(Player player) {
        /* This method will initialize the game
         */


        Scanner input = new Scanner(System.in);
        System.out.println("What is your name?");
        player.setName(input.nextLine());
        System.out.println("What race will you be? human, dwarf, or elf? ");
        String race = input.nextLine();
        if (race.equals("human")) {
            player.setHitPoints(30);
            player.setAttackPoints(10);
        } else if (race.equals("dwarf")) {
            player.setHitPoints(35);
            player.setAttackPoints(15);
        } else if (race.equals("elf")) {
            player.setHitPoints(35);
            player.setAttackPoints(12);
        }
        input.close();
        player.setRoomNumber(0);
    }

    //setup the game map
    public static void mapInitialize(ArrayList<Character> nonPlayers) {
        int[] caveRooms = {0, 1, 2, 3, 4, 5, 6, 7};


        /* create npCharacters array list of all the non player characters.
         *  wizard = list member 1
         *  nymph = list member 4
         *  dragon = list member 7
         * 4 non helper npCharacters will occupy the other cave rooms
         * list member number will correspond with room number
         * outside cave = room[0]
         * set player.position(0)
         */


        int hitPoints = 0, attackPoints = 0;
        String name;
        boolean helper = false;
        for (int i = 1; i <= 7; i++) {
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
            }
            nonPlayers.add(new NonPlayer(hitPoints, attackPoints, helper));
            nonPlayers.get(i - 1).setName(name);
            nonPlayers.get(i - 1).setRoomNumber(i);
            helper = false;

        }
        return nonPlayers;
    }

    }

    public static void gameSave() {
        /* This will create a text file that will contain
         * text representation of the game's current state.
         */
    }

    public static void gameRestore() {
        /*This will read in the text from the save file and
         * it will initialize the game instead of gameInitialize.
         */
    }

    public static int random(int howManyNumbers){

        Random randomGenerator=new Random();
        System.out.println(randomGenerator.nextInt(howManyNumbers) + 1);

    }
}
