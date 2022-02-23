import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_16234_인구이동 {

    static int N, L, R, arr[][];
    static int res; // 출력 결과
    static boolean[][] v;   // 방문체크 배열
    static ArrayList<Data> list;    // 연합국 리스트
    static boolean isEnd;   // 이동 가능 여부 체크 (while문 탈출 조건)
    static int total;   // 연합국 사람 수 합

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.print(res);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    private static void solve() {
        while (!isEnd) {
            isEnd = true;
            v = new boolean[N][N];

            // 인구 인동
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (v[i][j]) continue;
                    total = 0;
                    list = new ArrayList<>();

                    total += arr[i][j]; // 연합 총 인구 수 구하기
                    v[i][j] = true;
                    list.add(new Data(i, j));   // 연합 리스트에 추가
                    dfs(new Data(i, j));

                    int temp = total / list.size(); // 연합국마다 인구 수 구하기
                    for (Data cur : list) {
                        arr[cur.r][cur.c] = temp;
                    }
                }
            }

            if(!isEnd) res++;   // 이동한 인구가 있으면 결과 + 1, 이동을 안했으면 true이므로 탈출
        }
    }

    private static void dfs(Data cur) {
        for (int d = 0; d < 4; d++) {
            int nr = cur.r + dr[d];
            int nc = cur.c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc])
                continue;

            int diff = Math.abs(arr[cur.r][cur.c] - arr[nr][nc]);   // 두 국가의 인구수 차이

            if (diff < L || diff > R) continue; // L 미만, R 초과이면 국경선을 열 수 없음

            isEnd = false;  // 인구 이동 체크

            total += arr[nr][nc];   // 연합 총 인구 수 구하기
            v[nr][nc] = true;
            list.add(new Data(nr, nc)); // 연합 리스트에 추가
            dfs(new Data(nr, nc));
        }
    }

    static class Data {
        int r, c;

        public Data(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}