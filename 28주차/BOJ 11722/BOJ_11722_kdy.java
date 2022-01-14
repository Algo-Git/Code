import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N11722 {

	static int[] LDS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		LDS = new int[N + 1];
		LDS[0] = Integer.MAX_VALUE;
		int len = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (LDS[len] > n) {
				LDS[++len] = n;
			} else {
				LDS[binarySearch(0, len, n)] = n;
			}
		}
		System.out.println(len);
	}

	private static int binarySearch(int left, int right, int n) {
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (LDS[mid] > n) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

}
