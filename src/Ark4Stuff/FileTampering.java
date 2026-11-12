package Ark4Stuff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Stream;

//get methods
import static Methods.print.*;

//get classes
import Classes.StickStorage.*;
import Classes.Stick.*;
import Classes.Enemy.*;


// I contain all things file

public class FileTampering {

    //global names
    public static String stickFileName = "stickStorage.txt"; // saves stick collection
    public static String gameFileName = "gameStorage.txt"; // saves other things (health, wave, enemy)

    public static boolean confirmFile(String fileName){ // makes a file if none already and returns weather it did.

        try { // safety net
            File file = new File(fileName);
            if(file.createNewFile()){
                //did not exist
                return true;
            }else{
                //already exists
                return false;
            }

        } catch(Exception e) {
        print("comfirmFile method errored." +
        "\n Can't tell if "+fileName+" file exists so im gonna close the program.");

        System.exit(0);// close the program
        }
    return false;
    }

    public static void writeToFile(String fileName, String text){ // will override the previous text in the file with new text

        try { // safety net

            Path path = Path.of(fileName); // make the file reference storer
            Files.writeString(path, text);


        } catch(Exception e) {
            print("Write method errored." +
                    "\n Can't write to "+fileName+" file. so progress may not be saved, or program will break.");

        }

    }

    public static void writeWithoutClear(String fileName, String text, boolean newLine){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){ //  makes a writer that will append the string you put at the lowest line
            // Lines

            if(newLine){
                writer.newLine();
            }

        writer.write(text);

        } catch(Exception e) {
            print("write without clear method errored." +
                    "\n Can't get from "+fileName+" file. so progress may not be saved.");
        }
    }



    public static String readFromFile(String fileName){ // this will give Everything in the file back

        try { // safety net

            Path path = Path.of(fileName); // make the file reference storer
            return Files.readString(path);

        } catch(Exception e) {
            print("read method errored." +
                    "\n Can't get from "+fileName+" file. so progress may not have saved, or the file is weird." +
                    " \n returning null.");

        }

        return null;
    }


    public static String readLineFromFile(String fileName, int line){ // this will give the specif line back, if there is one.
        Path path = Path.of(fileName); // make the file reference storer

        try (Stream<String> lines = Files.lines(path)){ // safety net. this will also make a var called lines
            // Lines
            Optional<String> data = lines.skip(line -1).findFirst(); // this gets the line. it might be null so optional is there

            if(data.isPresent()){ // if theres nothing it will return null

                String betterData = data.orElse(""); // optionals are cool vars that dont crash when null is in them.
                                                            // in this senoiry the optional will turn to string, or if null just be "".
                return betterData;
            }

        } catch(Exception e) {// expected to fail at some point during runtime, no need to warn the user
          //  print("read method errored." +
          //          "\n Can't get from "+fileName+" file, line "+ line+". so progress may not have saved, or the file is weird." +
          //          "\n returning null.");
        }
        return null;
    }


    public static int fileLineCount (String fileName){//how many lines are in the file
        int lineCounter= 1; // for some reason it always gives back one less, so it stars as 1 to offset it

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){ // make a file reader obj that will scroll through the file until the line is null

            while (reader.readLine() != null){
                lineCounter++;
            }

        }catch(Exception e){
            print("file line count errored"+
                    "\n Accessing data from " + fileName + " file may be inaccurate.");
        }
        return lineCounter;
    }


    public static boolean fileEmpty(String fileName){ // is there something in the file?, does not check if we can use the something

        File file = new File(fileName);
        if(file.exists()){
            if(file.length() <= 0){
                return true;
            }
        }
        return false;
    }


}
