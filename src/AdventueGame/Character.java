package AdventueGame;

public abstract class Character {
    private int hitPoints;
    private int attackPoints;

    public Character(){
        /* default non-parameterized constructor
         * will create a default character object
         */
    }

    public Character(int hitPoints, int attackPoints){
        /* default paramaterized object
         * this hitpoints = user input hitpoints
         * this attackpoints = user input attackpoints
         */
    }

    public int getAttackPoints() {
        /* return character's attackpoints
        */
    }

    public int getHitPoints() {
        /* return character's hitpoints
         */
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void attack(int getAttackPoints) {

    }

}
