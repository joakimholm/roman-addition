package se.adaptiv.romanadd;

import java.util.Arrays;
import java.util.List;

/**
 * Generic Roman utils.
 */
class RomanUtils {
    /**
     * Converts a string to an array of Characters.
     */
    static Character[] str2CharArray(String str) {
        Character[] tempArray = new Character[str.length()];
        for (int i = 0; i < str.length(); i++) {
            tempArray[i] = str.charAt(i);
        }
        return tempArray;
    }

    /**
     * Sorts array of letters in the reverse order of the alphabet given.
     */
    static void sortArray(Character[] unsorted, List<Character> alphabet) {
        Arrays.sort(unsorted, (c1, c2) -> alphabet.indexOf(c2) - alphabet.indexOf(c1));
    }

    /**
     * Converts a Character array to a String.
     */
    static String charArr2Str(Character[] letterArray) {
        StringBuilder sb = new StringBuilder(letterArray.length);
        for (Character c : letterArray) {
            sb.append(c.charValue());
        }
        return sb.toString();
    }
}
