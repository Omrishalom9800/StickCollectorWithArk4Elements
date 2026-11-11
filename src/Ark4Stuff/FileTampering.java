package Ark4Stuff;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import java.io.IOException;
import java.sql.Array;
import java.util.Optional;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Stream;

//get methods
import static Methods.OtherFunctions.*;
import static Methods.MiniGames.*;
import static Methods.RequireInput.*;
import static Methods.print.*;

//get classes
import Classes.StickStorage.*;
import Classes.Stick.*;
import Classes.Enemy.*;


// I contain all things file

public class FileTampering {

    String stickFileName = "stickStorage.txt"; // saves stick collection
    String gameFileName = "gameStorage.txt"; // saves other things (health, wave, enemy)

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

    public static void writeToFile(String fileName, String text){ // will override the previous text

        try { // safety net

            Path path = Path.of(fileName); // make the file reference storer
            Files.writeString(path, text);


        } catch(Exception e) {
            print("Write method errored." +
                    "\n Can't write to "+fileName+" file. so progress may not be saved, or program will break.");

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


    public static String readLineFromFile(String fileName, int line){ // this will give the specif line back, there is one.
        Path path = Path.of(fileName); // make the file reference storer

        try (Stream<String> lines = Files.lines(path)){ // safety net. this will also make a var called lines
            // Lines
            Optional<String> data = lines.skip(line -1).findFirst(); // this gets the line. it might not be a string so optional is there

            if(data.isPresent()){ // if theres nothing it will return null
                return data + ""; // force it to string
            }

        } catch(Exception e) {
            print("read method errored." +
                    "\n Can't get from "+fileName+" file, line "+ line+". so progress may not have saved, or the file is weird." +
                    "\n returning null.");
        }
        return null;
    }



}
