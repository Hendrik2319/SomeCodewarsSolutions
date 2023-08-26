package net.schwarzbaer.challenges.codewars.java;

public class BlockLetterPrinterSolution {
	/*
		6 kyu
		Block Letter Printer
		
		https://www.codewars.com/kata/6375587af84854823ccd0e90
	 */
    public static void main(String[] args) {
        System.out.println(BlockLetterPrinter.blockPrint("abcd"));
        System.out.println(BlockLetterPrinter.blockPrint("heLLo WorLD"));
    }

	public class BlockLetterPrinter {

	    private static String[] font = {
	            " AAA  BBBB   CCC  DDDD  EEEEE FFFFF  GGG  H   H IIIII JJJJJ K   K L     M   M N   N  OOO  PPPP   QQQ  RRRR   SSS  TTTTT U   U V   V W   W X   X Y   Y ZZZZZ ",
	            "A   A B   B C   C D   D E     F     G   G H   H   I       J K  K  L     MM MM NN  N O   O P   P Q   Q R   R S   S   T   U   U V   V W   W X   X Y   Y     Z ",
	            "A   A B   B C     D   D E     F     G     H   H   I       J K K   L     M M M N   N O   O P   P Q   Q R   R S       T   U   U V   V W   W  X X   Y Y     Z  ",
	            "AAAAA BBBB  C     D   D EEEEE FFFFF G GGG HHHHH   I       J KK    L     M   M N N N O   O PPPP  Q   Q RRRR   SSS    T   U   U V   V W W W   X     Y     Z   ",
	            "A   A B   B C     D   D E     F     G   G H   H   I       J K K   L     M   M N   N O   O P     Q Q Q R R       S   T   U   U V   V W W W  X X    Y    Z    ",
	            "A   A B   B C   C D   D E     F     G   G H   H   I       J K  K  L     M   M N  NN O   O P     Q  QQ R  R  S   S   T   U   U  V V  W W W X   X   Y   Z     ",
	            "A   A BBBB   CCC  DDDD  EEEEE F      GGG  H   H IIIII JJJJ  K   K LLLLL M   M N   N  OOO  P      QQQQ R   R  SSS    T    UUU    V    W W  X   X   Y   ZZZZZ "
	    };

	    public static String blockPrint(String string){
	        string = string.toUpperCase().trim();
	        if (string.isEmpty()) return "";

	        char[] chars = string.toCharArray();
	        String[] output = {"","","","","","",""};
	        for (char ch : chars)
	        {
	            int index = (int)ch - (int)'A';
	            if (index<0 || index>=26)
	            {
	                for (int i=0; i<output.length; i++)
	                    output[i] += "      ";
	            }
	            else
	            {
	                int offset = index*6;
	                for (int i=0; i<output.length; i++)
	                    output[i] += font[i].substring(offset, offset+6);
	            }
	        }

	        for (int i=0; i<output.length; i++)
	            output[i] = output[i].stripTrailing();

	        return String.join("\n",output);
	    }
	}
}
