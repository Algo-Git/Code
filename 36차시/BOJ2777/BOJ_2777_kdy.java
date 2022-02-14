import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2777 {// BOJ 2777. 숫자 게임

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());// 테스트 케이스 수
        for (int t = 0; t < T; t++) {// 테스트 케이스 수만큼 반복
            int N = Integer.parseInt(br.readLine());// 정수 N
            int ans = 0;// 조건을 만족하는 가장 작은 X의 자리 수
            for (int i = 9; i > 1; i--) {// 큰 수부터 나눠보기
                while (N % i == 0) {// 나누어떨어지면
                    N /= i;// 나누기
                    ans += 1;// 자리수+1
                }
            }
            if (N == 1)// 조건을 만족하는 수가 있는 경우
                System.out.println(ans == 0 ? 1 : ans);// 1이면 1, 나머지는 ans 출력
            else// 조건을 만족하는 수가 없는 경우
                System.out.println(-1);// -1 출력
        }
    }
}
