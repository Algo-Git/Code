import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1072 {// BOJ 1072. 게임

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double X = Integer.parseInt(st.nextToken());// 게임 횟수
        double Y = Integer.parseInt(st.nextToken());// 이긴 게임 수
        int Z = (int) (Y * 100 / X);// 승률
        if (Z >= 99)// 승률이 99%이상이면 변하지X
            System.out.println(-1);
        else {
            double ans = ((X * Z) + X - (100 * Y)) / (99 - Z);// 승률이 변하는 최소 게임 횟수
            if ((int) ans == ans)// 승률이 나누어떨어지면
                System.out.println((int) ans);// 횟수 출력
            else
                System.out.println((int) ans + 1);// 횟수+1 출력
        }
    }
}