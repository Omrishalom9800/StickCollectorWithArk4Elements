package Classes;

//get methods
import static Methods.OtherFunctions.*;
import static Methods.MiniGames.*;
import static Methods.RequireInput.*;
import static Methods.print.*;

//get classes
import Classes.StickStorage.*;
import Classes.Enemy.*;

public class Stick {
    public static class stick {

        //metadata-----------
        private String name; // cool name
        private int stiffness; // how many avalible blocks before it breaks // also unnecessary
        private int strength; // damage
        private String attackType; // what minigame plays when attacking, also unnecessary
        private double value; // sell price
        private int id; // creation id, should never change

        public static int totalSticks; // total number of sticks made

        // constructor--------
        public stick(String Name, int Stiff, int Str, String aT) {

            this.name = Name;
            //this.wood = Wood;
            this.stiffness = Stiff;
            this.strength = Str;
            this.attackType = aT;

            this.value = calculateValue();

            totalSticks++; // increase from 0 to 1
            this.id = totalSticks; // assign id
        }


        // functions and stuff-------------
        public double calculateValue() {
            return (stiffness * strength * 0.005);
        } // if your stick is trouble enough, it could be negative.

        public boolean isBroken() {
            if (stiffness <= 0) {
                return true;
            }
            return false;
        }

        // gets and sets-------------

        //gets--
        public double getValue() {
            return value;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getStiffness() {
            return stiffness;
        }

        public int getStrength() {
            return strength;
        }

        //sets--
        public void setName(String newName) {
            name = newName;
        }

        public boolean setStiffness(int newStif) {
            stiffness = newStif;
            return isBroken();
        }

        public boolean damageStick() {
            stiffness--;
            return isBroken();
        }
        // crickets

    }
}
