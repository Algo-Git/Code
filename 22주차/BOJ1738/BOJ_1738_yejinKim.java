import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [1738] 골목길
// 벨만 포드 
// 음의 사이클 -> 양의 사이클
// 양의 사이클이 생기면 -1 출력
// 양의 사이클이 안생기면 -> 첫점에서 끝점까지 DFS하면서 끝점 도착했을 때 거리 합이랑 Dist[end] 랑 같으면 그 경로를 출력

public class BOJ_1738_yejinKim {
	static long[] Dist;
	static int N, M;
	static final int NINF = Integer.MIN_VALUE;
	static ArrayList<Data>[] adjList;
	static boolean[] visited;
	static int[] trace;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Data>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Data(to, weight));
		}

		boolean positiveCycle = bellmanford();

		if (positiveCycle) {
			System.out.println(-1);
		} else {
			visited = new boolean[N + 1];
			visited[1] = true;
			trace = new int[N + 1];
			trace[0] = 1;
			findTrace(1, 0, 1);
		}

	}

	// dfs로 찾아본다
	// 지금 가는 길이 최적 경로일 때만 재귀 돌림
	private static void findTrace(int from, long dist, int order) {
		if (from == N) {
			for(int i = 0; i<order; i++) {
				System.out.print(trace[i] + " ");
			}
			System.exit(0);
		}

		for (Data edge : adjList[from]) {
			if (!visited[edge.to] && Dist[edge.to] == (Dist[from] + edge.weight)) {
				visited[edge.to] = true;
				trace[order] = edge.to;
				findTrace(edge.to, Dist[from] + edge.weight, order + 1);
			}
		}
	}

	private static boolean bellmanford() {
		Dist = new long[N + 1];

		// Dist[1] = 0; // 시작점
		for (int i = 2; i <= N; i++) {
			Dist[i] = NINF;
		}

		// 정점 개수만큼 round
		for (int round = 0; round < N; round++) {
			// 모든 간선을 다 돌면서 확인해 본다.
			for (int i = 1; i <= N; i++) {
				for (Data edge : adjList[i]) {
					if (Dist[edge.to] < Dist[i] + edge.weight) {
						Dist[edge.to] = Dist[i] + edge.weight;

						if (round == N - 1) {
							visited = new boolean[N + 1];
							visited[i] = true;
							if (cycleConnected(i)) { // 양의 서클이 존재하는데 그게 도착 노드와 연결되어있으면 안됨!
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private static boolean cycleConnected(int from) {
		// 도착노드와 연결되어 있으면
		if (from == N) {
			return true;
		}
		boolean flag = false;

		for (Data edge : adjList[from]) {
			if (!visited[edge.to]) {
				visited[edge.to] = true;
				flag |= cycleConnected(edge.to);
			}
		}
		return flag;
	}

	private static class Data {
		int to;
		int weight;

		public Data(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}
