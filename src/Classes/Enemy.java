package Classes;

import java.io.IOException;

//get methods
import static Methods.OtherFunctions.*;
import static Methods.MiniGames.*;
import static Methods.RequireInput.*;
import static Methods.print.*;

//get classes
import Classes.StickStorage.*;
import Classes.Stick.*;


public class Enemy {
    public static class enemy {

        //metadata------------------------------------

        private int health = 8;
        private stick primaryWeapon; // enemies only get one weapon
        private String name = "-1";

        // attacking
        private int currectAttackIndex = 0;
        private int attackAmount = 1;

        //set up-----------------------------------------

        public enemy(String n, int level) { // call a function to randomize the enemy
            this.name = n;
            this.health = this.health + level;
            if (n.equals("randomName")) {
                this.name = randomName() + " " + randomLastName();
            }

            // inisialize primary weapon
            primaryWeapon = makeRandomWeapon(level);

        }

        // functions ---------------------------------------

        public int block(stick weapon, stickStorage storage) throws IOException { // calculate damage, minigames can be placed here
            print(name + " is attacking you!!");
            print("Hes using his " + primaryWeapon.getName() + "!");

            pause(1500);

            double damage = blockSomething(randomNumber(10, 15), randomNumber(25, 35)); // play mini game

            if (damage > 0) {
                if (weapon.damageStick()) {
                    storage.setPrimary(storage.getByName("fists").get(0)); // set primary to fists
                    storage.removeStick(storage.getIndexByReference(weapon));// remove broken stick
                    print(weapon.getName() + " has broken. setting weapons to fists");
                } else {
                    if (weapon.getName().equals("fists")) {   // make sure fists dont brake
                        weapon.setStiffness(999999);
                    } else {
                        print(weapon.getName() + " lost stiffness."); // if not fists and broken
                    }
                }


            }

            damage = damage * (double) primaryWeapon.getStrength(); // strength multiplier
            damage = (int) Math.ceil(damage);// round up
            print(name + " did " + damage + " damage.");
            pause(1000);
            return (int) damage;
        }

        public int attack(stick weapon) throws IOException { // you attack
            print("Your attacking " + name);
            print("with " + weapon.getName() + "!");

            pause(1500);

            double damage = wackSomething(randomNumber(10, 15), randomNumber(15, 25)); // play mini game
            damage = damage * (double) weapon.getStrength(); // strength multiplier
            damage = (int) Math.ceil(damage);// round up
            print("You did " + damage + " damage with " + weapon.getName());
            pause(1000);
            return (int) damage;
        }

        public stick makeRandomWeapon(int level) {
            stick newWeapon = new stick(randomStickName(), randomNumber(2, level), randomNumber(1, level), "default");
            return newWeapon;
        }

        //gets/sets----------------------------------------

        //gets
        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public stick getPrimaryWeapon() {
            return primaryWeapon;
        }


        //sets
        public void setAttackAmount(int aA) {
            attackAmount = aA;
        }

        public void setHealth(int h) {
            health = h;
        }


    }

}
