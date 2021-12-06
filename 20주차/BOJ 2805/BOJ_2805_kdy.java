import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2805 {// BOJ 2805. 나무 자르기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 나무 수
		int M = Integer.parseInt(st.nextToken());// 집에 가져갈 나무 길이
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];// 나무 길이 배열
		int max = 0;// 제일 긴 나무 길이
		for (int i = 0; i < N; i++) {// 나무 길이 입력받기
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);// 가장 긴 나무 길이 저장
		}
		// 왼쪽 오른쪽 두 부분으로 나눠서 탐색
		int l = 0;// 왼쪽 시작 길이
		int r = 0;// 오른쪽 시작 길이
		while (l <= max) {
			// int로 하면 범위 넘어가서 틀림ㅠㅠ
			long rsum = 0;// 절단기 높이를 r로 지정했을 때 가져가는 나무 길이
			r = (l + max) / 2;// 절단기 높이 범위의 중간=>오른쪽 범위의 시작길이
			for (int i = 0; i < N; i++) {// 절단기 높이를 r로 했을 때 가져갈 나무 길이 더하기
				if (arr[i] - r > 0) {// 절단기로 나무가 잘린 경우
					rsum += (arr[i] - r);// 집에 가져갈 수 있는 나무 길이에 더하기
				}
			}
			if (M <= rsum) {// 가져가야 할 나무 길이보다 길거나 같을 때=>같더라도 절단기 길이가 최대인 값 구해야하기때문에 높여보기
				l = r + 1;// 절단기 높이 높이기
			} else {// 가져가야 할 나무 길이보다 짧을 때
				max = r - 1;// 절단기 높이 낮추기
			}
		}
		System.out.println(max);// M미터의 나무를 집에 가져가기 위해 절단기에 설정할 수 있는 최대값 출력
	}

}
