package net.schwarzbaer.challenges.codewars.java;

import java.util.*;

public class TheObservedPIN {
    /*
        4 kyu
        The observed PIN
        https://www.codewars.com/kata/5263c6999e0f40dee200059d
     */

    public static void main(String[] args) {
        test("8", new String[]{"5", "7", "8", "9", "0"});
        test("11", new String[]{"11", "21", "41", "12", "22", "42", "14", "24", "44"});
        test("369", new String[]{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"});
    }

    private static void test(String number, String[] expected) {
        List<String> found = ObservedPin.getPINs(number);
        Vector<String> foundVars = new Vector<>(found);
        Vector<String> expectedVars = new Vector<>(Arrays.asList(expected));
        if (foundVars.size() != expectedVars.size()) {
            System.err.printf("test(%s) -> different size: %d found, %d expected%n", number, foundVars.size(), expectedVars.size());
            return;
        }

        foundVars.sort(null);
        expectedVars.sort(null);
        for (int i=0; i<foundVars.size(); i++)
            if (!foundVars.get(i).equals(expectedVars.get(i))) {
                System.err.printf("test(%s) -> different string at pos. %d: \"%s\" found, \"%s\" expected%n", number, i, foundVars.get(i), expectedVars.get(i));
                System.err.printf("   found   : %s%n", foundVars);
                System.err.printf("   expected: %s%n", expectedVars);
                return;
            }

        System.out.printf("test(%s) passed%n", number);
    }

    public static class ObservedPin {

        private static final HashMap<Character,Character[]> possibleDigits = ObservedPin.generate(new char[][] {
                { '1','2','3' },
                { '4','5','6' },
                { '7','8','9' },
                { '#','0','#' }
        });

        private static HashMap<Character,Character[]> generate(char[][] keypad) {
            HashMap<Character, Character[]> map = new HashMap<>();
            for (int row=0; row<keypad.length; row++)
                for (int col=0; col<keypad[row].length; col++) {
                    char ch = keypad[row][col];
                    if (Character.isDigit(ch)) {
                        Vector<Character> chars = new Vector<>();
                        chars.add(ch);
                        addKey(keypad, row+1, col  , chars);
                        addKey(keypad, row-1, col  , chars);
                        addKey(keypad, row  , col+1, chars);
                        addKey(keypad, row  , col-1, chars);
                        map.put(ch, chars.toArray(Character[]::new));
                    }
                }
            return map;
        }

        private static void addKey(char[][] keypad, int row, int col, Vector<Character> chars) {
            if (row<0 || keypad     .length<=row) return;
            if (col<0 || keypad[row].length<=col) return;
            char ch = keypad[row][col];
            if (Character.isDigit(ch))
                chars.add(ch);
        }

        public static List<String> getPINs(String observed) {
            char[] keys = observed.toCharArray();
            Vector<String> variations = new Vector<>();
            if (keys.length>0)
                findVariations(keys, 0, new char[keys.length], variations);
            return variations;
        } // getPINs

        private static void findVariations(char[] keys, int pos, char[] currentVariation, Vector<String> variations) {
            if (pos >= keys.length) {
                variations.add(String.valueOf(currentVariation));
                return;
            }

            Character[] chars = possibleDigits.get(keys[pos]);
            if (chars==null)
                throw new IllegalArgumentException();
            for (char ch : chars) {
                currentVariation[pos] = ch;
                findVariations(keys, pos+1, currentVariation, variations);
            }
        }
    } // ObservedPin

}
