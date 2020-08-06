package AdventueGame;


public class Player extends Character {

    private String race;
    private int originalHitPoints;

    public Player(){
        //default
        this(0,0,"human");
    }
    public Player(int hitPoints, int attackPoints, String race) {
         this.setHitPoints(hitPoints);
         this.setAttackPoints(attackPoints);
         this.race = race;

    }

    public String getRace() {
        // returns the race of this character
        return race;
    }

    public int getOriginalHitPoints() { return this.originalHitPoints; }

    // During development we realized we need to keep track
    // of how many hitpoints were assigned at the beginning.
    // This is a quick way of setting both at the same time.
    public void setOriginalHitPoints(int hitPoints) {
        this.originalHitPoints = hitPoints;
        super.setHitPoints(hitPoints);
    }

    // The player's attributes depend on the race.
    // This is a short cut to populate those attributes
    // by simply selecting the race.
    public void setRace(String race) {
        this.race = race;
        if(race.equals("human")) {
            this.setOriginalHitPoints(30);
            this.setAttackPoints(18);
        } else if(race.equals("dwarf")) {
            this.setOriginalHitPoints(35);
            this.setAttackPoints(10);
        } else {
            this.setOriginalHitPoints(40);
            this.setAttackPoints(10);
        }
    }

    public void move(int newRoom) {
        this.setRoomNumber(this.getRoomNumber() + newRoom);
    }

    /**
     * The fight method
     * Handles the fight between the player and non-player characters.
     * @param opponent
     */
    @Override
    public void fight(Character opponent) {
        System.out.println("You attack.");
        System.out.println("The " + opponent.getName() + " has " + opponent.getHitPoints() + " HP.");
        // 5% chance of a mortal blow at the beginning of each fight turn.
        // When that happens the opponent is instantly defeated.
        if (GameMethods.RandomNum(20) == 20) {
            System.out.println("You dealt a mortal blow!!");
            opponent.setDefeated();
            System.out.println("You defeated the " + opponent.getName());
        } else {
            System.out.println("You hit and do " + this.getAttackPoints() + " points of damage");
            opponent.setHitPoints(opponent.getHitPoints() - this.getAttackPoints());
            if(opponent.getHitPoints() <= 0) {
                opponent.setDefeated();
                System.out.println("You defeated the " + opponent.getName());
            }
        }
    }

    /**
     * The retreat method
     * doesn't work yet.
     * @param opponent
     */
    public void retreat(Character opponent) {
        this.move(-1);
        //opponent.setRoomNumber(this.getRoomNumber() - 1);
        System.out.println("You can no longer fight and you must retreat.");
        GameMethods.roomDescription(this.getRoomNumber(), opponent.getName(), opponent.isDefeated());
    }

    @Override
    public String toString( ) {
        return super.toString() + "\n" + this.getRace() + "\n" + this.getOriginalHitPoints();
    }

    @Override
    public boolean isHelper() {
        return false;
    }

    @Override
    public boolean isDefeated() {
        return false;
    }

    @Override
    public void setDefeated() {

    }

    @Override
    public void fight(Player player) {

    }
}

