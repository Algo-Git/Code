import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N16234 {// BOJ 16234. 인구 이동

    static int N, L, R;
    static int[][] map, tmp;
    static boolean[][] v;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());// 땅의 크기
        L = Integer.parseInt(st.nextToken());// 인구 차이 범위 최소값
        R = Integer.parseInt(st.nextToken());// 인구 차이 범위 최댓값
        map = new int[N][N];// 땅
        int day = 0;// 인구 이동이 발생하는 날
        for (int i = 0; i < N; i++) {// 인구 정보 입력받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int k = 0; k < 2000; k++) {// 인구 이동(최대 2000일)
            v = new boolean[N][N];// 이동한 곳 표시
            tmp = new int[N][N];// 이동 후 인구 수 저장할 배열
            flag = false;// 인구 이동 여부 확인
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!v[i][j])// 인구 이동 X
                        bfs(i, j);// 주변 나라 살펴보기(현재 국가의 x, y좌표)
                }
            }
            if (!flag) break;// 인구 이동 X => 중단
            for (int i = 0; i < N; i++) {// 인구 변화 반영
                for (int j = 0; j < N; j++) {
                    map[i][j] = tmp[i][j];
                }
            }
            day += 1;// 인구 이동한 날 수 +1
        }
        System.out.println(day);// 인구 이동이 발생한 날 수 출력
    }

    private static void bfs(int x, int y) {// 주변 국가 살펴보기
        Queue<XY> q = new LinkedList<>();// 경계를 열 국가 저장
        ArrayList<XY> list = new ArrayList<>();// 연합 국가 저장
        int sum = 0;// 연합의 인구 수
        q.offer(new XY(x, y));// 현재 국가 넣기
        v[x][y] = true;// 방문 표시
        while (!q.isEmpty()) {// 경계를 열 국가가 없을때까지 반복
            XY cur = q.poll();// 현재 국가 위치
            list.add(cur);// 현재 국 추가
            sum += map[cur.x][cur.y];// 연합 인구 수에 더하기
            for (int i = 0; i < 4; i++) {// 국경선을 공유하는 나라 살펴보기
                int nx = cur.x + dx[i];// x좌표 이동
                int ny = cur.y + dy[i];// y좌표 이동
                if (nx < 0 || ny < 0 || nx >= N || ny >= N)// 땅을 벗어나는 경우
                    continue;// 지나가기
                if (v[nx][ny]) continue;// 이미 살펴본 나라 => 지나가기
                int diff = Math.abs(map[cur.x][cur.y] - map[nx][ny]);// 두 나라의 인구 차이
                if (L > diff || diff > R)// 인구 차이가 범위 밖인 경우
                    continue;// 지나가기
                v[nx][ny] = true;// 방문 표시
                q.offer(new XY(nx, ny));// 살펴볼 국가로 추가
            }
        }
        if (list.size() > 1) {// 연합국이 2개 이상인 경우
            flag = true;// 인구 이동
        }
        sum /= list.size();// (각 칸의 인구수) = (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
        for (XY c : list) {// 연합국 정보
            tmp[c.x][c.y] = sum;// 인구 이동
        }
    }

    static class XY {// 국가의 좌표 정보
        int x, y;// X, y좌표

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
