package AdventueGame;

import java.io.Serializable;

public abstract class Character {

    private String name;
    private int hitPoints;
    private int attackPoints;
    private int roomNumber;

    /* default non-parameterized constructor
     * will create a default character object
     */
    public Character(){

        this(0, 0, 0, "dummy" );

    }

    /* default paramaterized object
     * this hitpoints = user input hitpoints
     * this attackpoints = user input attackpoints
     */
    public Character(int hitPoints, int attackPoints, int roomNumber, String name){

        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
        this.roomNumber = roomNumber;
        this.name = name;
    }

    // Set hit points
    // both players and non players need this.
    public int getHitPoints() {

        return this.hitPoints;
    }

    // Returnd the number of hit points
    // Most important during the fight rounds
    public int getAttackPoints() {
        return this.attackPoints;
    }

    // Used to keep track of where the player and non players are in the cave.
    public int getRoomNumber() { return this.roomNumber; }

    // All characters have a name. the non players use this to store the
    // type of non player they are, Wizard, Wold, Dragon, etc.
    public String getName() { return this.name; }

    // Hit poinst change and that is what this method does
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }


    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public void setName(String name) { this.name = name; }

    public String toString() {
        return this.getName() + "\n" + this.getHitPoints() + "\n" + this.getAttackPoints() + "\n" + this.getRoomNumber();
    }

    public abstract boolean isHelper();

    public abstract boolean isDefeated();

    public abstract void setDefeated();
}
