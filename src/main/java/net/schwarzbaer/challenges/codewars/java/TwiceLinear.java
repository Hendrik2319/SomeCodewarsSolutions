package net.schwarzbaer.challenges.codewars.java;

public class TwiceLinear {
    /*
        4 kyu
        Twice linear
        https://www.codewars.com/kata/5672682212c8ecf83e000050
     */
    public static void main(String[] args) {
        test(10, 22);
        test(30, 91);
        test(20, 57);
        test(50, 175);
    }

    private static void test(int n, int expected) {
        int actual = DoubleLinear.dblLinear(n);
        if (actual == expected)
            System.out.printf("test(%d) -> %d (as expected)%n", n, actual);
        else
            System.err.printf("test(%d) -> %d != expected %d%n", n, actual, expected);
    }

    static class DoubleLinear
    {
        private static final Node root = new Node(1,null);
        private static int lastComputedIndex = -1;

        public static int dblLinear (int n)
        {

            Node node = root;
            for (int i=0; i<n; i++)
            {
                if (lastComputedIndex < i) {
                    node.insert(2*node.n+1);
                    node.insert(3*node.n+1);
                    lastComputedIndex = i;
                }
                System.out.printf("%d, ", node.n);
                node = node.next;
            }
            System.out.printf("%d # %s%n", node.n, node);

            return node.n;
        }

        private static class Node
        {
            final int n;
            Node next;

            Node(int n, Node next)
            {
                this.n = n;
                this.next = next;
            }

            void insert(int n)
            {
                Node node = this;
                if (node.n >= n)
                    throw new IllegalStateException(String.format("node[n:%d].insert(%d) ->  !(node.n < n)", node.n, n));

                while (node.next!=null && node.next.n<n)
                    node = node.next;

                if (node.next==null || node.next.n>n)
                    node.next = new Node(n,node.next);
            }

            @Override
            public String toString() {
                return n + (next==null ? "" : " -> "+next);
            }
        }
    }
}
