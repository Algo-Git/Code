import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14226_이모티콘 {

    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());

        bfs();
    }

    static void bfs() {
        Queue<Data> q = new LinkedList<>();
        boolean[][] v = new boolean[S * 2][S + 1];  // 행: 만든 이모티콘 개수, 열: 복사한 이모티콘 개수

        q.offer(new Data(2, 1, 2)); // 2부터 복붙은 무조건 있어야함
        v[2][1] = true;

        while (!q.isEmpty()) {
            Data cur = q.poll();

            // 높이가 같은 것이 보장됨 -> bfs
            if (cur.num == S) {
                System.out.print(cur.time);
                return;
            }

            // 복사
            if (!v[cur.num][cur.num]) {
                q.offer(new Data(cur.num, cur.num, cur.time + 1));
                v[cur.num][cur.num] = true;
            }

            // 붙여넣기
            if (cur.num + cur.copy <= S && !v[cur.num + cur.copy][cur.copy]) {
                q.offer(new Data(cur.num + cur.copy, cur.copy, cur.time + 1));
                v[cur.num + cur.copy][cur.copy] = true;
            }

            // 삭제
            if(cur.num - 1 >= 0 && !v[cur.num - 1][cur.copy]){
                q.offer(new Data(cur.num - 1, cur.copy, cur.time + 1));
                v[cur.num - 1][cur.copy] = true;
            }
        }
    }

    static class Data {
        int num, copy, time; // 이모티콘 개수, 복사한 이모티콘, 시간

        public Data(int num, int copy, int time) {
            this.num = num;
            this.copy = copy;
            this.time = time;
        }
    }
}