package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// [골드4] 해킹
// 그래프 + 다익스트라 알고리즘
public class baekjoon_10282 {
	static final int INF = 10000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			ArrayList<Data>[] adjList = new ArrayList[n+1];
			for(int i = 1; i<=n; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for(int i = 0; i<d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				adjList[b].add(new Data(a,s));
			}
			
//			1. 최소거리배열  D와 방문체크배열 visited 만들기		
//			2. 시작점은 D[c] = 0 으로 두고 나머지는 큰 수로 둔다.
			int[] D = new int[n+1];
			for(int i = 1; i<=n; i++) {
				D[i] = INF;
			}
			D[c] = 0;
			int cnt = 0;
			boolean[] visited = new boolean[n+1];
			
			int ans = 0; // 마지막 컴퓨터 감염되는데 소요시간
			
//			5. 3-4번 반복
			while(true) {
//				6. 방문한 정점의 개수가 n개가 되거나 더이상 방문할 곳이 없으면 끝
				if(cnt == n) break; // 만약 모든게 감염된다면 끝
				
				// 최소값 찾기
				int k = -1;
				int min = INF;

//			3. 방문(고려)하지 않았고,  거리가 최소인 정점 k을 선택한다. 
				for(int i = 1; i<=n; i++) {
					if(visited[i]) {
						continue;
					}
					if(min>D[i]) {
						min = D[i];
						k = i;
					}
				}
//				6. 방문한 정점의 개수가 n개가 되거나 더이상 방문할 곳이 없으면 끝				
				if(k == -1) break;
				
				visited[k] = true;
				cnt ++;
				ans = Math.max(ans, min);
				
//				4.  k와 연결된 모든 정점을 확인(i) 하면서 거리를 비교한다. 더 작은 것으로 D[i]를 업데이트
//				c - (다른 경유지) → i ⇒ 즉, D[i]
//				c - k 경유 → i ⇒ 즉, D[k] + k~i 시간
				
				// k를 경유해서 가는 경로와 비교하기
				for(Data item:adjList[k]) {
					if(min + item.time<D[item.idx]) {
						D[item.idx] = min + item.time;
					}
				}
			}
			
			System.out.println(cnt + " " + ans);
		}
		
	}
	private static class Data{
		int idx;
		int time;

		public Data(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
	}
}
