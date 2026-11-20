package Methods;

import java.util.Scanner;

//get methods
import static Methods.OtherFunctions.*;
import static Methods.print.*;

//get classes
import Classes.StickStorage.*;
import Classes.Stick.*;
import Classes.Enemy.*;

public class RequireInput {


    // get console text functions

    public static int requireInt(int[] validNum, String context) {
        // provide but leave list empty if you want any number
        // provide something like this   int[]list = {}; or this         int[]list = {3,2,5};

        Scanner scanner = new Scanner(System.in); // console reader object

        boolean running = true;
        int response = -1;

        String printableList = ""; // a string form of list values

        for (int i = 0; i < validNum.length; i++) { // makes printableList
            printableList = printableList + " - " + validNum[i];
        }
        print(context);
        print("enter number");

        while (running) {// loop
            boolean valid = true;

            try {// attempt to put console into int

                response = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) { // int wasn't provided

                print("invalid number");
                valid = false; // do not allow progression


                pause(500);
                print("");

                print(context);
            }


            if (valid) { //good response

                if (validNum == null || validNum.length == 0) { // if list empty then allow any number
                    running = false; // close loop

                } else {

                    for (int i = 0; i < validNum.length; i++) {
                        if (response == validNum[i]) { // check if the response is a valid response from the list
                            running = false; // obliterate loop
                        }
                    }
                } // for loop and else close
            }

            if (running && valid) { // remind responses
                print("not an option");

                pause(500);
                print("");

                print(context);
                print("choose one of theses: " + printableList);
            }
        }

        return response;

    }

    public static String requireString(String context) {

        Scanner scanner = new Scanner(System.in); // console reader object

        boolean running = true;
        String response = "";

        print(context);

        while (running) {
            boolean valid = true;


            try {
                response = scanner.nextLine();

            } catch (Exception e) {
                print("invalid characters");
                valid = false;

                scanner.nextLine();

                pause(500);
                print("");

                print(context);

            }

            if (valid) {
                running = false;
                //scanner.close(); no need this miskate will close the all scanner not just this one
            }
        }
        return response;

    }

    public static boolean requireBoolean(String context) {

        Scanner scanner = new Scanner(System.in); // console reader object

        String response = "";

        print(context);
        print("enter yes or no (a boolean)");

        while (true) {

            response = scanner.nextLine();
            response = response.toLowerCase();

            if (isBoolean(response).equals("true")) {

                return true;
            } else if (isBoolean(response).equals("false")) {
                //scanner.close(); no need

                return false;
            } else {
                print("not a boolean");

                pause(500);
                print("");

                print(context);
                print("enter yes or no (a boolean)");
            }

        }

    }
}
