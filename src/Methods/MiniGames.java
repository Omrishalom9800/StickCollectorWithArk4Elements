package Methods;

import java.io.IOException;

//get methods
import static Methods.OtherFunctions.*;
import static Methods.RequireInput.*;
import static Methods.print.*;

//get classes
import Classes.StickStorage.*;
import Classes.Stick.*;
import Classes.Enemy.*;

public class MiniGames {

    // mini games---------

//notes
//System.in.available() will check if there's something in the current input stream
//System.in.read will pull a waiting byte out of input stream
// making a buffer will give system.in.read a place to put the input stream that I don't want

    // wack stuff mini game
    public static double wackSomething(int speed, int length) throws IOException {
        // returns a damage multiplier (which is 0,1,and 2)
        // the goal is to hit the X or near the X when cursor is on it

        print("");
        print("Type anything to stop the 'V' ");
        print("Try to stop so the 'V' lands on the 'X', landing on the small 'x' is fine to");
        print("(KEEP THE TYPING CURSOR AT THE BOTTOM OF THE CONSOLE OR IT WONT AUTO SCROLL)");
        requireString("Ready? ( type anything to start)");

        boolean didHit = false;
        int cursor = 0;
        String[] hitBar = new String[length]; // 8 characters long


        int target = (int) (Math.random() * hitBar.length); // get a random number between 0 and hitbar.length, always round down
        int[] closeTargets = new int[4]; // get all close indexs to target

        int arrayIndex = 0;

        String targetRow = "";// the visible row

        for (int i = 0; i < hitBar.length; i++) {

            if (i - target == 2 || i - target == 1 || i - target == -1 || i - target == -2) {// is it close to target
                targetRow = targetRow + "x";

                closeTargets[arrayIndex] = i;
                arrayIndex++;

            } else if (i == target) {// is it on target
                targetRow = targetRow + "X";
            } else {// is it not close
                targetRow = targetRow + "-";
            }
        }
        double damage = -1;// initializing damage (this is the thing getting returned)
        int gameI = 0; //how many times has the loop looped
        while (!didHit) {
            gameI++;

            //calculate cursor
            String cursorUI = "";
            for (int i = 0; i < hitBar.length; i++) {

                if (i == cursor) {
                    cursorUI = cursorUI + "V";
                } else {
                    cursorUI = cursorUI + " ";
                }
            }
            print("");
            print("");
            print("");
            print("(KEEP THE TYPING CURSOR AT THE BOTTOM OF THE CONSOLE OR IT WONT AUTO SCROLL)  (type anything to hit)");

            print("================================================================");
            print(cursorUI);
            print(targetRow);
            print("================================================================");

            pause(speed * 10); // difficulty, 8 is average difficulty for big length

            // starting count down
            if (gameI <= 1) {
                print("Starting: ");
                countDown(3, true);
            }


            if (System.in.available() > 0) {       // check if they typed something

                //calculate damage

                if (target - cursor == 2 || target - cursor == 1 || target - cursor == -1 || target - cursor == -2) {// is it close to target

                    damage = 1; //getting close to target does normal damage
                    print("Hit!");
                } else if (cursor == target) {// is it on target
                    damage = 2; // double damage
                    print("DoubleDamage!!");
                } else {// is it not close
                    damage = 0.5; // missed
                    print("miss..");
                }


                //close loop
                didHit = true;

                //print("hit");
                byte[] buffer = new byte[System.in.available()]; // a array that holds bytes, thats the exact size of whats in the input stream
                System.in.read(buffer); // put bytes from input stream into buffer
            }

            cursor++; // move cursor
            if (cursor > hitBar.length - 1) {
                cursor = 0;
            }

        }

        pause(500);
        return damage;
    }

    // block stuff
    public static double blockSomething(int speed, int length) throws IOException {

        // ask when to start, give intructions too
        print("");
        print("Type anything to move the '<' to other lane.");
        print("Move '<' out of the way of incoming 'X'.");
        print("(KEEP THE TYPING CURSOR AT THE BOTTOM OF THE CONSOLE OR IT WONT AUTO SCROLL)");
        requireString("Ready? ( type anything to start)");

        //length should be over 30
        int hazardAmount = 0; // made when placing obsticles
        double damageMultiplier = 0;

        int cursorYPosition = 0; // cursor position on the y axis
        int cursorXPosition = length - 1; // cursor position on the x axis

        char player = '<'; //model

        int gameI = 0; // how many times has the while loop looped

        char[] laneZero = new char[length]; // the top lane
        char[] laneOne = new char[length]; // the bottom lane

        //___________________________________ filling in the lane
        //generate empty lanes
        for (int i = 0; i < laneZero.length; i++) {
            laneOne[i] = ' ';
            laneZero[i] = ' ';
        }

        int placement = 0; // should x be on lane 1 or 0
        //hazards on empty lanes
        for (int i = 0; i < laneZero.length; i++) {

            int coolerI = i * 6;

            if (coolerI + 12 > laneZero.length - 1) {
                i = 2147483640;
            } else {
                if (placement == 0) {
                    laneZero[coolerI] = 'X';
                    hazardAmount++;
                    placement = 1;
                } else {
                    laneOne[coolerI] = 'X';
                    hazardAmount++;
                    placement = 0;
                }
            }
        }//___________________________________

        while (cursorXPosition >= 0) {//_____________________ game loop logic
            gameI++;
            if (System.in.available() > 0) { // swap lanes

                if (cursorYPosition == 0) {
                    cursorYPosition = 1;
                } else {
                    cursorYPosition = 0;
                }

                // clears input stream
                byte[] buffer = new byte[System.in.available()]; // a array that holds bytes, thats the exact size of whats in the input stream
                System.in.read(buffer); // put bytes from input stream into buffer
            }

            //make lane visuals
            String lane0 = "";
            String lane1 = "";

            if (cursorYPosition == 0) {
                for (int i = 0; i < laneZero.length; i++) {
                    if (i == cursorXPosition) {
                        lane0 = lane0 + player;
                    } else {
                        lane0 = lane0 + laneZero[i];
                    }
                    lane1 = lane1 + laneOne[i];
                }
            } else {
                for (int i = 0; i < laneOne.length; i++) {
                    if (i == cursorXPosition) {
                        lane1 = lane1 + player;
                    } else {
                        lane1 = lane1 + laneOne[i];
                    }
                    lane0 = lane0 + laneZero[i];
                }
            }


            //ui
            print("Type anything to move the '<' to other lane.");
            print("Move '<' out of the way of incoming 'X'.");
            print("(if its not moving try scrolling to the bottom of the console)");
            print("===============================================================");
            print(lane0);
            print(lane1);
            print("===============================================================");


            //take damage logic
            if (cursorYPosition == 0) {
                if (laneZero[cursorXPosition] == 'X') {
                    damageMultiplier = damageMultiplier + ((double) 1 / hazardAmount);
                    print("you got hit");
                }
            } else {
                if (laneOne[cursorXPosition] == 'X') {
                    damageMultiplier = damageMultiplier + ((double) 1 / hazardAmount);
                    print("you got hit");
                }
            }

            if (gameI <= 1) {
                print("Starting: ");
                countDown(3, true);
            }

            pause(speed * 10);
            cursorXPosition--;
        }//______________________________________

        return damageMultiplier;

    }
//

}
