package Ark4Stuff;

import java.util.ArrayList;

//get methods
import static Methods.print.*;
import static Ark4Stuff.FileTampering.*;

//get classes
import Classes.Stick.*;

public class CvsAndDataTranslation {

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

public static String[] cvsStringToData(String line){ // for convince
    return line.split(","); // splits the string up into an array
}


//stick specific methods

public static String[] stickToArray(stick stick){ // converts a stick to a three index array of data
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
            String[] data = cvsStringToData(cvs); //
            stick stic = dataToStick(data);

            if(stic != null) {
                if (!stic.getName().equals("fists")) { // don't copy the default weapon if that's somehow in here
                    collection.add(stic);
                }
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


    // game data specific methods

    public static void saveGameState(){ // stores health, level, etc.
    // not done, ran out of time
    }


}
