import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1405 {// BOJ 1405. 미친 로봇
    static int N;
    static double ans;
    static double[] pct;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());// 이동 횟수
        pct = new double[4];// 동서남북 확률
        for (int i = 0; i < 4; i++) {// 확률 입력 받기
            pct[i] = Double.parseDouble(st.nextToken());
            pct[i] *= 0.01; // 25% -> 0.25
        }
        ans = 0;// 로봇의 이동 경로가 단순할 확률
        v = new boolean[N * 2 + 1][N * 2 + 1];// 방문 체크
        go(0, N, N, 1);// 이동하기
        System.out.println(ans);// 로봇의 이동 경로가 단순할 확률 출력
    }

    private static void go(int n, int x, int y, double per) {// 이동하기(이동 횟수, x위치, y위치, 확률)
        if (v[x][y] || per == 0)// 방문했거나 확률이 0인 경우
            return;// 종료
        if (n == N) {// 이동을 완료한 경우
            ans += per;// 확률 더하기 => 또는
            return;// 종료
        }
        v[x][y] = true;// 방문 체크
        // 동서남북으로 이동하기
        go(n + 1, x + 1, y, per * pct[0]);
        go(n + 1, x - 1, y, per * pct[1]);
        go(n + 1, x, y + 1, per * pct[2]);
        go(n + 1, x, y - 1, per * pct[3]);
        v[x][y] = false;// 방문 체크
    }
}
