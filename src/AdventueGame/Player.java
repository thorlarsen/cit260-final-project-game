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

    public int getOriginalHitPoints() { return this.originalHitPoints; };

    public void setOriginalHitPoints(int hitPoints) {
        this.originalHitPoints = hitPoints;
        super.setHitPoints(hitPoints);}

    public void setRace(String race) {
        this.race = race;
    }

    public void superAttack(int superAttackPoints) {
        // will cause an instant win against opponent
    }

    public void move(int newRoom) {
        this.setRoomNumber(this.getRoomNumber() + newRoom);
    }
}