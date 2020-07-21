package FinalProject;

public class NonPlayerCharacter extends Character {

    private Boolean helper;
    private Boolean defeated = false;

        //default no parameter constructor
       public NonPlayer() {
            this(0, 0, false);
        }

        //default parameterized constructor
       public NonPlayerCharacter( int hitPoints, int attackPoints ,boolean helper){
            this.hitPoints = hitPoints;
            this.attackPoints = attackPoints;
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
            if (helper == false) {
                return defeated = true;
            }
        }

    }
