import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class N17952 {// BOJ 17952. 과제는 끝나지 않아!

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());// 학기 시간
		StringTokenizer st = null;
		int score = 0;// 과제 점수
		Stack<Data> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());// 과제 여부
			if (num == 0) {// 과제X
				if (!stack.isEmpty()) {// 이전에 하던 과제가 있으면
					Data d = stack.pop();// 이전에 하던 과제 가져오기
					if (d.T == 1) {// 과제를 완료
						score += d.score;// 점수 받기
					} else {// 과제 완료X
						stack.push(new Data(d.T - 1, d.score));// 과제 1만큼하고 목록에 추가
					}
				}
			} else {// 과제O
				int A = Integer.parseInt(st.nextToken());// 점수
				int T = Integer.parseInt(st.nextToken());// 시간
				if (T == 1) {// 과제를 완료
					score += A;// 점수에 추가
				} else {// 과제가 남음
					stack.push(new Data(T - 1, A));// 과제를 1만큼하고 목록에 추가
				}
			}
		}
		System.out.println(score);// 과제 점수 출력
	}

	static class Data {// 과제 정보
		int T, score;// 시간, 점수

		public Data(int t, int score) {
			T = t;
			this.score = score;
		}
	}

}
