import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2056 {// BOJ 2056. 작업

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());// 작업의 수
		int[] t = new int[N];// 각 작업이 끝나는 시간을 담은 배열
		int ans = 0;// 모든 작업이 끝난 시간
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());// 작업 소요 시간 입력
			int w = Integer.parseInt(st.nextToken());// 선행해야하는 작업의 수
			if (w != 0) {// 선행해야 할 작업이 있는 경우
				int end = 0;// 선행해야 할 작업 중에 가장 늦게 끝나는 시간
				for (int j = 0; j < w; j++) {
					int pre = Integer.parseInt(st.nextToken()) - 1;// 선행해야하는 작업의 번호
					end = Math.max(end, t[pre]);// 선행할 작업이 끝나는 시간 중에 가장 늦은 시간을 선택
				}
				t[i] += end;// 선행 작업이 끝난 후 작업 시작 => 선행 작업이 마친 시간 + 작업 시간
			}
		}
		for (int i = 0; i < N; i++) {// 가장 늦게 종료된 작업 시간 구하기
			ans = Math.max(ans, t[i]);
		}
		System.out.println(ans);// 모든 작업이 마무리된 시간(가장 늦게 종료된 작업의 시간) 출력
	}

}
