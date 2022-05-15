package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_컨베이어벨트위의로봇_HoonyCode {

    static int N, K, len;
    static Belt[] belt;

    public static void main(String[] args) throws Exception {

        input();

        int answer = 0;

        while (true) {
            answer++;

            //1. 벨트가 각 칸위에 있는 로봇과 함께 한 칸 회전한다
            rotation();

            //2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다
            move();

            //3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다
            robotUp();

            //4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다
            if (exit() >= K) {
                break;
            }
        }

        System.out.println(answer);

    }

    private static int exit() {
        int total = 0;
        for (int i = 0; i < len; i++) {
            if (belt[i].cnt == 0) total++;
        }

        return total;
    }

    private static void robotUp() {
        if (belt[0].cnt != 0) {
            belt[0].cnt--;
            belt[0].robot = true;
        }
    }

    private static void move() {
        belt[N - 1].robot = false;
        for (int i = N - 2; i > -1; i--) {
            if (!belt[i].robot) continue; // 로봇이 없으면 스킵
            //로봇이 있다면
            if (belt[i + 1].robot || belt[i + 1].cnt <= 0) continue; // 앞에 로봇이 있으면 스킵

            belt[i + 1].cnt--;
            belt[i + 1].robot = true;
            belt[i].robot = false;

        }
        belt[N - 1].robot = false;
    }

    static class Belt {
        int cnt;
        boolean robot;

        public Belt(int cnt, boolean robot) {
            this.cnt = cnt;
            this.robot = robot;
        }
    }

    private static void rotation() {
        Belt temp = belt[len - 1];
        for (int i = len - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }
        belt[0] = temp;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        K = Integer.parseInt(in[1]);
        len = 2 * N;

        belt = new Belt[len];
        in = br.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            belt[i] = new Belt(Integer.parseInt(in[i]), false);
        }
    }
}
