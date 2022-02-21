import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1072_게임 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        double X = Integer.parseInt(s[0]);    // 게임 횟수
        double Y = Integer.parseInt(s[1]);    // 이긴 게임
        int Z = (int) (Y * 100.0 / X);  // (Y / X * 100.0) 로 하면 틀렸습니다...

        int res;

        // X==Y 일때는 절대 바뀔 수 없으므로 -1
        // X=100, Y=99일 때는 99%이고, 100%가 절대 될 수 없으므로 -1
        if (Z >= 99) {
            res = -1;
        } else {
            for (int cnt = 1; ; cnt++) {
                X++;
                Y++;
                if ((int) (Y * 100.0 / X) != Z) {
                    res = cnt;
                    break;
                }
            }
        }

        System.out.print(res);
    }
}