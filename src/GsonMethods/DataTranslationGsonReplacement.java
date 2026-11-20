package GsonMethods;

// I have more dependant methods that do multiple things with methods from ,
// like 'restore saved sticks', 'save this stick', and 'restore saved game data'.
// uses json with gson instead of cvs

// the json conversion is incomplete.
// there may be cvs placeholder methods.

import java.util.ArrayList;

//get methods
import static Classes.Stick.makeRandomWeapon;
import static Methods.OtherFunctions.pause;
import static Methods.print.*;
import static RetiredArk4Stuff.FileTampering.*;

//get classes
import Classes.Stick.*;

public class DataTranslationGsonReplacement {

    //generic methods

    public static String arrayToCvsString(String[] array){// takes the items from array, and puts them in cvs format
        StringBuilder cvs = new StringBuilder();
        for(int i = 0; i< array.length; i++) {
            cvs.append(array[i]);

            if (i != array.length - 1) { // don't place comma at last item.
                cvs.append(",");
            }
        }
        return cvs.toString();// could return an empty string, ""
    }

    public static String[] cvsStringToData(String line){ // string to array
        return line.split(","); // splits the string up into an array
    }


//stick specific methods

    public static String[] stickToArray(stick stick){ // converts a stick into a three index array of data
        // a sticks constructor is 'name, stif, ans str'
        String[] data = new String[3];
        data[0] = stick.getName();
        data[1] = stick.getStiffness() +"";
        data[2] = stick.getStrength() +"";
        return data;
    }

    public static stick dataToStick(String[] data){ // converts an array to a stick obj

        try{
            return new stick(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        } catch (Exception e) {
            print("Reconstruction of a stick failed, because weird data." +
                    "\n returning null.");

        }
        return null;
    }


    public static ArrayList<stick> restoreStickData(){ // search through stick storage and bring back what it can

        ArrayList<stick> collection = new ArrayList<>();

        int count = fileLineCount(stickFileName);
        for(int i = 0; i<count; i++){ //loop through all lines in the stick storage

            try {
                String cvs = readLineFromFile(stickFileName, i ); // get the line
                if(cvs != null) {
                    String[] data = cvsStringToData(cvs); //
                    stick stic = dataToStick(data);

                    if (stic != null) {
                        if (!stic.getName().equals("fists")) { // don't copy the default weapon if that's somehow in here
                            collection.add(stic);
                        }
                    }
                }else{
                    //print("file "+ stickFileName +", line "+ i+" is null");
                }
            }catch (Exception e){
                //no need to print, this will commonly fail
            }
        }
        return collection;
    }

    public static void saveStick(stick stic){ //easy way to save sticks
        // turns the stick into array, then string, then puts it on the file
        try {
            String[] data = stickToArray(stic);
            String cvs = arrayToCvsString(data);
            writeWithoutClear(stickFileName,cvs,true);

        }catch (Exception e){
            print("save stick errored"+
                    "\n A stick might have not been saved to "+stickFileName+" file to be brought up later.");
        }

    }

    public static void giveThreeRandomSticks(){ // this is for testing purposes
        print("Adding sticks to file");
        writeToFile(stickFileName, "testStickOne,1,1");// clear file
        saveStick(makeRandomWeapon(3));
        saveStick(makeRandomWeapon(5));
        saveStick(makeRandomWeapon(10));
        saveGameState(15,1); // reset the game state

        ArrayList<stick> stuff = restoreStickData();
        pause(500);
        print(stuff.size() + " items saved");

        for (int i = 0; i < stuff.size(); i++) {// get the saved sticks from file and print them
            pause(200);
            print(stuff.get(i).getName());
        }
    }



    // game data specific methods

    public static void saveGameState(int health, int level){ // stores health, level, etc.
        // i would like to save the game in a format ( health,level)
        // thats it, i would to the enemy to just reset, make a new one

        String cvs = health + ","+level;// what im saving
        writeToFile(gameFileName,cvs);
    }

    public static int getSavedHealth(){ // get health
        String state = readFromFile(gameFileName);
        if(state == null){
            print("game save data is null");
        }else {
            String[] data = cvsStringToData(state);

            try{// attempt to turn thew data into int
                return Integer.parseInt(data[0]);
            } catch (Exception e) {
                print("Health data is corrupt."
                        +"\n Returning 15 Health.");
            }
        }
        return 15;
    }
    public static int getSavedLevel(){ // get level
        String state = readFromFile(gameFileName);
        if(state == null){
            print("game save data is null");
        }else {
            String[] data = cvsStringToData(state);

            try{// attempt to turn thew data into int
                return Integer.parseInt(data[1]);
            } catch (Exception e) {
                print("Level data is corrupt."
                        +"\n Returning 1 level.");
            }
        }
        return 1;
    }




}
