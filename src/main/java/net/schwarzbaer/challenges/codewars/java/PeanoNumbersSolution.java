package net.schwarzbaer.challenges.codewars.java;

public class PeanoNumbersSolution {
    public static void main(String[] args) {

    }

    public static final class PeanoNumbers {
        interface Peano {
            final class Zero implements Peano {
                static Zero INSTANCE = new Zero();
                private Zero() {}
            }

            final class Succ implements Peano {
                final Peano peano;
                Succ(Peano peano) {
                    this.peano = peano;
                }
            }
        }

        enum Ordering {GT, LT, EQ}

        static Peano add(Peano a, Peano b) {
            if (b instanceof Peano.Zero) return a;
            if (b instanceof Peano.Succ) return add(new Peano.Succ(a), ((Peano.Succ) b).peano);
            throw new IllegalArgumentException();
        }

        static Peano sub(Peano a, Peano b) {
            if (b instanceof Peano.Zero) return a;
            if (a instanceof Peano.Zero) throw new ArithmeticException("negative number");

            if (a instanceof Peano.Succ && b instanceof Peano.Succ)
                return sub(
                    ((Peano.Succ) a).peano,
                    ((Peano.Succ) b).peano
                );

            throw new IllegalArgumentException();
        }

        static Peano mul(Peano a, Peano b) {
            return addProd(Peano.Zero.INSTANCE, a, b);
        }

        static Peano addProd(Peano a, Peano b, Peano c) {
            if (b instanceof Peano.Zero) return a;
            if (c instanceof Peano.Zero) return a;
            if (c instanceof Peano.Succ) return addProd(add(a, b), b, ((Peano.Succ) c).peano);
            throw new IllegalArgumentException();
        }

        static Peano div(Peano a, Peano b) {
            if (b instanceof Peano.Zero) throw new ArithmeticException("divide by 0");
            if (a instanceof Peano.Zero) return Peano.Zero.INSTANCE;

            Peano c;
            try { c = sub(a, b); }
            catch (ArithmeticException e) {
                return Peano.Zero.INSTANCE;
            }

            return new Peano.Succ( div(c, b) );
        }

        static boolean even(Peano peano) {
            if (peano instanceof Peano.Zero) return true;
            if (peano instanceof Peano.Succ) return !even(((Peano.Succ)peano).peano);
            return false;
        }

        static boolean odd(Peano peano) {
            if (peano instanceof Peano.Zero) return false;
            if (peano instanceof Peano.Succ) return !odd(((Peano.Succ)peano).peano);
            return false;
        }

        static Ordering compare(Peano a, Peano b) {
            if (a instanceof Peano.Zero && b instanceof Peano.Zero) return Ordering.EQ;
            if (a instanceof Peano.Succ && b instanceof Peano.Zero) return Ordering.GT;
            if (a instanceof Peano.Zero && b instanceof Peano.Succ) return Ordering.LT;

            if (a instanceof Peano.Succ && b instanceof Peano.Succ)
                return compare(
                        ((Peano.Succ) a).peano,
                        ((Peano.Succ) b).peano
                );

            throw new IllegalArgumentException();
        }
    }
}
