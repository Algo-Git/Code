import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N10282 {// BOJ 10282. 해킹

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());// 테스트케이스 수
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());// 컴퓨터 수
			int d = Integer.parseInt(st.nextToken());// 의존성 개수
			int c = Integer.parseInt(st.nextToken()) - 1;// 해킹당한 컴퓨터 번호
			ArrayList<Data> list[] = new ArrayList[n];// 의존성 리스트
			for (int i = 0; i < n; i++) {// 리스트 초기화
				list[i] = new ArrayList<>();
			}
			for (int i = 0; i < d; i++) {// 의존성 정보 입력
				st = new StringTokenizer(br.readLine());
				// b컴퓨터가 감염되면 s초 후 a컴퓨터 감염
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int s = Integer.parseInt(st.nextToken());
				list[b].add(new Data(a, s));// 리스트에 의존성 저장
			}
			int[] D = new int[n];// 출발점에서 해당 컴퓨터까지 최단감염시간 배열
			Arrays.fill(D, Integer.MAX_VALUE);// 최댓값으로 초기화
			D[c] = 0;// 해킹당한 컴퓨터는 이미 감염됨 => 감염되기까지 걸리는 시간 0
			for (Data data : list[c]) {// 해킹당한 컴퓨터로인해 감염될 컴퓨터 시간 저장
				D[data.a] = data.s;
			}
			boolean[] v = new boolean[n];// 방문 체크 배열
			int cur = 0;// 현재 컴퓨터
			for (int i = 0; i < n; i++) {
				int min = Integer.MAX_VALUE;// 최단 시간
				for (int j = 0; j < n; j++) {// 방문하지 않은 컴퓨터 중 최단시간이 걸리는 컴퓨터 찾기
					if (v[j]) {// 방문한 경우
						continue;
					}
					if (D[j] < min) {// 최단 시간인 컴퓨터
						min = D[j];// 최단 시간 수정
						cur = j;// 컴퓨터 번호 저장
					}
				}
				v[cur] = true;// 방문 체크

				for (Data data : list[cur]) {// 현재 방문한 컴퓨터를 정점으로 감염되는 컴퓨터 처리
					if (v[data.a]) {// 방문한 경우
						continue;
					}
					// 컴퓨터가 감염되는데 걸리는 시간을 현재 기록된 시간과 현재 방문한 정점을 거쳐서 감염되는 시간 중 최솟값 저장
					D[data.a] = Math.min(D[data.a], data.s + min);
				}
			}
			int max = 0;// 마지막 컴퓨터가 감염되기까지 걸리는 시간 => 감염된 컴퓨터 중 최장시간
			int cnt = 0;// 감염된 컴퓨터 수
			for (int i = 0; i < n; i++) {
				if (D[i] != Integer.MAX_VALUE) {// 초기값에서 값이 변경된 경우 => 감염됨
					cnt++;// 감염된 컴퓨터 수 +1
					max = Math.max(max, D[i]);// 감염된 컴퓨터 중 마지막으로 감염된 컴퓨터 시간 저장
				}
			}
			System.out.println(cnt + " " + max);// 감염되는 컴퓨터 수, 마지막 컴퓨터가 감염되기까지 걸리는 시간 출력
		}
	}

	static class Data {// 컴퓨터 의존성 정보
		int a, s;// 의존성을 가지는 컴퓨터 번호, 감염 시간

		public Data(int a, int s) {
			this.a = a;
			this.s = s;
		}

	}

}
