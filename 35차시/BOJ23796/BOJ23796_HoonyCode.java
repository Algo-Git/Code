import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ23796 {

    static long[][] map = new long[8][8];
    static long[][] result = new long[8][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for (int i = 0; i < 8; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < 8; j++) {
                map[i][j] = Integer.parseInt(in[j]);
            }
        }
        char keyBoard = br.readLine().charAt(0);

        if (keyBoard == 'U')
            Up();
        else if (keyBoard == 'L')
            Left();
        else if (keyBoard == 'D')
            Down();
        else
            Right();


        PrintMap();
    }

    static void PrintMap() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }

    static void Up() {
        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < 8; i++) {
            stack.clear();

            long cur;
            boolean flag = false;
            for (int j = 0; j < 8; j++) {
                // 0이면 건너뜀
                if (map[j][i] == 0) continue;

                // 비어 있으면 그냥 stack 넣고 continue;
                if (stack.isEmpty()) {
                    stack.push(map[j][i]);
                    continue;
                }

                if (flag){
                    stack.push(map[j][i]);
                    flag = false;
                    continue;
                }

                //맨 위에꺼 꺼내고
                cur = stack.peek();


                if (cur == map[j][i] ) {
                    stack.pop();
                    stack.push(cur << 1);
                    flag = true;
                } else {
                    stack.push(map[j][i]);
                }
            }

            for (int j = stack.size() - 1; j > -1; j--) {
                result[j][i] = stack.pop();
            }
        }
    }

    static void Down() {
        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < 8; i++) {
            stack.clear();

            long cur;
            boolean flag = false;
            for (int j = 7; j > -1; j--) {
                // 0이면 건너뜀
                if (map[j][i] == 0) continue;

                // 비어 있으면 그냥 stack 넣고 continue;
                if (stack.isEmpty()) {
                    stack.push(map[j][i]);
                    continue;
                }

                if (flag){
                    stack.push(map[j][i]);
                    flag = false;
                    continue;
                }

                //맨 위에꺼 꺼내고
                cur = stack.peek();

                if (cur == map[j][i]) {
                    stack.pop();
                    stack.push(cur << 1);
                    flag = true;
                } else {
                    stack.push(map[j][i]);
                }
            }

            for (int j = 8 - stack.size(); j < 8; j++) {
                result[j][i] = stack.pop();
            }
        }
    }

    static void Right() {
        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < 8; i++) {
            stack.clear();

            boolean flag = false;
            long cur;
            for (int j = 7; j > -1; j--) {
                // 0이면 건너뜀
                if (map[i][j] == 0) continue;

                // 비어 있으면 그냥 stack 넣고 continue;
                if (stack.isEmpty()) {
                    stack.push(map[i][j]);
                    continue;
                }

                if (flag){
                    stack.push(map[i][j]);
                    flag = false;
                    continue;
                }

                //맨 위에꺼 꺼내고
                cur = stack.peek();

                if (cur == map[i][j]) {
                    stack.pop();
                    stack.push(cur << 1);
                    flag = true;
                } else {
                    stack.push(map[i][j]);
                }
            }

            for (int j = 8 - stack.size(); j < 8; j++) {
                result[i][j] = stack.pop();
            }
        }
    }

    static void Left() {
        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < 8; i++) {
            stack.clear();

            long cur;
            boolean flag = false;
            for (int j = 0; j < 8; j++) {
                // 0이면 건너뜀
                if (map[i][j] == 0) continue;

                // 비어 있으면 그냥 stack 넣고 continue;
                if (stack.isEmpty()) {
                    stack.push(map[i][j]);
                    continue;
                }

                if (flag){
                    stack.push(map[i][j]);
                    flag = false;
                    continue;
                }

                //맨 위에꺼 꺼내고
                cur = stack.peek();

                if (cur == map[i][j]) {
                    stack.pop();
                    stack.push(cur << 1);
                    flag = true;
                } else {
                    stack.push(map[i][j]);
                }
            }

            for (int j = stack.size() - 1; j > -1; j--) {
                result[i][j] = stack.pop();
            }
        }
    }


}
