import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// [골드4] 작업
// 선행관계 -> 위상정렬
public class BOJ_2056_kyj {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] times = new int[N+1]; // 작업소요시간들을 저장하는 배열
		int[] arr = new int[N+1]; // 나에게 향하는 화살표 개수를 저장하는 배열

		ArrayList<Integer>[] adjList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			times[i] = t;
			arr[i] = n;

			for (int j = 0; j < n; j++) {
				int start = Integer.parseInt(st.nextToken());
				adjList[start].add(i);
			}
		}



		int ans = 0; // 걸린 시간
		int cnt = 0; // 끝낸 작업의 개수

		while (true) {
//			System.out.println(ans);
			if (cnt == N) {
				break;
			}
			ArrayList<Integer> done = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (arr[i] == 0) { // 자신에게 향하는 화살표가 0개 라면
					times[i]--; // 걸리는 시간을 1 줄인다.
					if (times[i] == 0) { // 소요시간이 다 지나서 작업이 끝났다면?
						cnt++; // 끝난 작업의 수 1 증가
						arr[i] = -1;
						for (int j = 0; j < adjList[i].size(); j++) {
							done.add(adjList[i].get(j)); // 한번에 업데이트 하기 위해서 저장만 해둔다.
						}
					}
				}
			}
			for(int i = 0; i<done.size();i++) {
				arr[done.get(i)]--;
			}
			ans++;
		}
		System.out.println(ans);
	}
}
