import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [골드1] 어항정리
public class BOJ_23291_yejinKim {
	static int N, K;
	static int Min, Max;
	static ArrayList<Integer>[] arr;

	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<Integer>();
			arr[i].add(Integer.parseInt(st.nextToken()));
		}

		int cnt = 0;
		while (true) {
			// 최대값과 최소값을 찾는다
			Min = Integer.MAX_VALUE;
			Max = 0;
			findMinMax();

			if (Max-Min <= K) {
				break;
			}

			// 1. 최소값인 곳에 1을 더함
			minAddOne();

			// 2. 맨 왼쪽에 있는 어항을 그 다음 어항 위에 올린다.
			firstOnSecond();

			// 3. 되는 만큼 굴리기
			while (true) {
				if (!roll()) { // 굴릴 수 없으면 break;
					break;
				}
			}

			// 4. 물고기 조절 작업

			adjustFishNum();

			// 5. 펼치기
			open();

			// 6. 공중부양 2번
			gongjungFirst();
			gongjungSecond();

			// 7. 물고기 조절 작업
			adjustFishNum();

			// 8. 펼치기
			open();

			cnt++;
		}
		System.out.println(cnt);
	}

	private static void gongjungSecond() {
		int ref = N / 2 + N / 4;
		for (int j = 1; j >= 0; j--) {
			for (int i = 0; i < N / 4; i++) {
				arr[ref + i].add(arr[ref - 1 - i].get(j));
			}
		}

		for (int i = N / 2; i < ref; i++) {
			arr[i] = new ArrayList<>();
		}

	}

	private static void gongjungFirst() {
		for (int i = 0; i < N / 2; i++) {
			arr[N - i - 1].add(arr[i].get(0));
			arr[i] = new ArrayList<>();
		}
	}

	private static void open() {
		ArrayList<Integer>[] openarr = new ArrayList[N];

		int idx = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i].size() == 0) {
				continue;
			}
			for (int j = 0; j < arr[i].size(); j++) {
				openarr[idx] = new ArrayList<>();
				openarr[idx].add(arr[i].get(j));
				idx++;
			}
		}

		arr = openarr;

	}

	private static void adjustFishNum() {
		ArrayList<Integer>[] calc = new ArrayList[arr.length];
		for (int i = 0; i < arr.length; i++) {
			calc[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].size(); j++) {
				int sum = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (nx < 0 || nx >= arr.length || ny < 0 || ny >= arr[nx].size()) {
						continue;
					}

					if (Math.abs(arr[i].get(j) - arr[nx].get(ny)) < 5) {
						continue;
					}

					// 자기 칸 위주로 생각하기
					sum += (arr[nx].get(ny) - arr[i].get(j)) / 5;
				}
				calc[i].add(sum);
			}
		}


		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].size(); j++) {
				arr[i].set(j, arr[i].get(j) + calc[i].get(j));
			}
		}
	}

	private static boolean roll() {
		int startIdx = 0; // 굴리기 시작할 인덱스
		int endIdx = 0; // 굴리기 끝나는 인덱스
		int size = 0;

		// 굴리기 시작할 인덱스 찾기
		while (true) {
			if (arr[startIdx].size() != 0) {
				size = arr[startIdx].size();
				break;
			}
			startIdx++;
		}

		// 굴리기 끝낼 인덱스 찾기
		endIdx = startIdx;
		while (true) {
			if (endIdx >= N ||arr[endIdx].size() != size) {
				break;
			}
			endIdx++;
		}

		endIdx--;

		// 못굴리는 경우
		if (endIdx + size >= N) {
			return false;
		}

		// 굴릴 수 있으면?
		for (int i = endIdx; i >= startIdx; i--) {
			for (int j = 0; j < size; j++) {
				arr[endIdx + 1 + j].add(arr[i].get(j));
			}
			arr[i] = new ArrayList<>();
		}
		return true;
	}

	private static void firstOnSecond() {
		arr[1].add(arr[0].get(0));
		arr[0] = new ArrayList<>();
	}

	private static void minAddOne() {
		for (int i = 0; i < N; i++) {
			if (arr[i].get(0) == Min) {
				arr[i].set(0, Min + 1);
			}
		}
	}

	private static void findMinMax() {
		for (int i = 0; i < N; i++) {
			Max = Math.max(arr[i].get(0), Max);
			Min = Math.min(arr[i].get(0), Min);
		}
	}
	
	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i].toString());
		}
		System.out.println();
	}
}
