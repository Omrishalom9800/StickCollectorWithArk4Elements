package Classes;

import java.util.ArrayList;

//get methods
import static Methods.OtherFunctions.*;
import static Methods.MiniGames.*;
import static Methods.RequireInput.*;
import static Methods.print.*;

//get classes
import Classes.Stick.*;
import Classes.Enemy.*;

public class StickStorage {
    public static class stickStorage { // there should only be one of these and action function could be put here

        //metadata-----------
        private final ArrayList<stick> storage = new ArrayList<>(); // stores reference to new array list
        //  .add(value)    .get(index)    .remove(index)    .size()

        private stick primary = null;


        // constructor--------
        public stickStorage() {

            stick fists = new stick("fists", 9999, 2, "default");
            primary = fists;
            storage.add(fists);
        }


        // functions and stuff-------------

        public void addStick(stick stick) {
            storage.add(stick);
        }

        public void removeStick(int index) {
            storage.remove(index);
        }

        //search functions

        public ArrayList<stick> getByName(String name) { // gets sticks by comparing name (can find multiple sticks)

            ArrayList<stick> collection = new ArrayList<>(); //the list of sticks that's being returned

            name = name.toLowerCase();

            for (int i = 0; i < storage.size(); i++) { // traverse through all sticks in storage

                stick reference = storage.get(i); // current stick reference
                String stickName = reference.getName().toLowerCase(); // current stick name and make it lower case

                if (stickName.equals(name)) { // compare names
                    collection.add(reference); // add to collection
                }
            }
            return collection;
        }

        public int getIndexByReference(stick stick) { // gets the index of a stick

            for (int i = 0; i < storage.size(); i++) { // traverse through all sticks in storage

                stick currentIndex = storage.get(i); // current stick reference

                if (stick == currentIndex) { // compare references
                    return i;
                }
            }
            return -1;
        }

        public ArrayList<stick> getStrongest() { // gets sticks by comparing name (can find multiple sticks)

            ArrayList<stick> collection = new ArrayList<>(); //the list of sticks thats being returned

            if (storage.isEmpty()) {
                return collection;
            } else {
                collection.add(storage.get(0));
            }

            for (int i = 1; i < storage.size(); i++) { // traverse through all sticks in storage

                stick reference = storage.get(i); // current stick reference

                if (reference.getStrength() > collection.get(0).getStrength()) { // compare strength
                    collection.clear(); //replace with stronger stick
                    collection.add(reference);
                } else if (reference.getStrength() == collection.get(0).getStrength()) {
                    collection.add(reference); // add equal sticks
                }
            }
            return collection;
        }

        public ArrayList<stick> getStiffest() { // gets sticks by comparing name (can find multiple sticks)

            ArrayList<stick> collection = new ArrayList<>(); //the list of sticks that's being returned

            if (storage.isEmpty()) {
                return collection;
            } else {
                collection.add(storage.get(0));
            }

            for (int i = 1; i < storage.size(); i++) { // traverse through all sticks in storage

                stick reference = storage.get(i); // current stick reference

                if (reference.getStiffness() > collection.get(0).getStiffness()) { // compare strength
                    collection.clear(); //replace with stiffer stick
                    collection.add(reference);
                } else if (reference.getStiffness() == collection.get(0).getStiffness()) {
                    collection.add(reference); // add equal sticks
                }
            }
            return collection;
        }

        //other functions

        public void printStats(stick stick) { //print the stats of a stick by its reference in a fancy way

            print("---" + stick.getName() + " Stat Page---");
            print("Name: " + stick.getName());
            print("Stiffness: " + stick.getStiffness());
            print("Strength: " + stick.getStrength());
            print("Value: " + stick.getValue());
            print("stick creation id: " + stick.getId());
            print("--------------");
        }


        // gets and sets-------------
        //gets--

        public ArrayList<stick> getStorage() {
            return storage;
        } // returns the reference

        public stick getPrimary() {
            return primary;
        }

        //sets--

        public void setPrimary(stick stick) {
            primary = stick;
        }

    }
}
