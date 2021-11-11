package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [D4]하나로
// 총 환경 부담금을 최소로 지불하며, N개의 모든 섬을 연결할 수 있는 교통 시스템을 설계하시오.
// => mst
public class swExpert_1251 {
	static int N;
	static int[] X, Y;
	static final long INF = Long.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			X = new int[N];
			Y = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				Y[i] = Integer.parseInt(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			
			// 여기부터 최소 거리 구하기
			long hap= 0;
			
			long[] D = new long[N];
			boolean[] visited = new boolean[N];
			for(int i =0; i<N; i++) {
				D[i] = INF;
			}
			
			D[0] = 0;
			visited[0] = true;
			int cnt = 1; // 방문한 섬 수
			int cur = 0;
			
			while(true) {
				long min = Long.MAX_VALUE;
				int next = cur;
				
				for(int i = 0; i<N; i++) {
					// 방문했거나 자기자신이면 continue;
					if(i == cur || visited[i]) {
						continue;
					}
					// 그렇지 않으면 값을 업데이트
					D[i] = Math.min(D[i], calcDist(i,cur));
					if(min>D[i]) {
						min = D[i];
						next = i;
					}
				}
				
				
				hap += min;
				visited[next] = true;
				cnt++;
				cur = next;
				
				if(cnt == N) {
					break;
				}
			}
			
			long ans = Math.round(E*hap); 
			System.out.printf("#%d %d%n",test_case, ans);
		}
	}
	private static long calcDist(int i, int cur) {
		return (long) (Math.pow(X[i]-X[cur],2)+Math.pow(Y[i]-Y[cur],2));
	}
}
