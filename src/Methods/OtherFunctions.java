package Methods;

import static Methods.print.*;

public class OtherFunctions {

    public static void printSameLine(String text) {
        System.out.print(text);
    }

    // briefly pause program
    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            print("pause function errored");
        }
    }

    // is this string a kind of boolean?
    public static String isBoolean(String text) {
        // converts a word into a boolean but as a string, because theyre three outputs.
        // can return "true" "false", "invalid"
        text = text.toLowerCase();

        String[] goodWords = {"yes", "true", "fine", "sure", "ok", "okay", "yah", "ya", "tung tung tung sahur", "pls", "please", "why not"};
        String[] badWords = {"no", "false", "nah", "na", "i dont like you", "i hate you", "i dont wanna", "meh"};

        for (String word : goodWords) {
            if (text.equals(word)) {
                return "true";
            }
        }

        for (String word : badWords) {
            if (text.equals(word)) {
                return "false";
            }
        }

        return "invalid";
    }

    //random name
    public static String randomName() {

        String[] nameList;
        nameList = new String[]{
                "gorden", "bob", "fobeo", "richard", "tim", "timmmy", "john", "joe",
                "mikey", "dutch", "andrew", "james", "brady", "jorge", "ian", "braxton",
                "collins", "Norah", "dane", "salem", "evan", "cesar", "williams", "reed", "shiloh", "corbin", "peyton", "colt",
                "hank", "madden", "tung tung tung", "garry", "brendan", "nola", "clay", "tyson", "luka",
                "gould", "kane", "remy", "landen", "erik", "gill", "biggie", "smalls", "martin", "jamir", "sam",
                "donald", "slone", "lu", "wall", "jett", "norris", "olson", "ashton", "reece", "miller", "tru", "march",
                "carson", "sahEvil", "sahur", "randel", "ben", "benny", "chase", "bo", "peep", "sim", "sam", "ken", "michel",
                "holt", "bruce", "lee", "aden", "paris", "page", "riley", "cassidy", "carter"
        };

        String name = nameList[randomNumber(0, nameList.length)];

        return name;
    }


    public static String randomLastName() {

        String[] nameList;
        nameList = new String[]{
                "fobeo", "quavoius", "timmmy", "joe", "mr small",
                "bad guy", "meter", "james", "brady", "jorge", "ian", "braxton",
                "collins", "Norah", "dane", "salem", "evan", "cesar", "williams", "reed",
                "brendan", "nola", "clay", "reader", "tyson", "luka", "west",
                "cook", "biggie", "smalls", "cruz", "jamir", "sam", "wahsington",
                "donald", "Mc", "sir", "wall", "miller", "tru", "march",
                "sahEvil", "bo", "peep", "sim", "ken", "grey",
                "little", "byrd", "ross", "lee", "page", "philips", "carter"
        };

        String name = nameList[randomNumber(0, nameList.length)];

        return name;
    }


    public static String randomStickName() {

        String[] nameList;
        nameList = new String[]{
                "Apple", "Ball", "Chair", "Door", "Engine", "Fork", "Glass", "Hat", "Ink", "Jacket",
                "Kettle", "Lamp", "Map", "Notebook", "Pen", "Quilt", "Ring", "Spoon", "Table", "Umbrella",
                "Vase", "Watch", "Xylophone", "Yarn", "Zipper", "Book", "Clock", "Desk", "Eraser", "Fan",
                "Glove", "Key", "Lock", "Mug", "Nail", "Pencil", "Ruler", "1Shoe", "Sock", "Tape",
                "Box", "Bag", "Bottle", "Bowl", "Brush", "Candle", "Coin", "Comb", "Couch", "Cup",
                "Dice", "Flag", "Frame", "Hammer", "Kite", "Mirror", "Pillow", "Radio", "Soap", "Towel",
                "tung tung", "bat", "cool"
        };

        String name = nameList[randomNumber(0, nameList.length)];

        return name + " stick";
    }


    //random Number
    public static int randomNumber(int min, int max) {

        double number = -1;
        number = Math.random() * (max - min);
        return (int) number + min;

    }

    // flip a coin
    public static boolean flipACoin() {

        int bool = randomNumber(0, 2);
        if (bool == 0) {
            return true;
        } else {
            return false;
        }
    }

    // pause and play a countdown on this line
    public static void countDown(int countDown, boolean printStuff) {
        int time = countDown;
        for (int i = 0; i < countDown; i++) {

            if (printStuff) {
                printSameLine(time + ", ");
            }
            time--;
            pause(1000);
        }

        if (printStuff) { // add an extra second,  if displaying ui
            printSameLine("0!");
            pause(1500); // make it a little for suspence
        }

    }



}
