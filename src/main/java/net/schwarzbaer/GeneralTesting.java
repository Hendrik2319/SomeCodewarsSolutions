package net.schwarzbaer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

@SuppressWarnings("unused")
public class GeneralTesting {
    public static void main(String[] args) {

//        charTests();
//        stringReplaceRegExTests();
//        testLineCommentRemove();

        double zinssatz_d = 0.65;
        double balance_d = 120.43;

        BigDecimal zinssatz = new BigDecimal("0.65"); // % per anno
        BigDecimal balance = new BigDecimal("120.43"); // €
        System.out.printf("zinssatz: %s%n", zinssatz);
        System.out.printf("balance: %s%n", balance);

        BigDecimal div  = zinssatz.divide(BigDecimal.valueOf(100));
        BigDecimal div2 = zinssatz.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
        BigDecimal div3 = zinssatz.divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_EVEN);
        System.out.printf("div : %s%n", div );
        System.out.printf("div2: %s%n", div2);
        System.out.printf("div3: %s%n", div3);

        System.out.printf("balance*div : %s%n", balance.multiply(div) );

        double div_d = zinssatz_d / 100;
        System.out.printf("div_d: %s%n", div_d);
        System.out.printf(Locale.ENGLISH, "div_d: %1.30f%n", div_d);
        System.out.printf("balance_d * div_d: %s%n", balance_d * div_d);

        //throw new IllegalArgumentException();
    }

    private static void stringReplaceRegExTests() {
        System.out.println("00011321".replaceAll("^0+", ""));
        System.out.println("\""+("000".replaceAll("^0+", ""))+"\"");
    }

    private static void charTests() {
        System.out.println('3' - '0');
        System.out.println((char)('0' + 2));
    }

    private static void testLineCommentRemove() {
        String str = "apples, pears # and # bananas\ngrapes\nbananas !apples";
        System.out.println(str);
        System.out.println();

        str = str.replaceAll("#[^#\\n]*\\n", "\n");
        System.out.println(str);
        System.out.println();

        str = str.replaceAll("![^!\\n]*\\n", "\n");
        System.out.println(str);
        System.out.println();

        str = str.replaceAll("#[^#\\n]*\\z", "");
        System.out.println(str);
        System.out.println();

        str = str.replaceAll("![^!\\n]*\\z", "");
        System.out.println(str);
        System.out.println();
    }
}
