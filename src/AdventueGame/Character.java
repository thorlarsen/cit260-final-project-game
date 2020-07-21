package AdventueGame;

public class Character {

    private int hitPoints;
    private int attackPoints;
    private int roomNumber;
    private String name;


    public Character(){
        /* default non-parameterized constructor
         * will create a default character object
         */
        this(0, 0, 0, "dummy" );

    }

    public Character(int hitPoints, int attackPoints, int roomNumber, String name){
        /* default paramaterized object
         * this hitpoints = user input hitpoints
         * this attackpoints = user input attackpoints
         */
        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
        this.roomNumber = roomNumber;
        this.name = name;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public int getAttackPoints() {
        return this.attackPoints;
    }

    public int getRoomNumber() { return this.roomNumber; }

    public String getName() { return this.name; }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public void setName(String name) { this.name = name; }

    public void attack(int getAttackPoints) {
        /*attack the opponent
        get he opponents hitpoints and subtract this.attack points from it.
         */
    }

}
