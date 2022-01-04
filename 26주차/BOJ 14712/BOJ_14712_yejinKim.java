import java.util.Scanner;

// [실버1] 넴모넴모 (Easy)
// 네모가 게임을 그만두었을 때 나올 수 있는 “넴모”의 배치의 가짓수를 구하여라.
// 왼->오 위->아래 채우기
// 부분집합 + 백트래킹
public class BOJ_14712_yejinKim {
    static int N, M;
    static int cnt;
    static boolean[][] map;
    static int[] dx = {0, -1, -1};
    static int[] dy = {-1, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        cnt = 0;
        map = new boolean[N][M];

        map[0][0] = true;
        subset(0);
        map[0][0] = false;
        subset(0);

        System.out.println(cnt);
    }

    // 왼, 왼위, 위를 확인한다.
    private static void subset(int num) {

        int x = num / M;
        int y = num % M;

        boolean flag = true; // 터질 수 있으면 true
        if (!map[x][y]) flag = false;
        else {
            for (int d = 0; d < 3; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0) {
                    flag = false;
                    break;
                } else {
                    if (!map[nx][ny]) { // 만약 터질수 없다면
                        flag = false;
                        break;
                    }
                }
            }
        }
        // 가지치기
        if (flag) {
//            System.out.println("터진다" + num);
            return; // 터질 수 있으면 return
        }


        // 터질 수 없다면 다음 단계
        // 기저 조건
        if(num == N*M-1){
//            System.out.println(num);
            cnt ++;
            return;
        }

        x = (num+1)/M;
        y = (num+1)%M;

//        System.out.println("num+1: "+ (num+1) + " x: " + x + " y: " + y );
        map[x][y] = true;
        subset(num+1);
        map[x][y] = false;
        subset(num+1);
    }
}
