import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1738_kdy {// BOJ 1738. 골목길

	static int n, m, INF;
	static ArrayList<Data>[] list;
	static int[] dis, route;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		INF = -1000000;
		list = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Data(v, w));
		}
		dis = new int[n];
		for (int i = 0; i < n; i++) {
			dis[i] = INF;
		}
		dis[0] = 0;
		route = new int[n];
		bf();
		if (dis[n - 1] == INF) {
			System.out.println(-1);
		} else {
			int cur = n - 1;
			String str = n + " ";
			while (cur != 0) {
				int pre = route[cur];
				str = (pre + 1) + " " + str;
				cur = pre;
			}
			System.out.println(str);
		}
	}

	private static boolean bfs(int st) {
		if (st == n - 1) {
			return true;
		}
		Queue<Integer> q = new LinkedList<>();
		boolean[] v = new boolean[n];
		q.offer(st);
		v[st] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == n - 1) {
				return true;
			}
			for (Data d : list[cur]) {
				if (v[d.v]) {
					continue;
				}
				q.offer(d.v);
				v[d.v] = true;
			}
		}
		return false;
	}

	private static void bf() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (Data d : list[j]) {
					int cur = j;
					int next = d.v;
					int cost = d.w;

					if (dis[cur] == INF) {
						continue;
					}
					if (dis[next] < dis[cur] + cost) {
						route[next] = cur;
						dis[next] = dis[cur] + cost;
						if (i == n - 1 && bfs(next)) {
							dis[n - 1] = INF;
							return;
						}
					}
				}
			}
		}
	}

	static class Data {
		int v, w;

		public Data(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

}
