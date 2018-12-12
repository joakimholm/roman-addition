package se.adaptiv.romanadd;

import java.util.*;

/**
 * Adds roman numerals.
 */
class RomanAdder {

    /**
     * Adds to roman numerals without using conversion to Arabic number representation.
     *
     * @param numeral1 first numeral term, from I (1) up to MMMCMXCIX (3998)
     * @param numeral2 second numeral term, from I (1) up to MMMCMXCIX (3998)
     * @return numeral sum, can be anything from II (2) up to MMMCMXCIX (3999)
     */
    static String add(String numeral1, String numeral2) {
        return normalize(merge(denormalize(numeral1), denormalize(numeral2)));
    }

    /**
     * Denormalizes a roman numeral. It will replace all digits created using subtraction
     * with four of a kind instead, e.g. IV (4) becomes IIII.
     */
    private static String denormalize(String numeral) {
        String result = numeral;
        Map<String, String> denormalizationTable = buildDenormalizationTable();

        for (Map.Entry<String, String> entry : denormalizationTable.entrySet()) {
            if (result.contains(entry.getKey())) {
                result = result.replace(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    private static Map<String, String> buildDenormalizationTable() {
        Map<String, String> result = new LinkedHashMap<>();
        result.put("CM", "DCCCC");
        result.put("CD", "CCCC");
        result.put("XC", "LXXXX");
        result.put("XL", "XXXX");
        result.put("IX", "VIIII");
        result.put("IV", "IIII");
        return result;
    }

    /**
     * Merges two denormalized roman numerals together.
     */
    private static String merge(String numeral1, String numeral2) {
        return sortNumeralLetters(numeral1 + numeral2);
    }

    /**
     * Sorts a string of roman numeral letters according to their values.
     * Highest value letters first, e.g. X (10) comes before V (5) which comes before I (1).
     */
    private static String sortNumeralLetters(String unsortedLetters) {
        Character[] letterArray = RomanUtils.str2CharArray(unsortedLetters);
        RomanUtils.sortArray(letterArray, buildRomanAlphabet());
        return RomanUtils.charArr2Str(letterArray);
    }

    /**
     * Build ordered list of all letters in the Roman number system.
     */
    private static List<Character> buildRomanAlphabet() {
        List<Character> alphabet = new ArrayList<>(7);
        alphabet.add(0, 'I');
        alphabet.add(1, 'V');
        alphabet.add(2, 'X');
        alphabet.add(3, 'L');
        alphabet.add(4, 'C');
        alphabet.add(5, 'D');
        alphabet.add(6, 'M');
        return alphabet;
    }

    /**
     * Normalizes roman numerals back from denormalized form, e.g. VIV to IX.
     */
    private static String normalize(String denormalized) {
        String result = denormalized;
        Map<String, String> normalizationTable = buildNormalizationTable();

        for (Map.Entry<String, String> entry : normalizationTable.entrySet()) {
            if (result.contains(entry.getKey())) {
                result = result.replace(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    private static Map<String, String> buildNormalizationTable() {
        Map<String, String> result = new LinkedHashMap<>();
        result.put("IIIII", "V");
        result.put("IIII", "IV");
        result.put("VV", "X");
        result.put("VIV", "IX");
        result.put("XXXXX", "L");
        result.put("XXXX", "XL");
        result.put("LL", "C");
        result.put("LXL", "XC");
        result.put("CCCCC", "D");
        result.put("CCCC", "CD");
        result.put("DD", "M");
        result.put("DCD", "CM");
        return result;
    }
}
