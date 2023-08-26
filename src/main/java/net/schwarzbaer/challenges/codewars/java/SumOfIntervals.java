package net.schwarzbaer.challenges.codewars.java;

import java.util.Arrays;
import java.util.Comparator;

public class SumOfIntervals
{
	/*
		4 kyu
		Sum of Intervals
		
		https://www.codewars.com/kata/52b7ed099cdc285c300001cd
		
		Your algorithm should be able to handle large intervals.
		All tested intervals are subsets of the range [-1000000000, 1000000000]
	 */
	public static void main(String[] args)
	{
		assertEquals(  0, new int[][] {} );
		assertEquals(  0, new int[][] { {4, 4}, {6, 6}, {8, 8} } );
		assertEquals(  9, new int[][] { {1, 2}, {6, 10}, {11, 15} } );
		assertEquals(  7, new int[][] { {1, 4}, {7, 10}, {3, 5} } );
		assertEquals( 19, new int[][] { {1, 5}, {10, 20}, {1, 6}, {16, 19}, {5, 11} } );
		assertEquals( 100000030, new int[][] { {0, 20}, {-100000000, 10}, {30, 40} } );
	}
	
	private static void assertEquals(int expected, int[][] intervals)
	{
		String intervalsStr = toString(intervals);
		int sumIntervals = Interval.sumIntervals( intervals );
		if (expected == sumIntervals)
			System.out.printf("expected: %11d == sum: %11d <- %s%n", expected, sumIntervals, intervalsStr);
		else
			System.err.printf("expected: %11d != sum: %11d <- %s%n", expected, sumIntervals, intervalsStr);
	}

	private static String toString(int[][] intervals)
	{
		return String.format("[ %s ]", String.join(", ",Arrays.stream(intervals).map(arr -> Arrays.toString(arr)).toList()));
	}

	public class Interval {

	    public static int sumIntervals(int[][] intervals) {
	        Arrays.sort(intervals, Comparator.<int[],Integer>comparing(interval -> interval[0]).thenComparing(interval -> interval[1]));
			
			int sum = 0;
			
			int[] lastInterval = null;
			for (int[] interval : intervals)
			{
				if (lastInterval == null)
				{
					// initial case
					lastInterval = new int[] { interval[0], interval[1] };
				}
				else
				{
					if (interval[0] < lastInterval[1])
					{
						// last interval overlaps with current interval
						lastInterval[1] = Math.max(interval[1], lastInterval[1]);
					}
					else
					{
						// no overlap -> new interval
						sum += lastInterval[1]-lastInterval[0];
						lastInterval[0] = interval[0];
						lastInterval[1] = interval[1];
					}
				}
			}
			if (lastInterval != null)
			{
				sum += lastInterval[1]-lastInterval[0];
				lastInterval = null;
			}
			
	        return sum;
	    }
	}
}
