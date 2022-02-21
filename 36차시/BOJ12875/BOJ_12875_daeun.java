import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_12875_칙령 {

    static int N, d, D[][], res;
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());    // 사람 수
        d = Integer.parseInt(br.readLine());    // 최대 차이날 수 있는 돈
        D = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                if (s[j].equals("Y")) D[i][j] = d;  // 친구관계일 때 d로 초기화
                else D[i][j] = MAX;  // 친구관계 아닐 때 MAX 초기화
            }
        }

        solve();

        System.out.print(res);
    }

    static void solve() {   // 플로이드와샬
        for (int k = 0; k < N; k++) {   // 경유지
            for (int i = 0; i < N; i++) {   // 출발지
                if(D[i][k] == MAX) continue;    // 친구가 아닌 관계면 continue
                for (int j = 0; j < N; j++) {   // 도착지
                    if(D[k][j] == MAX) continue;    // 친구가 아닌 관계면 continue
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;    // 같은 인덱스가 아닐 때만
                if (D[i][j] == MAX) {    // MAX를 가지고 있다는 것은 관계가 분리되어 있단 뜻 => -1
                    res = -1;
                    return;
                }
                res = Math.max(res, D[i][j]);
            }
        }
    }
}