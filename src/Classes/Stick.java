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


    public static stick makeRandomWeapon(int level) {

        int strMin = level; // make weapon hover around a certain str range
        if(level -3 <= 2){
            strMin = 2;
        }

        if(level>15){ // just start giving absolutely random weapons
            strMin = 3;
        }

        int stif = level;
        if(stif > 8) { // dont let stiffness get over 8
            stif = 8;
        }

        String stickName = randomObjectName();
        if(level > 10){ // make sticks more unique after ten
            stickName = stickName + " " + randomObjectName();
        }


        stick newWeapon = new stick(stickName + " stick", randomNumber(2, stif), randomNumber(strMin-3, level+2));
        return newWeapon;
    }

    public static class stick {

        //metadata-----------
        private String name; // cool name
        private int stiffness; // how many avalible blocks before it breaks
        private int strength; // damage
       // private String attackType; // what minigame plays when attacking, also unnecessary
        private double value; // sell price
        private int id; // creation id, should never change

        public static int totalSticks; // total number of sticks made

        // constructor--------
        public stick(String Name, int Stiff, int Str) {

            this.name = Name;
            //this.wood = Wood;
            this.stiffness = Stiff;
            this.strength = Str;

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

        public void setStrength(int newStr) {
            strength = newStr;
        }
        public boolean damageStick() {
            stiffness--;
            return isBroken();
        }

    }


}
