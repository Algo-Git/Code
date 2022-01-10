import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N9019 {

	static int A, B;
	static String ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			ans = "";
			trans();
			System.out.println(ans);
		}
	}

	private static void trans() {
		Queue<Integer> q = new LinkedList<>();
		String[] res = new String[10000];
		res[A] = "";
		q.add(A);
		while (!q.isEmpty()) {
			int n = q.poll();
			int d = n * 2 % 10000;
			int s = n == 0 ? 9999 : n - 1;
			int l = n * 10 % 10000 + n / 1000;
			int r = (n % 10) * 1000 + n / 10;
			if (res[d] == null) {
				q.add(d);
				res[d] = res[n] + "D";
			}
			if (res[s] == null) {
				q.add(s);
				res[s] = res[n] + "S";
			}
			if (res[l] == null) {
				q.add(l);
				res[l] = res[n] + "L";
			}
			if (res[r] == null) {
				q.add(r);
				res[r] = res[n] + "R";
			}
			if (d == B || s == B || l == B || r == B) {
				break;
			}
		}
		ans = res[B];
	}
}
