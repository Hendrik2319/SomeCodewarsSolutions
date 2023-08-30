package net.schwarzbaer.challenges.codewars.java;

import java.util.List;
import java.util.Vector;

public class HowManyNumbersIII {

    public static void main(String[] args) {

        System.out.printf("(10,3) -> %s%n", HowManyNumbers.findAll(10,3, 1));
        System.out.printf("(10,3) -> %s%n", HowManyNumbers.findAll(10,3));
    }


    static class HowManyNumbers
    {
        public static List<Long> findAll(final int sumDigits, final int numDigits)
        {
            Vector<Long> list = findAll(sumDigits, numDigits, 1);
            if (list==null || list.isEmpty())
                return List.of();

            return List.of( (long)list.size(), list.firstElement(), list.lastElement() );
        }

        public static Vector<Long> findAll(final int sumDigits, final int numDigits, int lowestDigit)
        {
            if (numDigits   <= 0         ) return null;
            if (sumDigits   < lowestDigit) return null;
            if (numDigits*9 < sumDigits  ) return null;
            // ==>.   lowestDigit <= sumDigits <= numDigits*9

            long f = 1;
            for (int i=1; i<numDigits; i++)
                f *= 10;

            Vector<Long> resultList = new Vector<>();

            if (numDigits==1)
                resultList.add((long)sumDigits);

            else
                for (int d=lowestDigit; d<=9; d++)
                {
                    Vector<Long> sublist = findAll(sumDigits-d, numDigits-1, d);
                    if (sublist!=null)
                        for (long n : sublist)
                            resultList.add( d*f + n );
                }

            return resultList;
        }
    }
}
