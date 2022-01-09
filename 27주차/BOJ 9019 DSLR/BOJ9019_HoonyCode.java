import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ9019 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        String[] in;
        for (int i = 0 ; i < T ; i++){
            in = br.readLine().split(" ");
            int A, B;
            A = Integer.parseInt(in[0]);
            B = Integer.parseInt(in[1]);
            sb.append(bfs(A, B)).append('\n');
        }
        System.out.print(sb.toString());
    }

    static char[] d = {'D', 'S', 'L', 'R'};

    private static String bfs(int start, int end) {
        boolean[] v = new boolean[10000];
        String[] answer = new String[10000];
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        v[start] = true;
        answer[start] = "";

        Integer cur;
        while (!que.isEmpty()) {
            cur = que.poll();

            int D = (cur * 2) % 10000;
            int S = (cur == 0) ? 9999 : cur-1;
            int L = (cur % 1000) * 10 + cur/1000;
            int R = (cur % 10) * 1000 + cur/10;

            if (!v[D]){
                que.offer(D);
                v[D] = true;
                answer[D] = answer[cur] + 'D';
            }
            if (!v[S]){
                que.offer(S);
                v[S] = true;
                answer[S] = answer[cur] + 'S';
            }
            if (!v[L]) {
                que.offer(L);
                v[L] = true;
                answer[L] = answer[cur] + 'L';
            }
            if (!v[R]) {
                que.offer(R);
                v[R] = true;
                answer[R] = answer[cur] + 'R';
            }

            if (v[end]) {
                break;
            }
        }
        return answer[end];
    }
}
