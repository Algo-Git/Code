import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class BOJ12852_HoonyCode {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int map[] = new int[N + 1];
        int pre[] = new int[N + 1];
        pre[1] = 1;
        map[1] = 0;
        int result;

        for (int i = 2; i < N + 1; i++) {
            result = Integer.MAX_VALUE;
            if (i % 3 == 0) {
                if (result > map[i/3] + 1){
                    result = map[i/3] + 1;
                    pre[i] = i/3;
                }
            }
            if (i % 2 == 0) {
                if (result > map[i/2] + 1){
                    result = map[i/2] + 1;
                    pre[i] = i/2;
                }
            }

            if (result > map[i-1] + 1){
                result = map[i-1] + 1;
                pre[i] = i-1;
            }
            map[i] = result;
        }
        Queue<Integer> que = new LinkedList<>();
        int now = N;

        while (now != 1){
            que.add(now);
            now = pre[now];
        }
        que.add(1);
        StringBuilder sb = new StringBuilder();
        sb.append(map[N]).append('\n');

        while (!que.isEmpty()){
            sb.append(que.poll()).append(' ');
        }

        System.out.println(sb.toString());

    }
}