

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

//get methods
import static Methods.OtherFunctions.*;
import static Methods.RequireInput.*;
import static Methods.print.*;
import static RetiredArk4Stuff.FileTampering.*;
import static RetiredArk4Stuff.CvsAndDataTranslation.*;

// this should replace the file tampering and cvs methods
//import static GsonMethods.DataTranslationGsonReplacement.*;
//import static GsonMethods.FileTamperingGsonReplacement.*;

//get classes
import Classes.StickStorage.*;
import Classes.Enemy.*;
import com.google.gson.Gson;

import static Classes.Stick.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in); // console reader object
        // variable = scanner.nextLine(); to read console
        // scanner.close(); to kill it
        //  scanner.nextLine(); placing this alone will clear the line

        confirmFile(stickFileName);

        Gson gson = new Gson();
  //      String json = gson.toJson();

        if(false) { // testing stuff, disabled right now
        giveThreeRandomSticks();
            System.exit(0);// dont play game, only run test
        }
        if(false){
            saveGameState(2,23);
            print(getSavedHealth());
            print(getSavedLevel());
            System.exit(0);// dont play game, only run test
        }


        // Public Test interface. so teach can easily test the saving
        int[] threeOptions = new int[3]; // fill out the options for the array
        for(int i = 0; i<threeOptions.length; i++){
            threeOptions[i] = i+1;
        }

        int test = requireInt(threeOptions, "To test ark4 file saving features would you like to"+
                "\n 1. I would not like to test file saving. Let me play the game."+
                "\n 2. I would like to get sticks in storage, then immediately proceed to game, and experience the game attempt to restore the sticks.(will clear current stored data)"+
                "\n 3. I would like to get sticks in storage, then quit out the program, then run program again, select option 1, and experience the game restore the saved sticks.(will clear current stored data)");

        if(test ==2){
            giveThreeRandomSticks();

            pause(500); // the pauses are to make the program look like its doing something important
            print("proceeding...");
            pause(500);
        } else if(test ==3){
            giveThreeRandomSticks();

            pause(500);
            print("Ending program. \n Please re-run program and select option 1");
            System.exit(0);
        } else{
            print("Proceeding to game");
            pause(500);
        }


        //start playing the game
        boolean playing = true; // set to false, to kill the game loop

        boolean checkedFiles = false;// did ask to restore sticks?

        while (playing) {
            //game state vars
            int health = 15;
            int level = 1;

            // recover sticks prompt
            ArrayList<stick> recovered = null;
            if(checkedFiles == false){
                if(!fileEmpty(stickFileName) || !fileEmpty(gameFileName)){
                    print("");
                   boolean restore = requireBoolean("You seem to have saved sticks or game data! \n " +
                            "Would you like to attempt to restore them? (saying no will clear data, and start new game)");
                   if(restore){
                       print("restoring game data...");
                       pause(500);// pauses for dramatic effect

                       health = getSavedHealth();
                       level = getSavedLevel();

                       print("Health: " + health);
                       pause(200);
                       print("Level: " + level);

                       pause(500);
                       print("restoring Sticks...");
                       pause(500);

                       recovered = restoreStickData();

                       if(recovered.size() <= 0){
                           print("no sticks was recovered, data is weird or no sticks were present");
                           writeToFile(stickFileName, ""); // clear the file because the data is weird
                       }else{
                           print(recovered.size() + " item(s) recovered");

                           for (int i = 0; i < recovered.size(); i++) {
                               pause(200);
                               print(recovered.get(i).getName());
                           }

                       }



                   }else{
                       print("Clearing saved data...");
                       writeToFile(stickFileName,"");
                       writeToFile(gameFileName,"");
                       pause(500);
                   }
                    pause(200);
                    requireString("Type something to start game.");
                    pause(200);
                    print("");
                }
                checkedFiles = true;

            }

            stickStorage sticks;// the stick storage

            if(recovered != null && recovered.isEmpty() == false) { // fill out stick storage
                sticks = new stickStorage(recovered); // there should only be one of theses.
            }else{
                sticks = new stickStorage(); // new one
            }



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
                int turns = 0; // counts how many turns there has been

                while (enemyAlive && health > 0) {

                    if(turns != 0){ // emeny attacks first, so its self defense

                    int[] optionList = {1, 2, 3}; // create options for player action
                    int option = requireInt(optionList, "Would you like to \n 1, attack " + currentEnemy.getName() + " \n 2, Swap primaries \n 3, Do nothing \n your current weapon is " + sticks.getPrimary().getName());

                    // make game loop
                    if (option == 1) {
                        currentEnemy.attack(sticks.getPrimary());
                        pause(200);
                        print(currentEnemy.getName() + " has " + currentEnemy.getHealth() + " health left.");
                        pause(1000);
                    } else if (option == 2) {
                        print("what would you like to swap to?");

                        int[] weaponList = new int[sticks.getStorage().size()]; // create options for player action

                        for(int i =0; i < weaponList.length; i++){// fill out the options inputs.
                            weaponList[i] = i +1;
                        }

                        String weaponListContext = "Your choises are: ";
                        for (int i = 0; i < sticks.getStorage().size(); i++) {

                            if(Objects.equals(sticks.getStorage().get(i).getName(), "fists")) {
                                weaponListContext = weaponListContext + "\n" + i + ", " + sticks.getStorage().get(i).getName() +
                                        ", Strength: " + sticks.getStorage().get(i).getStrength() + ", Stiffness: unbreakable" ;
                            }else{
                                weaponListContext = weaponListContext + "\n" + i + ", " + sticks.getStorage().get(i).getName() +
                                        ", Strength: " + sticks.getStorage().get(i).getStrength() + ", Stiffness: "+sticks.getStorage().get(i).getStiffness() ;
                            }
                        }
                        int weaponOption = requireInt(weaponList, weaponListContext);
                        sticks.setPrimary(sticks.getStorage().get(weaponOption));

                        print("you have picked " + sticks.getStorage().get(weaponOption).getName());
                        pause(1000);
                    } else {
                        print("you have skipped");
                        pause(1000);
                    }

                }else{
                    pause(1000);
                    }

                    if (currentEnemy.getHealth() <= 0) { // take stick if he's dead
                        enemyAlive = false;
                        print(currentEnemy.getName() + " has been defeated!");

                        if(level <= 2) {
                            currentEnemy.getPrimaryWeapon().setStrength(currentEnemy.getPrimaryWeapon().getStrength() + 2); // give in extra two points of damage to the weapon
                        }                                                                                 // i want the user to feel strong at first

                        currentEnemy.getPrimaryWeapon().setName(currentEnemy.getName() + "'s "+ currentEnemy.getPrimaryWeapon().getName()); // name the stick after the enemy

                        pause(1000);

                        boolean youWant = requireBoolean("would you like " + currentEnemy.getName() + "'s weapon? \n The weapon is called "
                                + currentEnemy.getPrimaryWeapon().getName() +
                                ". \n Stiffness: " + currentEnemy.getPrimaryWeapon().getStiffness() +
                                "\n Strength: " + currentEnemy.getPrimaryWeapon().getStrength() + ".");

                        if (youWant) {
                            sticks.getStorage().add(currentEnemy.getPrimaryWeapon());
                            print("you took " + currentEnemy.getPrimaryWeapon().getName() + "!");
                            saveStick(currentEnemy.getPrimaryWeapon());// save stick to storage
                            pause(500);
                        } else {
                            print("you did not take " + currentEnemy.getPrimaryWeapon().getName() + ".");
                            pause(1000);
                        }
                        turns = 0; // reset turns
                    } else { // enemy turn

                        health = health - currentEnemy.block(sticks.getPrimary(), sticks); // block attack

                        if (health <= 0) {
                            print("you have dead, :(");
                            pause(500);
                            print("reseting save file.");
                            writeToFile(stickFileName,"");
                            writeToFile(gameFileName,"");

                        } else {
                            print("your health is " + health);
                        }

                        turns++;
                        pause(1000);
                    }
                }

                level++;
                if( health > 0) {// dont save if dead
                    saveGameState(health, level);
                }
            }

            if (!requireBoolean(" Would you like to reset and continue?")) { // end game if they choose
                playing = false;

            }else{// just making sure its actually cleared
                writeToFile(stickFileName,"");
                writeToFile(gameFileName,"");
            }
        }
    }
}