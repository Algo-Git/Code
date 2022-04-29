package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class BOJ_16202_HoonyCode {

	static int N, M, K;
	static int[] parent;
	static Edge[] edgs;

	public static void main(String[] args) throws Exception {

		input();
		init();

	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] in = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		K = Integer.parseInt(in[2]);

		edgs = new Edge[M];

		for (int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			edgs[i] = new Edge(Integer.parseInt(in[0]), Integer.parseInt(in[1]), i + 1);
		}
		
		int[] arr = new int[K];
		
		
		loop: for (int t = 0; t < K; t++) {
			int cnt = 0;
			int weight = 0;
			init();
			
			if(t > 0 && arr[t-1] == 0) {
				sb.append(0).append(' ');
				continue loop;
			}
		
			for (int i = t; i < M; i++) {

				if (!union(edgs[i].to, edgs[i].next))
					continue;

				cnt++;
				weight += edgs[i].weight;

				if (cnt == N - 1) {
					sb.append(weight).append(' ');
					arr[t] = weight;
					continue loop;
				}
			}

			arr[t] = 0;
			sb.append(0).append(' ');
		}

		System.out.println(sb.toString());
	}

	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y) {
			return false;
		}

		parent[x] = parent[y];
		return true;
	}

	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	private static void init() {
		parent = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static class Edge {
		int to;
		int next;
		int weight;

		public Edge(int to, int next, int weight) {
			super();
			this.to = to;
			this.next = next;
			this.weight = weight;
		}
	}
}
