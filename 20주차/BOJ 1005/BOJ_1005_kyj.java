package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [골드3] ACM Craft
// 위상 정렬
// 1. 작업에게 향하는 화살표의 개수가 0개면 실행함 => priority queue에 넣는다.
// 2. 시간이 각각 다름 => pq를 이용해서 남은 작업시간이 가장 적은 것(t초)을 작업 완료 시킴
//		pq 안에 있는(건설중인 작업) 빼와서 t초씩 감소시키고 다시 pq에 넣는다.
// 3. 1번 반복

// 시간: 1176ms => 느린듯
public class BOJ_1005_kyj {
	static int Min;
	static int N; // 건물의 개수
	static int[] times; // 걸리는 시간들
	static int[] allows; // 나에게 향하는 화살표의 개수
	static ArrayList<Integer>[] adjList; // 건설순서 화살표 A->B;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			times = new int[N + 1];
			adjList = new ArrayList[N + 1];

			for (int i = 1; i <= N; i++) {
				times[i] = Integer.parseInt(st.nextToken());
				adjList[i] = new ArrayList<>();
			}

			allows = new int[N + 1];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				allows[y]++; // 나에게 향하는 화살표의 개수 증가
				adjList[x].add(y); // 건설순서 표시
			}
			
			int W = Integer.parseInt(br.readLine());

			// 1. 작업에게 향하는 화살표의 개수가 0개면 실행함 => priority queue에 넣는다.
			PriorityQueue<Data> pq = new PriorityQueue<>();
			Min = 0;
			outer: while (true) {
				for (int i = 1; i <= N; i++) {
					if (allows[i] == 0) {
						pq.offer(new Data(i, times[i])); // pq에 넣기
						allows[i] = -1; // 작업완료라고 표시
					}
				}
				// 2. 시간이 각각 다름 => pq를 이용해서 남은 작업시간이 가장 적은 것(t초)을 작업 완료 시킴
				Data shortest = pq.poll();
				Min += shortest.time;
				
				if (shortest.idx == W) {
					break;
				}
				
				for (int i = 0; i < adjList[shortest.idx].size(); i++) {
					allows[adjList[shortest.idx].get(i)]--; // 화살표 개수 줄임
				}

				// pq 안에 있는(건설중인 작업) 빼와서 t초씩 감소시키고 다시 pq에 넣는다.
				PriorityQueue<Data> temp = new PriorityQueue<>();
				
				int size = pq.size();
				for (int i = 0; i < size; i++) { // *이부분 주의* pq.size는 반복문 안에서 계속 변하는데 이걸 for문에서 사용해서 오류남 
					Data cur = pq.poll();
					cur.time -= shortest.time;
					if (cur.time != 0) { // 시간이 남아있다면
						temp.offer(cur);
					} else { // 시간이 안남아있다면?
						for (int j = 0; j < adjList[cur.idx].size(); j++) {
							allows[adjList[cur.idx].get(j)]--; // 화살표 개수 줄임
						}
						if (cur.idx == W) {
							break outer;
						}
					}
				}
				pq = temp;
				// 3. 1번 반복
			}

			System.out.println(Min);
		}

	}

	private static class Data implements Comparable<Data> {
		int idx;
		int time;

		public Data(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}

		@Override
		public int compareTo(Data o) {
			return this.time - o.time;
		}

		@Override
		public String toString() {
			return "Data [idx=" + idx + ", time=" + time + "]";
		}
		
	}
}
