import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_13164_kdy {// BOJ 13164. 행복유치원

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] h = new int[N];
		st = new StringTokenizer(br.readLine());
		h[0] = Integer.parseInt(st.nextToken());
		int[] dif = new int[N];
		for (int i = 1; i < N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
			dif[i] = h[i] - h[i - 1];
		}
		Arrays.sort(dif);
		int ans = 0;
		for (int i = 0; i <= N - K; i++) {
			ans += dif[i];
		}
		System.out.println(ans);
	}

}
