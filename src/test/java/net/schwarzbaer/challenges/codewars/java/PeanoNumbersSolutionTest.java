package net.schwarzbaer.challenges.codewars.java;

import net.schwarzbaer.challenges.codewars.java.PeanoNumbersSolution.PeanoNumbers.Peano.Succ;
import net.schwarzbaer.challenges.codewars.java.PeanoNumbersSolution.PeanoNumbers.Peano.Zero;
import org.junit.jupiter.api.Test;

import static net.schwarzbaer.challenges.codewars.java.PeanoNumbersSolution.PeanoNumbers.Ordering.*;
import static net.schwarzbaer.challenges.codewars.java.PeanoNumbersSolution.PeanoNumbers.*;
import static org.junit.jupiter.api.Assertions.*;

class PeanoNumbersSolutionTest {

    private static Peano peano(int n) {
        if (0 == n) return Zero.INSTANCE;
        return new Succ(peano(n - 1));
    }

    private static void assertPeanoEq(Peano a, Peano b) {
        assertEquals(EQ, compare(a, b));
    }

    @Test
    public void addTest() {
        assertPeanoEq(peano(0), add(peano(0), peano(0)));
        assertPeanoEq(peano(664), add(peano(0), peano(664)));
    }

    @Test
    public void subTest() {
        assertPeanoEq(peano(0), sub(peano(0), peano(0)));
        assertPeanoEq(peano(10), sub(peano(10), peano(0)));
    }

    @Test
    public void mulTest() {
        assertPeanoEq(peano(0), mul(peano(0), peano(0)));
        assertPeanoEq(peano(0), mul(peano(5), peano(0)));
    }

    @Test
    public void divTest() {
        assertPeanoEq(peano(4), div(peano(8), peano(2)));
        assertPeanoEq(peano(3), div(peano(10), peano(3)));
    }

    @Test
    public void evenTest() {
        assertTrue(even(peano(0)));
        assertTrue(even(peano(8)));
    }

    @Test
    public void oddTest() {
        assertFalse(odd(peano(0)));
        assertFalse(odd(peano(8)));
    }

    @Test
    public void compareTest() {
        assertEquals(LT, compare(peano(0), peano(4)));
        assertEquals(GT, compare(peano(210), peano(43)));
        assertEquals(EQ, compare(peano(0), peano(0)));
    }

    @Test
    public void shouldntCheat() {
        // hidden
    }

}