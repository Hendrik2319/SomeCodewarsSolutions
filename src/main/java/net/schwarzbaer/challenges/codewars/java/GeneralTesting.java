package net.schwarzbaer.challenges.codewars.java;

public class GeneralTesting {
    public static void main(String[] args) {

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
