import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class N14226 {// BOJ 14226. 이모티콘

    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());// 효빈이에게 보낼 이모티콘 수
        cal();//이모티콘 수 계산하기
    }

    private static void cal() {// 이모티콘 수 계산
        Queue<Data> q = new LinkedList<>();// 현재 정보 큐
        boolean[][] v = new boolean[S + 1][S + 1];// 화면 속 이모티콘 수/클립보드 이모티콘 수 방문체크
        q.offer(new Data(2, 2, 1));// S가 제일 작은 경우 넣기
        v[2][1] = true;// 방문체크
        while (!q.isEmpty()) {// bfs
            Data cur = q.poll();// 하나 꺼내기
            if (cur.n == S) {// 화면에 S개 있으면
                System.out.println(cur.t);// 최소 시간 출력
                return;// 종료
            }
            // 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
            if (!v[cur.n][cur.n]) {// 화면에 있는 이모티콘 복사한 경우
                v[cur.n][cur.n] = true;// 방문체크
                q.offer(new Data(cur.t + 1, cur.n, cur.n));// 클립보드에 저장
            }
            // 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
            if (cur.c != 0 && cur.n + cur.c <= S && !v[cur.n + cur.c][cur.c]) {// 클립보드에 이모티콘이 있고 범위 안, 방문X일 때
                v[cur.n + cur.c][cur.c] = true;// 방문 체크
                q.offer(new Data(cur.t + 1, cur.n + cur.c, cur.c));// 화면에 클립보드 이모티콘 붙여넣기
            }
            // 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
            if (cur.n - 1 >= 0 && !v[cur.n - 1][cur.c]) {// 이모티콘이 1개 이상이면서 하나뺀 경우를 확인X한 경우
                v[cur.n - 1][cur.c] = true;// 방문체크
                q.offer(new Data(cur.t + 1, cur.n - 1, cur.c));// 화면 이모티콘 하나 삭제
            }
        }
    }

    static class Data {// 이모티콘 정보
        int t, n, c;// 시간, 화면 속 이모티콘 수, 클립보드 이모티콘 수

        public Data(int t, int n, int c) {
            this.t = t;
            this.n = n;
            this.c = c;
        }
    }
}