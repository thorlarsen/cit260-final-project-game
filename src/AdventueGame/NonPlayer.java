package AdventueGame;


public class NonPlayer extends Character {

    private Boolean helper;
    private Boolean defeated;

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

        //determines whether character is a helper or not
        public boolean isHelper () {
            //returns true or false.
            // If helper = T, return T. else return F
            return helper;
        }

        //change value of defeated from false to true
        public boolean setDefeated () {
            if (!helper) {
                this.defeated = true;
            }
        }

    }
