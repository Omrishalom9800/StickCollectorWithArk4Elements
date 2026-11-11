

import java.io.IOException;
import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;

//get methods
import static Methods.OtherFunctions.*;
import static Methods.MiniGames.*;
import static Methods.RequireInput.*;
import static Methods.print.*;

//get classes
import Classes.StickStorage.*;
import Classes.Stick.*;
import Classes.Enemy.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in); // console reader object
        // variable = scanner.nextLine(); to read console
        // scanner.close(); to kill it
        //  scanner.nextLine(); placing this alone will clear the line
        boolean playing = true;

        while (playing) {

            int health = 15;
            stickStorage sticks = new stickStorage(); // there should only be one of theses.

            int level = 1;

            printSameLine("Your Goal?");
            pause(1000);
            print(" Get sticks.");
            pause(1500);
            printSameLine("How?");
            pause(1000);
            printSameLine(" Defeat People and take them for your self!");
            pause(1000);


            while (health > 0) { // game loop


                enemy currentEnemy = new enemy("randomName", level);

                print("");
                print("");
                print("you encountered " + currentEnemy.getName() + "!");
                pause(2000);
                print(currentEnemy.getName() + " has " + currentEnemy.getHealth() + " Health!");
                pause(1000);
                print(currentEnemy.getName() + "'s weapon has the name " + currentEnemy.getPrimaryWeapon().getName());
                pause(1000);

                boolean enemyAlive = true;
                while (enemyAlive || health > 0) {

                    int[] optionList = {1, 2, 3}; // create options for player action
                    int option = requireInt(optionList, "Would you like to \n 1, attack " + currentEnemy.getName() + " \n 2, Swap primaries \n 3, Do nothing \n your current weapon is " + sticks.getPrimary().getName());

                    // make game loop
                    if (option == 1) {
                        currentEnemy.attack(sticks.getPrimary());
                        pause(1000);
                    } else if (option == 2) {
                        print("what would you like to swap to?");

                        int[] weaponList = new int[sticks.getStorage().size()]; // create options for player action
                        String weaponListContext = "You choises are: ";
                        for (int i = 0; i < sticks.getStorage().size(); i++) {
                            weaponListContext = weaponListContext + "\n" + i + ", " + sticks.getStorage().get(i).getName();
                        }
                        int weaponOption = requireInt(weaponList, weaponListContext);
                        sticks.setPrimary(sticks.getStorage().get(weaponOption));

                        print("you have picked " + sticks.getStorage().get(weaponOption).getName());
                        pause(1000);
                    } else {
                        print("you have skipped");
                        pause(1000);
                    }

                    if (currentEnemy.getHealth() == 0) {
                        enemyAlive = false;
                        boolean youWant = requireBoolean("would you like " + currentEnemy.getName() + "'s weapon? \n The weapon is called "
                                + currentEnemy.getPrimaryWeapon().getName() +
                                ". \n Stiffness: " + currentEnemy.getPrimaryWeapon().getStiffness() +
                                "\n Strenght: " + currentEnemy.getPrimaryWeapon().getStrength() + ".");

                        if (youWant) {
                            sticks.getStorage().add(currentEnemy.getPrimaryWeapon());
                            print("you took " + currentEnemy.getPrimaryWeapon().getName() + "!");
                            pause(500);
                        } else {
                            print("you did not take " + currentEnemy.getPrimaryWeapon().getName() + ".");
                            pause(1000);
                        }

                    } else { // enemy turn

                        health = health - currentEnemy.block(sticks.getPrimary(), sticks);

                        if (health <= 0) {
                            print("you have dead, :(");
                        } else {
                            print("your health is " + health);
                        }
                        pause(1000);
                    }

                }
                level++;
            }

            if (!requireBoolean(" Would you like to reset and continue?")) { // end game if they choose
                playing = false;
            }
        }
    }
}