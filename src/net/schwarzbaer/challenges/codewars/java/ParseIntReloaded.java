package net.schwarzbaer.challenges.codewars.java;

public class ParseIntReloaded
{
	
	public static void main(String[] args)
	{
		test("one", 1);
		test("eighteen", 18);
		test("twenty", 20);
		test("two hundred forty-six", 246);
		test("seven hundred eighty-three thousand nine hundred and nineteen", 783919);
		test("one million", 1000000);
		test("zero", 0);
	}
	
	private static void test(String numStr, int expected)
	{
		int actual = Parser.parseInt(numStr);
		if (actual == expected)
			System.out.printf("Parser.parseInt( \"%s\" ) -> %d (as expected)%n", numStr, actual);
		else
			System.err.printf("Parser.parseInt( \"%s\" ) -> %d != expected (%s)%n", numStr, actual, expected);
	}
	
	/*
	 * 
In this kata we want to convert a string into an integer. The strings simply represent the numbers in words.

Examples:

    "one" => 1
    "twenty" => 20
    "two hundred forty-six" => 246
    "seven hundred eighty-three thousand nine hundred and nineteen" => 783919

Additional Notes:

    The minimum number is "zero" (inclusively)
    The maximum number, which must be supported is 1 million (inclusively)
    The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present and in others it's not
    All tested numbers are valid, you don't need to validate them

	 */
	
	public static class Parser
	{
		private static class ParseException extends RuntimeException
		{
			private static final long serialVersionUID = 5946265494174060438L;
			ParseException(String format, Object... values) { super(String.format(format, values)); }
		}
		
		
		private static final String[] ones =
			{ "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
			
		private static final String[] aboveEqualTen =
			{ "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
			
		private static final String[] tens =
			{ null, null, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
			
		private static final String hundred  = "hundred";
		private static final String thousand = "thousand";
		private static final String million  = "million";
		private static final String and = "and";
		
		private record CutResult(String remainingStr, String valueStr) {}
		
		private static CutResult cut(String numStr)
		{
			int pos = numStr.lastIndexOf(' ');
			if (pos < 0)
				return new CutResult("", numStr);
			return new CutResult(numStr.substring(0, pos), numStr.substring(pos + 1));
		}
		
		private record ParseResult(String remainingStr, String valueStr, int value) {}
		
		private static ParseResult parse99(String numStr)
		{
			CutResult result1 = cut(numStr);
			
			if (result1.valueStr.equals(and))
				return new ParseResult(result1.remainingStr, "", 0);
			
			if (result1.valueStr.equals(hundred))
				return new ParseResult(numStr, "", 0);
			
			if (result1.valueStr.equals(thousand))
				return new ParseResult(numStr, "", 0);
			
			
			for (int i=0; i<ones.length; i++)
				if (result1.valueStr.equals(ones[i]))
					return new ParseResult(result1.remainingStr, ones[i], i);
			
			for (int i=0; i<aboveEqualTen.length; i++)
				if (result1.valueStr.equals(aboveEqualTen[i]))
					return new ParseResult(result1.remainingStr, aboveEqualTen[i], 10+i);
			
			for (int i=2; i<tens.length; i++)
				if (result1.valueStr.equals(tens[i]))
					return new ParseResult(result1.remainingStr, tens[i], 10*i);
			
			
			String[] parts = result1.valueStr.split("-");
			if (parts.length!=2)
				throw new ParseException("A 2 digit number string like \"forty-five\" expected after \"%s\" in \"%s\"", result1.remainingStr, numStr);
			
			
			int secondDigit = -1;
			for (int i=0; i<ones.length; i++)
				if (parts[1].equals(ones[i])) {
					secondDigit = i;
					break;
				}
			if (secondDigit < 0)
				throw new ParseException("Can't parse second digit \"%s\" of number string \"%s\" in \"%s\"", parts[1], result1.valueStr, numStr);

			
			int firstDigit = -1;
			for (int i=2; i<tens.length; i++)
				if (parts[0].equals(tens[i])) {
					firstDigit = i;
					break;
				}
			if (firstDigit < 0)
				throw new ParseException("Can't parse first digit \"%s\" of number string \"%s\" in \"%s\"", parts[0], result1.valueStr, numStr);
			
			return new ParseResult(result1.remainingStr, String.format("%s-%s", tens[firstDigit], ones[secondDigit]), firstDigit*10 + secondDigit);
		}
		
		private static String removeSuffix(String str, String suffix)
		{
			if (!str.endsWith(suffix))
				throw new IllegalStateException(String.format("\"%s\" expected at end of \"%s\"", suffix, str));
			return str.substring(0, str.length() - suffix.length());
		}
		
		private static ParseResult parse999(String numStr)
		{
			ParseResult result1 = parse99(numStr);
			
			if (result1.remainingStr.endsWith(" " + and))
				result1 = new ParseResult( removeSuffix( result1.remainingStr, " " + and ), result1.valueStr, result1.value);
			
				
			// expecting: "hundred eight-four" is not allowed (without "one ...") 
			if (result1.remainingStr.endsWith(" " + hundred))
			{
				String str = removeSuffix( result1.remainingStr, " " + hundred );
				CutResult result2 = cut(str);
				
				for (int i=1; i<ones.length; i++)
					if (result2.valueStr.equals(ones[i]))
					{
						int value = i*100 + result1.value;
						return new ParseResult(result2.remainingStr, String.format("<%s %s %s %s>(%d)", ones[i], hundred, and, result1.valueStr, value) , value);
					}
				
				throw new ParseException("A digit number string (like \"one\" or \"five\") expected before \"%s\" in \"%s\"", hundred, numStr);
			}
			
			return result1;
		}
		
		public static int parseInt(String numStr)
		{
			if (numStr.equals(ones[1] + " " + million))
				return 1000000;
			
			if (numStr.equals(ones[0]))
				return 0;
			
			ParseResult result1 = parse999(numStr);
			
			if (result1.remainingStr.isEmpty())
				return result1.value;
			
			if (!result1.remainingStr.endsWith(" " + thousand))
				throw new ParseException("\"%s\" expected at end of \"%s\" in \"%s\"", thousand, result1.remainingStr, numStr);
			
			String thousandStr = removeSuffix( result1.remainingStr, " " + thousand );
			
			ParseResult result2 = parse999(thousandStr);
			
			if (!result2.remainingStr.isEmpty())
				throw new ParseException("No chars expected before \"%s\" in \"%s\"", result2.valueStr, numStr);
			
			return result2.value*1000 + result1.value;
		}
	}
}
