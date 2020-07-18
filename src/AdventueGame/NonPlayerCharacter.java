package FinalProject;

public class NonPlayerCharacter {

    private String type;
    private Boolean helper;
    private Boolean defeated = false;

        //default no parameter constructor
       public NonPlayerCharacter() {

        }

        //default perameterized constructor
       public NonPlayerCharacter( int hitPoints, int attackPoints, String type ,boolean helper){

        }

        //determines whether character is a helper or not
        public boolean isHelper () {
            //returns true or false. If helper = T, return T. else return F
        }

        //returns type of non player character
        public String getType () {
            //returns type
        }

        //change value of defeated from false to true
        public boolean setDefeated () {
           //if helper = false, set defeated = true
        }

    }
