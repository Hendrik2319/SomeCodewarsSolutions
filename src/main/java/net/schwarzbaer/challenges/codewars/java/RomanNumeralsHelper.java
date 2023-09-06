package net.schwarzbaer.challenges.codewars.java;

import java.util.Map;

public class RomanNumeralsHelper {

    /*
        4 kyu
        Roman Numerals Helper
        https://www.codewars.com/kata/51b66044bce5799a7f000003
     */

    public static void main(String[] args) {
        TestTools.testIntOutput("I"        ,    1, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("V"        ,    5, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("X"        ,   10, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("L"        ,   50, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("C"        ,  100, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("D"        ,  500, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("M"        , 1000, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("III"      ,    3, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("VIII"     ,    8, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("IV"       ,    4, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("IX"       ,    9, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("MCMLXXXIV", 1984, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("MMCMXCIX" , 2999, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.testIntOutput("MMMCMXCIX", 3999, "%s", RomanNumerals::fromRoman, "RomanNumerals.fromRoman");
        TestTools.test(    1, "I"        , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(    5, "V"        , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(   10, "X"        , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(   50, "L"        , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(  100, "C"        , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(  500, "D"        , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test( 1000, "M"        , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(    3, "III"      , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(    8, "VIII"     , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(    4, "IV"       , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test(    9, "IX"       , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test( 1984, "MCMLXXXIV", "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test( 2999, "MMCMXCIX" , "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
        TestTools.test( 3999, "MMMCMXCIX", "%d", "%s", RomanNumerals::toRoman, "RomanNumerals.toRoman", String::equals);
    }

    public static class RomanNumerals {

        private static final Map<Character,Integer> literal2num = Map.of(
                'I', 1, 'V', 5,
                'X', 10, 'L', 50,
                'C', 100, 'D', 500,
                'M', 1000
                );

        public static String toRoman(int n) {
            String str = "";
            str = getBlock(n % 10, "I", "V", "X") + str; n /= 10;
            str = getBlock(n % 10, "X", "L", "C") + str; n /= 10;
            str = getBlock(n % 10, "C", "D", "M") + str; n /= 10;
            str = "M".repeat(n) + str;
            return str;
        }

        private static String getBlock(int n, String I, String V, String X) {
            switch (n) {
                case 0 : return "";
                case 1 : return I;
                case 2 : return I+I;
                case 3 : return I+I+I;
                case 4 : return I+V;
                case 5 : return V;
                case 6 : return V+I;
                case 7 : return V+I+I;
                case 8 : return V+I+I+I;
                case 9 : return I+X;
            }
            return null;
        }

        public static int fromRoman(String romanNumeral) {
            int sum = 0;
            int block = 0;
            int lastCharValue = 0;

            char[] chars = romanNumeral.toCharArray();
            for (char ch : chars) {
                int n = literal2num.get(ch);
                if (lastCharValue == n)
                    block += n;
                else {
                    if (lastCharValue > n)
                        sum += block;
                    else
                        sum -= block;
                    block = n;
                }
                lastCharValue = n;
            }
            sum += block;

            return sum;
        }
    }
}
