package net.schwarzbaer.challenges.codewars.java;

public class NextBiggerNumberWithTheSameDigits
{
	/*
		4 kyu
		Next bigger number with the same digits
		
		https://www.codewars.com/kata/55983863da40caa2c900004e
		
		Create a function that takes a positive integer and returns the next bigger number that can be formed by rearranging its digits. For example:
		
		  12 ==> 21
		 513 ==> 531
		2017 ==> 2071
		
		If the digits can't be rearranged to form a bigger number, return -1 (or nil in Swift, None in Rust):
		
		  9 ==> -1
		111 ==> -1
		531 ==> -1
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
        test(   21,    12);
        test(  531,   513);
        test( 2071,  2017);
        test(  441,   414);
        test(  414,   144);
        test(19009, 10990);
        test(   -1,     9);
        test(   -1,   111);
        test(   -1,   531);
	}
	
	private static void test(int expected, int n)
	{
		long result = Kata.nextBiggerNumber(n);
		if (result == expected)
			System.out.printf("Kata.nextBiggerNumber( %d ) -> %d (as expected)%n", n, result);
		else
			System.err.printf("Kata.nextBiggerNumber( %d ) -> %d != expected %d%n", n, result, expected);
	}

	public class Kata
	{
		public static long nextBiggerNumber(long n)
		{
			// max(long) == 2^64 < 10^20
			long[] digits = new long[20];
			
			// find digit[pos], with digit[pos] < digit[pos-1]
			boolean swapDigits = false;
			int pos = 0;
			for (; n > 0; pos++)
			{
				digits[pos] = n % 10;
				n = n / 10;
				if (pos > 0 && digits[pos] < digits[pos - 1])
				{
					swapDigits = true;
					break;
				}
			}
			
			if (!swapDigits)
				return -1; // all digits are in ascending order from lowest position up to highest
				
			if (pos == 1)
				// swap the two lowest digits
				return n * 100 + digits[0] * 10 + digits[1];
			
			// swap digits[pos] and digits[i], where digits[i] is the next higher digit to digits[pos] within digits[pos-1 .. 0]
			long highestChangedDigit = 0;
			for (int i = 0; i < pos; i++)
				if (digits[pos] < digits[i])
				{
					highestChangedDigit = digits[i];
					digits[i] = digits[pos];
					break;
				}
			
			// rebuild n:  remaining n  +  highestChangedDigit  +  digits[pos-1 .. 0] in reversed order
			n = n * 10 + highestChangedDigit;
			for (int i = 0; i < pos; i++)
				n = n * 10 + digits[i];
			
			return n;
		}
	}
}
