package net.schwarzbaer.challenges.codewars.java;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TestTools {

    public static <InputType, OutputType> void test(
            InputType input, OutputType expected, String formatStrInput, String formatStrOutput,
            Function<InputType, OutputType> workMethod, String methodLabel,
            BiFunction<OutputType,OutputType,Boolean> equals
    ) {
        OutputType actual = workMethod.apply( input );
        if (equals.apply(actual, expected))
            System.out.printf("%s( "+formatStrInput+" ) -> "+formatStrOutput+" (as expected)%n", methodLabel, input, actual);
        else
            System.err.printf("%s( "+formatStrInput+" ) -> "+formatStrOutput+", but expected "+formatStrOutput+"%n", methodLabel, input, actual, expected);
    }

    public static <InputType> void testIntOutput(
            InputType input, int expected, String formatStrInput,
            Function<InputType, Integer> workMethod, String methodLabel
    ) {
        test(
                input, expected, formatStrInput, "%d",
                workMethod, methodLabel,
                (n1,n2) -> (n1==null && n2==null) || (n1!=null && n2!=null && n1.intValue() == n2.intValue())
        );
    }
}
