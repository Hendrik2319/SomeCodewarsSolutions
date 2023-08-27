package net.schwarzbaer.challenges.codewars.java;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DecodeTheMorseCode
{
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		test("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011", "HEY JUDE");
	}
	
	private static void test(String bits, String expected)
	{
		String morseCode = MorseCodeDecoder2.decodeBits(bits);
		String decodedText = MorseCodeDecoder2.decodeMorse(morseCode);
		
		System.out.printf("bits:%n   \"%s\"%n", bits);
		System.out.printf("-> morseCode:%n   \"%s\"%n", morseCode);
		System.out.printf("-> decodedText:%n   \"%s\"%n", decodedText);
		if (expected.equalsIgnoreCase(decodedText))
			System.out.printf("expected:%n   \"%s\"%n", expected);
		else
			System.err.printf("!= expected:%n   \"%s\"%n", expected);
	}

	public class MorseCodeDecoder2 {
	  public static String decodeBits(String bits) {
	    return scanBits(bits);
	    //return ".";
	  }

	  public static String decodeMorse(String morseCode) {
	    return morseCode;
	    //return MorseCode.get(morseCode);
	  }

	  private static String scanBits(String bits) {
	    char[] chars = bits.toCharArray();

	    HashMap<Integer,Integer> ones  = new HashMap<>();
	    HashMap<Integer,Integer> zeros = new HashMap<>();

	    char lastChar = '?';
	    int lastBlockLength = 0;
	    for (char ch : chars)
	    {
	      if (ch==lastChar)
	        lastBlockLength++;
	      else
	      {
	        if (lastChar=='0')
	          incMapEntry(zeros, lastBlockLength);
	        else if (lastChar=='1')
	          incMapEntry(ones, lastBlockLength);
	        lastChar = ch;
	        lastBlockLength = 1;
	      }
	    }
	    
	    return String.format("bits: \"%s\"%n-> ones : { %s }%n-> zeros: { %s }%n", bits, toString(ones), toString(zeros));
	  }
	  
	  private static String toString(HashMap<Integer,Integer> map) {
	    return map.entrySet().stream()
	      .sorted(Comparator.comparing(Map.Entry::getKey))
	      .map(entry -> String.format("%d->%d", entry.getKey(), entry.getValue()))
	      .collect(Collectors.joining(", "));
	  }
	  
	  private static void incMapEntry(HashMap<Integer,Integer> map, int key) {
	    Integer n = map.get(key);
	    if (n==null)
	      map.put(key, 1);
	    else
	      map.put(key, n+1);
	  }
	}
	
	public class MorseCodeDecoder1
	{
	  public static String decode(String morseCode)
	  {
	    morseCode = morseCode.trim();
	    
	    String decodedText = "";
	    
	    String[] encodedWords = morseCode.split("   ");
	    for (String encodedWord : encodedWords)
	    {
	      if (!decodedText.isEmpty())
	        decodedText += " ";
	      
	      String[] encodedChars = encodedWord.split(" ");
	      for (String encodedChar : encodedChars)
	      {
	        String decodedChar = MorseCode.get(encodedChar);
	        decodedText += decodedChar;
	      }
	    }
	    
	    return decodedText;
	  }
	}
	
	private static class MorseCode
	{

		public static String get(String encodedChar)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
