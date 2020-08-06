package AdventueGame;


public class NonPlayer extends Character {

    private Boolean helper;
    private Boolean defeated = false;

        //default no parameter constructor
    public NonPlayer() {
            this(0, 0, false);
        }

        //parameterized constructor
    public NonPlayer(int hitPoints, int attackPoints, boolean helper){
            this.setHitPoints(hitPoints);
            this.setAttackPoints(attackPoints);
            this.helper = helper;
    }

    @Override
    public void fight(Player player1) {

        System.out.println("The " + this.getName() + " attacks");
        // Has a 1 in 3 chance of missing the player, and he'll do no damge if he misses.
        if (GameMethods.RandomNum(3) == 3) {
            System.out.println("and misses! No damage.");
        } else {
            System.out.println("and does " + this.getAttackPoints() + " points of damage.");
            player1.setHitPoints(player1.getHitPoints() - this.getAttackPoints());
        }
    }

     //determines whether character is a helper or not
    @Override
    public boolean isHelper() {
        //returns true or false.
        // If helper = T, return T. else return F
        return helper;
    }

    //change value of defeated from false to true
    @Override
    public void setDefeated() {
        if (!helper) {
            this.defeated = true;
        }
    }

    @Override
    public boolean isDefeated() { return this.defeated; }

    @Override
    public String toString( ) {
        return super.toString() + "\n" + this.isHelper() + "\n" + this.isDefeated();
    }

    @Override
    public void fight(Character opponent) {

    }

}
