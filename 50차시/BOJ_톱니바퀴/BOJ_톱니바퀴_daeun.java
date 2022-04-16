import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {

    static char[][] arr, copyArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new char[5][8];
        copyArr = new char[5][8];
        for (int i = 1; i <= 4; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                copyArr[i][j] = arr[i][j];
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 톱니바퀴 번호
            int d = Integer.parseInt(st.nextToken());   // 방향

            solve(num, d);
            copy(); // arr에 copyArr 값 복사
        }

        int answer = 0;
        if (arr[1][0] == '1') answer += 1;
        if (arr[2][0] == '1') answer += 2;
        if (arr[3][0] == '1') answer += 4;
        if (arr[4][0] == '1') answer += 8;

        System.out.print(answer);
    }

    static void solve(int num, int d) {
        rotate(num, d); // num 번의 톱니바퀴 회전
        if (num == 1) {
            // 1번의 2와 2번의 6이 다른 극이라면 2번 변경
            if (arr[1][2] != arr[2][6]) {
                rotate(2, d * -1);
                if (arr[2][2] != arr[3][6]) {
                    rotate(3, d);
                    if (arr[3][2] != arr[4][6])
                        rotate(4, d * -1);
                }
            }
        } else if (num == 2) {
            // 2번의 6과 1번의 2가 다른 극이라면 1번 변경
            if (arr[2][6] != arr[1][2]) rotate(1, d * -1);
            // 2번의 2와 3번의 6이 다른 극이라면 3번 변경
            if (arr[2][2] != arr[3][6]) {
                rotate(3, d * -1);
                if (arr[3][2] != arr[4][6])
                    rotate(4, d);
            }
        } else if (num == 3) {
            // 3번의 6과 2번의 2가 다른 극이라면 2번 변경
            if (arr[3][6] != arr[2][2]) {
                rotate(2, d * -1);
                if (arr[2][6] != arr[1][2])
                    rotate(1, d);
            }
            // 3번의 2와 4번의 6이 다른 극이라면 4번 변경
            if (arr[3][2] != arr[4][6]) rotate(4, d * -1);
        } else {
            // 4번의 6과 3번의 2가 다른 극이라면 3번 변경
            if (arr[4][6] != arr[3][2]) {
                rotate(3, d * -1);
                if (arr[3][6] != arr[2][2]) {
                    rotate(2, d);
                    if (arr[2][6] != arr[1][2])
                        rotate(1, d * -1);
                }
            }
        }
    }

    static void copy() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < 8; j++) {
                arr[i][j] = copyArr[i][j];
            }
        }
    }

    static void rotate(int num, int d) {
        if (d == 1) { // 톱니바퀴 시계방향 회전
            char temp = arr[num][7];
            for (int i = 6; i >= 0; i--) {
                copyArr[num][i + 1] = copyArr[num][i];
            }
            copyArr[num][0] = temp;
        } else {  // 톱니바퀴 반시계방향 회전
            char temp = arr[num][0];
            for (int i = 1; i < 8; i++) {
                copyArr[num][i - 1] = copyArr[num][i];
            }
            copyArr[num][7] = temp;
        }
    }
}