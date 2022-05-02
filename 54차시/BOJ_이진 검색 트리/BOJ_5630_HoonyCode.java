package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5630_HoonyCode {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        String in;
        while (true){
            in = br.readLine();

            if (in == null || in.equals("")) break;

            root.insert(Integer.parseInt(in));
        }

        postOrder(root);

        System.out.print(sb.toString());
    }

    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value).append('\n');
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        void insert(int n) {
            if (n < this.value) {
                if (this.left == null) {
                    this.left = new Node(n);
                } else {
                    this.left.insert(n);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(n);
                } else {
                    this.right.insert(n);
                }
            }
        }
    }
}
