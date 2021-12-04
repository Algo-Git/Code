import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ1005_HoonyCode {

    static int N, K, W;
    static int[] time;
    static List<Integer>[] list;
    static int[] cnt; //선행해야 하는 일의 개수


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스의 개수 T
        int T = Integer.parseInt(br.readLine());

        String[] input;

        // 테스트 케이스마다 반복
        for (int tc = 0; tc < T; tc++) {
            input = br.readLine().split(" ");

            // 건물의 개수
            N = Integer.parseInt(input[0]) + 1;
            // 건서순서 규칙의 총 개수 K
            K = Integer.parseInt(input[1]);

            //time 각 건물을 짓는데 소요되는 시간
            time = new int[N];

            //선행해야하는 일의 개수
            cnt = new int[N];

            input = br.readLine().split(" ");
            for (int i = 1; i < N; i++) {
                time[i] = Integer.parseInt(input[i - 1]);
            }

            list = new List[N];
            for (int i = 1; i < N; i++) {
                list[i] = new ArrayList<Integer>();
            }

            int start;
            int end;
            for (int i = 0; i < K; i++) {
                input = br.readLine().split(" ");
                start = Integer.parseInt(input[0]);
                end = Integer.parseInt(input[1]);
                list[start].add(end);
                cnt[end]++;
            }

            //백준이가 승리하기 위해 건설해야하는 건물의 번호
            W = Integer.parseInt(br.readLine());

            //위상정렬
            sb.append(TopologicalSort()).append('\n');
        }

        System.out.println(sb.toString());
    }

    static private int TopologicalSort() {
        Queue<Integer> que = new LinkedList<>();
        int[] res = new int[N];

        for (int i = 1; i < N; i++) {
            if (cnt[i] == 0) {
                que.offer(i);
                res[i] = time[i];
            }
        }

        int cur;

        // 큐에 데이터가 있을 경우 루프를 돈다
        while (!que.isEmpty()) {
            cur = que.poll(); // 현재 할일

            if (W == cur) break;

            for (int next : list[cur]){
                cnt[next]--;

                res[next] = Math.max(res[next], res[cur] + time[next]);

                if (cnt[next] == 0){
                    que.offer(next);
                }
            }
        }

        return res[W];
    }
}
