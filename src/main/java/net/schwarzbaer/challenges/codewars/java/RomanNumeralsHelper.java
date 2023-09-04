package net.schwarzbaer.challenges.codewars.java;

import java.util.Map;

public class RomanNumeralsHelper {

    /*
        4 kyu
        Roman Numerals Helper
        https://www.codewars.com/kata/51b66044bce5799a7f000003
     */

    public static void main(String[] args) {
        TestTools.testIntOutput( "I", 1, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("II", 2, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.test(1,  "I", "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(2, "II", "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
    }

    public static class RomanNumerals {

        private static Map<Character,Integer> literal2num = Map.of(
                'I', 1, 'V', 5,
                'X', 10, 'L', 50,
                'C', 100, 'D', 500,
                'M', 1000
                );

        /*
        private static Map<Integer, Character> num2literal = Map.of(
                1,'I',  'V', 5,
                'X', 10, 'L', 50,
                'C', 100, 'D', 500,
                'M', 1000,
                );
        */
        public static String toRoman(int n) {
            return "I";
        }

        public static int fromRoman(String romanNumeral) {
            int n = 0;
            int count = 0;
            char lastCh = ' ';

            char[] chars = romanNumeral.toCharArray();
            for (char ch : chars) {

            }

            return n;
        }
    }
}
