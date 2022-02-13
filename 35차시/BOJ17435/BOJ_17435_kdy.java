import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N17435 {// BOJ 17435. 합성함수와 쿼리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());// 정수 m
        int[][] y = new int[m + 1][20];// (m+1, 19+1)배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {// f(x)값 입력받기
            y[i][0] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < 20; i++) {// 2의 거듭제곱인 범위의 구간 값 저장
            for (int j = 1; j <= m; j++) {
                y[j][i] = y[y[j][i - 1]][i - 1]; // f(f(x)) 저장하기
            }
        }
        int Q = Integer.parseInt(br.readLine());// 쿼리 수
        for (int i = 0; i < Q; i++) {// 쿼리 입력받고 답 구하기
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());// 정수 n
            int x = Integer.parseInt(st.nextToken());// 정수 x
            for (int j = 0; n != 0; j++) {// fn(x)구하기
                if ((n & 1) == 1)// 홀수일 때
                    x = y[x][j];// 구간 값 저장
                n >>= 1;// n%=2;
            }
            System.out.println(x);// fn(x) 출력
        }
    }
}