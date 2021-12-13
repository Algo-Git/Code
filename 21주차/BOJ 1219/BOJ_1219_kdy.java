import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1219 {// BOJ 1219. 오민식의 고민

	static int N, F, T, M;
	static Data[] list;
	static int[] money;
	static long[] dist;
	static long INF = Long.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 도시 수
		F = Integer.parseInt(st.nextToken());// 시작 도시
		T = Integer.parseInt(st.nextToken());// 도착 도시
		M = Integer.parseInt(st.nextToken());// 교통 수단 수
		list = new Data[M];// 교통 수단 정보
		for (int i = 0; i < M; i++) {// 교통 수단 정보 받기
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());// 시작
			int to = Integer.parseInt(st.nextToken());// 끝
			int cost = Integer.parseInt(st.nextToken());// 가격
			list[i] = new Data(from, to, (-1) * cost);// 교통수단 정보 리스트에 추가
		}
		money = new int[N];// 도시를 방문할 때 벌 수 있는 돈
		st = new StringTokenizer(br.readLine());
		dist = new long[N];// 도시에 도착할 때 가지고 있는 돈의 최대 액수
		for (int i = 0; i < N; i++) {// 도시를 방문할 때 벌 수 있는 돈 입력, 돈 최대 액수 배열 초기화
			money[i] = Integer.parseInt(st.nextToken());
			dist[i] = INF;
		}
		dist[F] = money[F];// 출발지는 출발지를 방문했을 때 벌 수 있는 돈을 가지고 시작

		go();// 벨만포드

		if (dist[T] == INF) {// 도착 도시에 갈 수 없는 경우
			System.out.println("gg");
		} else if (dist[T] == Integer.MAX_VALUE) {// 도착 도시에 도착했을 때 돈을 무한히 많이 가지고 있을 수 있는 경우
			System.out.println("Gee");
		} else {
			// 도착 도시에 도착했을 때 가지고 있는 돈의 액수의 최댓값
			System.out.println(dist[T]);
		}
	}

	private static void go() {// 벨만포드
		// 이전에 방문했을 때 가지고 있는 돈의 액수
		long pre = Long.MAX_VALUE;
		// 도시 수에 100번쯤 추가로 반복해보기
		for (int i = 0; i < N + 100; i++) {
			for (int j = 0; j < M; j++) {// 모든 교통수단 사용해보기
				int cur = list[j].from;// 시작점
				int next = list[j].to;// 도착점
				int cost = list[j].cost;// 가격

				if (dist[cur] == INF) {// 시작점에 갈 수 없는 경우
					continue;// 지나가기
				}

				// 기존에 도착점 액수보다 현재 위치에서 도착점으로 갈 때 가질 액수가 큰 경우
				if (dist[next] < (dist[cur] + cost + money[next])) {
					if (i >= N - 1) {// N번째보다 큰 수에서도 값이 변하면 싸이클이 있을 수 있음
						pre = dist[next];// 이전 값 저장해두기
					}
					dist[next] = dist[cur] + cost + money[next];// 현재 위치에서 도착점으로 갈 때 가질 액수 저장
					if (i >= N - 1 && pre < dist[next]) {// 싸이클이 있으면서 이전값보다 큰 값으로 바뀌는 경우
						dist[next] = Integer.MAX_VALUE;// 돈을 무한히 많이 가지고 있을 수 있음 => Long의 최댓값으로 하면 비교할 때 값이 이상해짐ㅠ
					}
				}
			}
		}
	}

	static class Data {// 교통 수단 정보
		// 시작, 끝, 가격
		int from, to, cost;

		public Data(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

	}

}
