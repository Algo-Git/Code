import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005_kdy {// BOJ 1005. ACM Craft

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());// 테스트케이스 수
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());// 건물 수
			int K = Integer.parseInt(st.nextToken());// 건설순서 규칙 수
			int[] time = new int[N];// 건물 건설완료 시간 배열
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {// 건물별 건설 시간 입력
				time[i] = Integer.parseInt(st.nextToken());
			}
			ArrayList<Integer>[] list = new ArrayList[N];// 건물 완성 후 지을 수 있는 건물 리스트
			for (int i = 0; i < N; i++) {// 리스트 초기화
				list[i] = new ArrayList<>();
			}
			int[] ind = new int[N];// 각 건물을 짓기 전에 완료되어야할 건물 수
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;// 먼저 지어야 하는 건물 번호
				int y = Integer.parseInt(st.nextToken()) - 1;// x 건물 건설완료 후 지어야 하는 건물 번호
				list[x].add(y);// x->y순으로 지어야 함
				ind[y]++;// y를 짓기 전 건설완료해야 할 건물 수 +1
			}
			int W = Integer.parseInt(br.readLine()) - 1;// 백준이가 승리하기 위해 건설해야 할 건물 번호
			int[] maxt = new int[N];// 건물을 짓기 전 지어야 할 건물 중 가장 마지막에 지어진 건물이 완료된 시간
			if (ind[W] != 0) {// W를 짓기 전에 건설해야 할 건물이 있는 경우
				Queue<Integer> q = new LinkedList<>();// 건물 건설 명령을 담은 큐
				for (int i = 0; i < N; i++) {
					if (ind[i] == 0) {// 바로 지을 수 있는 건물들을 명령에 넣기
						q.offer(i);
					}
				}
				while (!q.isEmpty()) {// 모든 명령을 수행할 때까지 => 모든 건물을 건설
					int cur = q.poll();// 현재 건설하는 건물 번호
					if (cur == W) {// W를 건설
						break;// 반복 종료
					}
					for (Integer integer : list[cur]) {// 현재 건설하는 건물 이후에 지을 건물 살펴보기
						ind[integer]--;// 이전에 지어야할 건물 수 -1
						maxt[integer] = Math.max(maxt[integer], time[cur]);// 이전에 지은 건물 중 가장 늦게 완료된 시간 저장
						if (ind[integer] == 0) {// 건물을 바로 지을 수 있는 경우
							time[integer] += maxt[integer];// 건물을 짓기 시작하는 시간을 더해줌
							q.offer(integer);// 건물 건설 명령 내리기
						}
					}
				}
			}
			System.out.println(time[W]);// W건물을 짓는데 걸린 최소시간 출력
		}

	}

}
