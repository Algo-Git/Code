import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//[골드1] 온풍기 안녕!
public class BOJ_23289_yejinKim {
    //1. 모든 온풍기에서 바람 나옴
    // 2. 온도가 조절됨
    // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
    // 4. 초콜릿 하나 먹음
    // 5. 모든 칸의 온도가 K이상이 되었는지 검사, 모든칸의 온도가 K이상이면 테스트 중단. 아니면 1부터 다시 시작
    static int R, C, K;
    static int[][] map;
    static ArrayList<Heater> heaters;
    static ArrayList<Loc> checkList;
    static boolean[][][] walls;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 온도

        map = new int[R][C]; // 온도를 저장할 곳
        heaters = new ArrayList<>();
        checkList = new ArrayList<>();
        walls = new boolean[R][C][2]; // 0이면 가로 벽 , 1이면 세로 벽

        int choco = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int d = Integer.parseInt(st.nextToken());
                if (d == 0) continue;
                else if (d == 5) checkList.add(new Loc(i, j));
                else heaters.add(new Heater(i, j, d));
            }
        }

        int W = Integer.parseInt(br.readLine()); // 벽의 개수
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            if (w == 0) { // 가로 벽
                walls[x - 1][y][w] = true;
            } else { // 세로 벽
                walls[x][y][w] = true;
            }
        }

        while (true) {
            for (int k = 0; k < heaters.size(); k++) {
                Heater heater = heaters.get(k);
                int x = heater.x;
                int y = heater.y;
                int d = heater.d;
                visited = new int[R][C];
                switch (d) {
                    case 1:
                        if (y + 1 < C) {
                            visited[x][y + 1] = 5;
                            windAround(x, y + 1, d, 4);
                        }
                        break;
                    case 2:
                        if (y - 1 >= 0) {
                            visited[x][y - 1] += 5;
                            windAround(x, y - 1, d, 4);
                        }
                        break;
                    case 3:
                        if (x - 1 >= 0) {
                            visited[x - 1][y] += 5;
                            windAround(x - 1, y, d, 4);
                        }
                        break;
                    case 4:
                        if (x + 1 < R) {
                            visited[x + 1][y] += 5;
                            windAround(x + 1, y, d, 4);
                        }
                        break;
                    default:
                        break;
                }
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        map[i][j] += visited[i][j];
                    }
                }
            }

//            for (int i = 0; i < R; i++) {
//                for (int j = 0; j < C; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

            // 온도 조절
            int[][] temp = new int[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                        switch (d) {
                            case 0: // 상
                                if (walls[nx][ny][0]) break;
                                temp[i][j] += (-map[i][j] + map[nx][ny]) / 4;
                                break;
                            case 1: // 하
                                if (walls[i][j][0]) break;
                                temp[i][j] += (-map[i][j] + map[nx][ny]) / 4;
                                break;
                            case 2: // 좌
                                if (walls[nx][ny][1]) break;
                                temp[i][j] += (-map[i][j] + map[nx][ny]) / 4;
                                break;
                            case 3: // 우
                                if (walls[i][j][1]) break;
                                temp[i][j] += (-map[i][j] + map[nx][ny]) / 4;
                                break;
                        }
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    map[i][j] += temp[i][j];
                }
            }

//            System.out.println("온도 조절 후");
//            for (int i = 0; i < R; i++) {
//                for (int j = 0; j < C; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }


            // 온도감소
            for (int j = 0; j <= C - 2; j++) {
                if (map[0][j] != 0) map[0][j]--;
            }
            for (int j = C - 1; j >= 1; j--) {
                if (map[R - 1][j] != 0) map[R - 1][j]--;
            }
            for (int i = 0; i <= R - 2; i++) {
                if (map[i][C - 1] != 0) map[i][C - 1]--;
            }
            for (int i = R - 1; i >= 1; i--) {
                if (map[i][0] != 0) map[i][0]--;
            }

//            System.out.println("온도 감소 후");
//            for (int i = 0; i < R; i++) {
//                for (int j = 0; j < C; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

            choco++;

            boolean flag = true;
            for (Loc check : checkList) {
                if (map[check.x][check.y] < K) {
                    flag = false;
                    break;
                }
            }

            if (flag) break;

            if (choco == 100){
                choco ++;
                break;
            }
        }

        System.out.print(choco);

    }

    private static void windAround(int x, int y, int d, int step) {
        if (step == 0) return;

        boolean[][] v = new boolean[R][C];
        switch (d) {
            case 1:
                if (y + 1 < C) {
                    if (x - 1 < 0) {
                    } else if (!walls[x - 1][y][1] && !walls[x - 1][y][0]) {
                        visited[x - 1][y + 1] = step;
                        v[x - 1][y + 1] = true;
                    }

                    if (!walls[x][y][1]) {
                        visited[x][y + 1] = step;
                        v[x][y + 1] = true;
                    }

                    if (x + 1 >= R) {
                    } else if (!walls[x][y][0] && !walls[x + 1][y][1]) {
                        visited[x + 1][y + 1] = step;
                        v[x + 1][y + 1] = true;
                    }

                    for (int i = -1; i <= 1; i++) {
                        if (x + i < 0 || x + i >= R || !v[x + i][y + 1]) continue;
                        windAround(x + i, y + 1, d, step - 1);
                    }
                }
                break;
            case 2:
                if (y - 1 >= 0) {
                    if (x - 1 < 0) {
                    } else if (!walls[x - 1][y - 1][1] && !walls[x - 1][y][0]) {
                        visited[x - 1][y - 1] = step;
                        v[x - 1][y - 1] = true;
                    }

                    if (!walls[x][y - 1][1]) {
                        visited[x][y - 1] = step;
                        v[x][y - 1] = true;
                    }

                    if (x + 1 >= R) {
                    } else if (!walls[x + 1][y - 1][1] && !walls[x][y][0]) {
                        visited[x + 1][y - 1] = step;
                        v[x + 1][y - 1] = true;
                    }

                    for (int i = -1; i <= 1; i++) {
                        if (x + i < 0 || x + i >= R || !v[x + i][y - 1]) continue;
                        windAround(x + i, y - 1, d, step - 1);
                    }
                }
                break;
            case 3:
                if (x - 1 >= 0) {
                    if (y - 1 < 0) {
                    } else if (!walls[x - 1][y - 1][0] && !walls[x][y - 1][1]) {
                        visited[x - 1][y - 1] = step;
                        v[x - 1][y - 1] = true;
                    }

                    if (!walls[x - 1][y][0]) {
                        visited[x - 1][y] = step;
                        v[x - 1][y] = true;
                    }

                    if (y + 1 >= C) {
                    } else if (!walls[x - 1][y + 1][0] && !walls[x][y][1]) {
                        visited[x - 1][y + 1] = step;
                        v[x - 1][y + 1] = true;
                    }

                    for (int i = -1; i <= 1; i++) {
                        if (y + i < 0 || y + i >= C || !v[x - 1][y + i]) continue;
                        windAround(x - 1, y + i, d, step - 1);
                    }
                }
                break;
            case 4:
                if (x + 1 < R) {
                    if (y - 1 < 0) {
                    } else if (!walls[x][y - 1][1] && !walls[x][y - 1][0]) {
                        visited[x + 1][y - 1] = step;
                        v[x + 1][y - 1] = true;
                    }

                    if (!walls[x][y][0]) {
                        visited[x + 1][y] = step;
                        v[x + 1][y] = true;
                    }

                    if (y + 1 >= C) {
                    } else if (!walls[x][y][1] && !walls[x][y + 1][0]) {
                        visited[x + 1][y + 1] = step;
                        v[x + 1][y + 1] = true;
                    }

                    for (int i = -1; i <= 1; i++) {
                        if (y + i < 0 || y + i >= C || !v[x + 1][y + i]) continue;
                        windAround(x + 1, y + i, d, step - 1);
                    }
                }
                break;
            default:
                break;
        }

    }


    private static class Heater {
        int x;
        int y;
        int d;

        public Heater(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    private static class Loc {
        int x;
        int y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
