package net.schwarzbaer.challenges.codewars.java;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Vector;

public class LastDigitOfALargeNumber
{
	/*
	 	5 kyu
		Last digit of a large number
		
		https://www.codewars.com/kata/5511b2f550906349a70004e1
		
		Define a function that takes in two non-negative integers a and b and returns the last decimal digit of a^b.
		Note that aaa and bbb may be very large!
		
		For example, the last decimal digit of 9^7 is 9, since 9^7=4782969.
		The last decimal digit of (2^200)^(2^300), which has over 10^92 decimal digits, is 6.
		Also, please take 0^0 to be 1.

		You may assume that the input will always be valid.
	 */
	
	public static void main(String[] args)
	{
		/*
		lastDigit(new BigInteger("4" ), new BigInteger("1"          )) // returns 4
		lastDigit(new BigInteger("4" ), new BigInteger("2"          )) // returns 6
		lastDigit(new BigInteger("9" ), new BigInteger("7"          )) // returns 9
		lastDigit(new BigInteger("10"), new BigInteger("10000000000")) // returns 0
		assertEquals(8, Kata.lastDigit(new BigInteger("2"), new BigInteger("7")));
		assertEquals(4, Kata.lastDigit(new BigInteger("4"), new BigInteger("1")));
		assertEquals(6, Kata.lastDigit(new BigInteger("4"), new BigInteger("2")));
		assertEquals(9, Kata.lastDigit(new BigInteger("9"), new BigInteger("7")));
		 */
		
		
		Kata.lastOfPowSeries.forEach((a,series) -> {
			System.out.printf("%d: %s%n", a, series);
		});
		
		test( "2", "7", 8);
		test( "4", "1", 4);
		test( "4", "2", 6);
		test( "9", "7", 9);
		test("10", "10000000000", 0);
	}
	
	private static void test(String aStr, String bStr, int expected)
	{
		BigInteger a = new BigInteger(aStr);
		BigInteger b = new BigInteger(bStr);
		int lastDigit = Kata.lastDigit(a, b);
		if (lastDigit == expected)
			System.out.printf("lastDigit( %s, %s ) -> %d (as expected)%n", aStr, bStr, lastDigit);
		else
			System.err.printf("lastDigit( %s, %s ) -> %d != expected %d%n", aStr, bStr, lastDigit, expected);
	}

	public class Kata
	{
		private static final HashMap<Integer,Vector<Integer>> lastOfPowSeries = computeLastOfPowSeries();
		
		public static int lastDigit(BigInteger a, BigInteger b)
		{
		    int lastOfA = a.remainder(new BigInteger("10")).intValueExact();
			if (b.equals(new BigInteger("0"))) return 1;
			if (lastOfA==0) return 0;
			if (lastOfA==1) return 1;
			if (b.equals(new BigInteger("1"))) return lastOfA;
			
			Vector<Integer> series = lastOfPowSeries.get(lastOfA);
		    BigInteger lengthOfSeries = new BigInteger(Integer.toString(series.size()));
		    int posInSeries = b.remainder(lengthOfSeries).intValueExact();
			return series.get(posInSeries);
		}

		private static HashMap<Integer, Vector<Integer>> computeLastOfPowSeries()
		{
			HashMap<Integer, Vector<Integer>> map = new HashMap<>();
			for (int i=2; i<10; i++)
			{
				Vector<Integer> series = new Vector<>();
				int val = 1;
				for (int j=1; j<10; j++)
				{
					val = (val*i) % 10;
					if (series.contains(val)) break;
					series.add(val);
				}
				
				if (series.size()>1)
				{
					Integer last = series.remove(series.size()-1);
					series.insertElementAt(last, 0);
				}
				map.put(i, series);
			}
			return map;
		}
	}
}
