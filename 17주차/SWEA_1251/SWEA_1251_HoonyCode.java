import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//크루스칼 풀이법
public class SW1251 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 섬의 개수

			Edge[] edgeList = new Edge[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				edgeList[i] = new Edge(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				edgeList[i].y = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine()); // 세율

			boolean[] v = new boolean[N];
			long[] minEdge = new long[N];

			for (int i = 0; i < N; i++) {
				minEdge[i] = Long.MAX_VALUE;
			}

			long result = 0;
			int cnt = 0; // 최소신장트리 비용
			minEdge[0] = 0; // 임의의 시작점 0의 간선거리를 0으로 초기화

			for (int i = 0; i < N; i++) {
				// 1. 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 찾기
				long min = Long.MAX_VALUE;
				int minVertex = -1; // 최소간선비용의 정점번호

				for (int j = 0; j < N; j++) {
					if (!v[j] && min > minEdge[j]) {
						min = minEdge[j];
						minVertex = j;
					}
				}
				if (minVertex == -1) {
					break;
				}
				v[minVertex] = true; // 신장트리에 포함시킴
				result += min; // 간선비용 누적
				if (cnt++ == N - 1)
					break;

				// 2. 선택된 정점 기준으로 신장트리에 연결되지 않은 타 정점과의 간선 거리 최소로
				for (int j = 0; j < N; j++) {
					double d = dist(edgeList[minVertex], edgeList[j]);
					if (!v[j] && d != 0 && minEdge[j] > d) {
						minEdge[j] = (long) d;
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(Math.round(result * E)).append("\n");
		}
		System.out.println(sb);
	}

	static double dist(Edge e1, Edge e2) {
		return Math.pow(e1.x - e2.x, 2) + Math.pow(Math.abs(e1.y - e2.y), 2);
	}

	static class Edge implements Comparable<Edge> {
		int x, y;
		long len = Integer.MAX_VALUE;

		public Edge(int x) {
			this.x = x;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(len, o.len);
		}
	}
}
