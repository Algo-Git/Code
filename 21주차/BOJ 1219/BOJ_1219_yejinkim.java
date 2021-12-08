import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// [골드1] 오민식의 고민
// 벨만 포드 문제
public class BOJ_1219_yejinkim {
	static int N, M, end;
	static Data[] edges;
	static int[] money;
	static boolean[] visited;
	static final int NINF = Integer.MIN_VALUE;
	static long[] D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 도시 개수
		int start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 교통수단 개수의 개수

		// - 교통수단 돈 + 도시에서 번 돈 => 간선 값

		// 교통수단 돈 저장
		edges = new Data[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Data(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					-Integer.parseInt(st.nextToken()));
		}

		// 마지막 줄에는 오민식이 각 도시에서 벌 수 있는 돈의 최댓값이 0번 도시부터 차례대로 주어진다.
		money = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}

		// 진짜 간선 값 저장
		for (int i = 0; i < M; i++) {
			int idx = edges[i].to;
			edges[i].weight += money[idx];
		}

		D = new long[N];
		for (int i = 0; i < N; i++) {
			D[i] = NINF;
		}

		boolean positiveCircle = bellmanford(start);

//		System.out.println(Arrays.toString(D));
		
		if (D[end] == NINF) {
			System.out.println("gg");
		}else if (positiveCircle) {
			System.out.println("Gee");
		} else {
			System.out.println(D[end]);
		}

	}

	private static boolean bellmanford(int start) {
		D[start] = money[start];
		// N번의 라운드를 돌면서
		for (int i = 0; i < N; i++) {
			// M개의 간선을 모두 확인한다.
			for (int j = 0; j < M; j++) {
				Data data = edges[j];
				if (D[data.from] != NINF && D[data.to] < D[data.from] + data.weight) {
					D[data.to] = D[data.from] + data.weight;

					// 만약 N번째도 업데이트를 한다면.. 양수의 서클이 존재한다
					// => 이 양수 서클이 도착점과 연결되어야지 Gee임 그렇지 않으면 D[end] 출력해야 함 즉 false
					if (i == N - 1) {
						// data.from에서 연결된 것들 중에 end가 있는가..?
						visited = new boolean[N];
						if (dfs(data.from)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// bfs는 왜 안될까..
	private static boolean bfs(int from) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(from);
		visited[from] = true;

		while (!q.isEmpty()) {
			System.out.println(q.toString());
			int cur = q.poll();
			for (Data edge : edges) {
				// 
				if (edge.from == cur && !visited[edge.to]){
					if(edge.to == end) {
						// 서클 존재하지만 도착 가능 => GEE
//						System.out.println("??");
						return true;
					}
					visited[edge.to] = true;
					q.offer(edge.to);
				}
			}
		}
		// 서클 존재하는데 도착 불가능
		return false;
	}
	
	private static boolean dfs(int from) {
		if(from == end) return true;
		visited[from] = true;
		boolean check = false;
		for(Data edge: edges) {
			if(edge.from == from && !visited[edge.to]) {
				check |= dfs(edge.to); 
			}
		}
		return check;
	}

	private static class Data {
		int from;
		int to;
		int weight;

		public Data(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
}
