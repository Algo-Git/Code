import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ17952 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 몇분
        /*
        1. 과제는 가장 최근에 나온 순서대로 한다. 과제를 받으면 바로 시작
        2. 과제를 하던 도중 새로운 과제가 나오면 하던 과제를 중단하고 새로우 과제 진행
        3. 새로운 과제가 끝났다면 이전에 하던 과제를 이전에 하던 부분부터 이어서 한다.
         */

        Stack<Subject> stack = new Stack<>();

        String[] in;
        Subject cur = null;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");

            //1일때
            if (Integer.parseInt(in[0]) == 1){
                if (cur != null){
                    stack.add(cur);
                }
                cur = new Subject(Integer.parseInt(in[1]), Integer.parseInt(in[2]));
            }

            //1일떄나 0일떄
            if (cur == null) continue;
            
            //cur이 null이 아닐때
            cur.time--;
            if (cur.time == 0){
                answer += cur.score;
                if (!stack.isEmpty()) cur = stack.pop();
                else cur = null;
            }
        }

        System.out.println(answer);

    }

    static class Subject {
        int score;
        int time;

        public Subject(int score, int time) {
            this.score = score;
            this.time = time;
        }

    }
}
