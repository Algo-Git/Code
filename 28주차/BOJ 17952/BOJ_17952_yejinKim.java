import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// [실버4] 과제는 끝나지 않아!
// 스택을 이용한다
public class BOJ_17952_yejinKim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Data> stack = new Stack<>();
        int score = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int homework = Integer.parseInt(st.nextToken());
            if (homework == 0) {
                if (!stack.isEmpty()) {
                    Data data = stack.pop();
                    data.time--;
                    if (data.time == 0) {
                        score += data.score;
                    } else {
                        stack.push(data);
                    }
                }
            } else {
                int s = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                t --;
                if(t == 0){
                    score += s;
                }
                else{
                    stack.add(new Data(s,t));
                }
            }
//            System.out.println(stack.toString());
        }
        System.out.println(score);
    }

    private static class Data {
        int score;
        int time;

        public Data(int score, int time) {
            this.score = score;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "score=" + score +
                    ", time=" + time +
                    '}';
        }
    }
}
