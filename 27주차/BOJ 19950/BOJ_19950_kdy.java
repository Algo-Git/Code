import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N19950 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int z1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		int z2 = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		int max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int bar = Integer.parseInt(st.nextToken());
			sum += bar;
			max = Math.max(max, bar);
		}
		double dist = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
		if (sum < dist) {
			System.out.println("NO");
		} else if (max > sum - max + dist) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}
	}

}
