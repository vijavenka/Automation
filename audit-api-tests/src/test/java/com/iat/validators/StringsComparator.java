package com.iat.validators;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;

public class StringsComparator {


    /*
    currentElement.compareTo(previousElement)

    The value 0 if the previousElement is a string lexicographically == to currentElement string;
    a value < 0 if the previousElement is a string lexicographically > than currentElement string;
    a value > 0 if the previousElement is a string lexicographically < than currentElement string.

    previousElement == currentElement    0
    previousElement > currentElement    -1
    previousElement < currentElement    +1
    */


    public void validateStringsOrder(String[] array, String ascending, String sortedFieldName) throws FileNotFoundException {
        String previous;
        String current;

        if (ascending.equals("false")) {
            for (int i = 1; i < array.length; i++) {
                previous = array[i - 1];
                current = array[i];
                assertTrue("Sort by: " + sortedFieldName + "\nElement [" + (i - 1) + "]: " + previous + " and [" + i + "]: " + current + " are sorted ascending but should be descending", current.toLowerCase().compareTo(previous.toLowerCase()) <= 0);
            }
        } else {
            for (int i = 1; i < array.length; i++) {
                previous = array[i - 1];
                current = array[i];
                assertTrue("Sort by: " + sortedFieldName + "\nElement [" + (i - 1) + "]: " + previous + " and [" + i + "]: " + current + " are sorted descending but should be ascending", current.toLowerCase().compareTo(previous.toLowerCase()) >= 0);
            }
        }
    }
}