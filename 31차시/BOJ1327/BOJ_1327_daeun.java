import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1327_소트게임 {

    static int N, K, res;
    static String input;
    static StringBuilder goal;
    static boolean check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = br.readLine().replace(" ", "");

        goal = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            goal.append(i);
        }

        bfs();

        System.out.print(check ? res : -1);
    }

    private static void bfs() {
        Queue<Data> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        q.offer(new Data(input, 0));

        while (!q.isEmpty()) {
            Data cur = q.poll();

            // 오름차순 정렬 되었으면 break
            if (cur.s.equals(goal.toString())) {
                res = cur.cnt;
                check = true;
                break;
            }

            // 뒤집을 수 있는 숫자
            if (!set.contains(cur.s)) {
                set.add(cur.s);
                for (int i = 0; i <= N - K; i++) {
                    StringBuilder s = new StringBuilder();
                    StringBuilder sb = new StringBuilder();

                    // 뒤집을 문자열
                    sb.append(cur.s.substring(i, i + K));
                    sb.reverse();

                    // 새로 만든 문자열
                    s.append(cur.s.substring(0, i));
                    s.append(sb);
                    s.append(cur.s.substring(i + K, N));

                    q.offer(new Data(s.toString(), cur.cnt + 1));
                }
            }
        }
    }

    static class Data {
        String s;
        int cnt;    // 선택한 수의 개수

        public Data(String s, int cnt) {
            this.s = s;
            this.cnt = cnt;
        }
    }
}