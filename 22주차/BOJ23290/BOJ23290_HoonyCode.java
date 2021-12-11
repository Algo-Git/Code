import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ23290_HoonyCode {

    /*
    상어가 모든 물고기에게 복제 마법을 시전한다. 복제 마법은 시간이 조금 걸리기 때문에, 아래 5번에서 물고기가 복제되어 칸에 나타난다.
    모든 물고기가 한 칸 이동한다. 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동할 수 없다.
    각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다.
    만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다. 물고기의 냄새는 아래 3에서 설명한다.
    상어가 연속해서 3칸 이동한다. 상어는 현재 칸에서 상하좌우로 인접한 칸으로 이동할 수 있다.
    연속해서 이동하는 칸 중에 격자의 범위를 벗어나는 칸이 있으면, 그 방법은 불가능한 이동 방법이다.
    연속해서 이동하는 중에 상어가 물고기가 있는 같은 칸으로 이동하게 된다면,
    그 칸에 있는 모든 물고기는 격자에서 제외되며, 제외되는 모든 물고기는 물고기 냄새를 남긴다.
    가능한 이동 방법 중에서 제외되는 물고기의 수가 가장 많은 방법으로 이동하며, 그러한 방법이 여러가지인 경우 사전 순으로 가장 앞서는 방법을 이용한다.
    사전 순에 대한 문제의 하단 노트에 있다.
    두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
    1에서 사용한 복제 마법이 완료된다. 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 갖게 된다.
     */

    static int[][][] fish = new int[4][4][8]; // 물고기 저장
    static int[][][] m_fish = new int[4][4][8]; //이동한 물고기
    static int[][][] egg = new int[4][4][8]; // 물고기 알낳은 곳
    static int S; //마법을 엽습한 횟수

    static int smell[][] = new int[4][4]; //물고기 시체 냄새

    //상어 부분
    static Pair shark; // 상어의 위치 저장
    static long ear_res;
    static int[][] res_move = new int[3][2];
    static int[][] move = new int[3][2];


    // 물기가 이동 방향
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    // 상어 이동 방향 상 좌 하 우
    static int[] sdr = {-1, 0, 1, 0};
    static int[] sdc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        input();

        // 연습횟수 만큼 반복
        for (int i = 0; i < S; i++) {
            // 1. 알을 낳는다.
            CopyArray(egg, fish);
            // 2. 물고기는 움직인다. 모든 물고기가 한 칸 이동한다.
            // 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동할 수 없다.
            // 각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다.
            move_fish();
            CopyArray(fish, m_fish);

            //상어가 먹이를 먹으러 이동한다.
            eat_fish();


            //냄새 제거
            remove_smell();

            //알을 낳음
            ArrayPlus(fish, egg);

            //m_fish 초기화
            clear(m_fish);
        }

        System.out.println(Total());

    }

    private static void clear(int[][][] map){
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                Arrays.fill(map[i][j], 0);
            }
        }
    }

    private static void ArrayPlus(int[][][] map1, int[][][] map2){
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                for (int d= 0 ; d < 8 ; d++){
                    map1[i][j][d] += map2[i][j][d];
                }
            }
        }
    }

    private static long Total(){
        long res = 0;
        for (int row = 0 ; row < 4 ; row++){
            for (int col = 0 ; col < 4; col++){
                for (int d = 0 ; d < 8; d++){
                    res += fish[row][col][d];
                }
            }
        }
        return res;
    }
    private static void remove_smell(){
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                smell[i][j]--;
            }
        }
    }

    private static void eat_fish() {
        //백트래킹
        ear_res = -1; // 먹은 물고기 초기화
        int[][] v = new int[4][4]; // 들어간 것을 판단하는 것
        move_shark(0, shark.row, shark.col, 0, v);

        //경로를 이동하면서 먹는 부분
        int row, col;
        for (int i = 0 ; i < 3; i++){
            row = res_move[i][0];
            col = res_move[i][1];

            for (int d = 0 ; d < 8 ; d++){
                if (fish[row][col][d] > 0){
                    smell[row][col] = 3;
                    fish[row][col][d] = 0;
                }
            }
        }

        //상어 위치 이동
        shark.row = res_move[2][0];
        shark.col = res_move[2][1];
    }

    //parameter dept 깊이, 현재 상어위치 row, col, total 먹은 물고기의 개수
    private static void move_shark(int cnt, int row, int col, long total, int[][] v) {
        if (cnt == 3) { // 이동은 세번함
            if (ear_res < total) {
                ear_res = total;
                // 이동경로를 복사함.
                for (int i = 0 ; i <3 ; i++){
                    res_move[i][0] = move[i][0]; // row
                    res_move[i][1] = move[i][1]; // col;
                }
            }
            return;
        }

        int nr, nc;
        long now_eat = 0;
        for (int d = 0; d < 4; d++){
            // 상어가 움직인 좌표
            nr = row + sdr[d];
            nc = col + sdc[d];

            // 범위를 벗어나면 안함.
            if (range_map(nr, nc)) continue;
            // 방문 표시
            v[nr][nc]++;
            move[cnt][0] = nr;
            move[cnt][1] = nc;
            if (v[nr][nc] == 1) now_eat = sum_fish(nr, nc);
            move_shark(cnt+1, nr, nc, total + now_eat, v);
            //백트래킹
            v[nr][nc]--;
        }

    }

    //map 복사
    static void CopyArray(int[][][] map1, int[][][] map2) {
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                for (int d = 0 ; d < 8; d++){
                    map1[i][j][d] = map2[i][j][d];
                }
            }
        }
    }


    //map 범위를 넘으면 true
    static boolean range_map(int row, int col) {
        return row < 0 || row >= 4 || col < 0 || col >= 4;
    }

    //상어랑 만나는 경우 true
    static boolean meet_shark(int row, int col) {
        return shark.col == col && shark.row == row;
    }

    //물고기가 움직이는 메서드
    static void move_fish() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                loop:
                for (int d = 0; d < 8; d++) {
                    if (fish[r][c][d] == 0) continue; // 물고기가 없으면 그냥 넘김

                    //물고기가 있다면
                    int nr, nc, dir;
                    for (int k = 0; k < 8; k++) {
                        dir = d - k < 0 ? d - k + 8 : d - k;
                        nr = r + dr[dir];
                        nc = c + dc[dir];

                        // 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동할 수 없다.
                        // 각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다.
                        if (range_map(nr, nc) || meet_shark(nr, nc) || smell[nr][nc] > 0 ) continue;
                        m_fish[nr][nc][dir] += fish[r][c][d];
                        continue loop;
                    }
                    m_fish[r][c][d] += fish[r][c][d]; // 그대로 가져감.
                }
            }
        }
    }


    // 입력매서드
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int M = Integer.parseInt(input[0]); // 물고기의 수 M
        S = Integer.parseInt(input[1]); // 마법을 연습한 횟수 S

        // M개의 줄에는 물고기의 정보 fx, fy, d가 주어진다. (fx, fy)는 물고기의 위치를 의미하고, d는 방향
        int r, c, d;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            r = Integer.parseInt(input[0]) - 1; // 행
            c = Integer.parseInt(input[1]) - 1; // 열
            d = Integer.parseInt(input[2]) - 1;
            fish[r][c][d]++;
        }
        input = br.readLine().split(" ");
        shark = new Pair(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);
    }


    static class Pair {
        int row, col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static long sum_fish(int row, int col) {
        long sum = 0;
        for (int d = 0; d < 8; d++) {
            sum += fish[row][col][d];
        }

        return sum;
    }

    //LOG print()
//    static void print() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                sb.append(sum_fish(i, j)).append(' ');
//            }
//            sb.append('\n');
//        }
//        System.out.println(sb.toString());
//    }
}
