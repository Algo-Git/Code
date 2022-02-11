import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N24270 {// BOJ 24270. 미니 버킷 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());// 버킷 리스트에 적힌 일의 수
        int K = Integer.parseInt(st.nextToken());// 단위 시간 수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {// 일이 걸리는 시간 빼기
            K -= Integer.parseInt(st.nextToken());
        }
        int MOD = 1000000007;// 나눌 수
        long ans = 1;// 버킷 리스트로 가능한 경우의 수
        for (int i = 0; i < N; i++) {// K-sum+1 ~ K-sum+N
            K += 1;
            ans = (ans * K) % MOD;
        }
        System.out.println(ans);// 버킷 리스트로 가능한 경우의 수 출력
    }
}