package net.schwarzbaer.challenges.codewars.java;

@SuppressWarnings("unused")
public class GeneralTesting {
    public static void main(String[] args) {

        System.out.println('3' - '0');
        System.out.println((char)('0' + 2));

        System.out.println("00011321".replaceAll("^0+", ""));
        System.out.println("\""+("000".replaceAll("^0+", ""))+"\"");

        throw new IllegalArgumentException();
        //testLineCommentRemove();
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
