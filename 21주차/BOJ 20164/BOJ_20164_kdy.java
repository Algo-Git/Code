import java.util.Scanner;

public class N20164 {// BOJ 20164. 홀수 홀릭 호석

	static int max, min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String N = sc.next();// 호석이가 처음 시작할 때 가지고 있는 수
		max = Integer.MIN_VALUE;// 호석이가 만들 수 있는 최종값 중 최댓값
		min = Integer.MAX_VALUE;// 호석이가 만들 수 있는 최종값 중 최솟값
		cal(N, 0);// 만들어보기
		System.out.println(min + " " + max);// 최소 최댓값 출력
	}

	private static void cal(String N, int cnt) {
		cnt += count(N);// 현재 수의 홀수 개수 더하기
		if (N.length() > 2) {// 3자리 이상인 경우
			// 조합으로 끊을 위치 모두 시도
			for (int i = 1; i < N.length() - 1; i++) {
				for (int j = i + 1; j < N.length(); j++) {
					// 0~i-1, i~j-1, j~끝 i와 j를 기준으로 3개의 수로 분할해서 더한 값을 새로운 수로 만든 후 연산해보기
					int num1 = Integer.parseInt(N.substring(0, i));
					int num2 = Integer.parseInt(N.substring(i, j));
					int num3 = Integer.parseInt(N.substring(j));
					cal(String.valueOf(num1 + num2 + num3), cnt);
				}
			}
		} else if (N.length() > 1) {// 두자리 수인 경우
			int n = N.charAt(0) - '0';// 10의 자리 수 가져오기
			n += N.charAt(1) - '0';// 1의 자리 수를 더하기
			cal(String.valueOf(n), cnt);// 더한 값을 새로운 수로 여기고 연산
		} else {// 한자리 수인 경우
			min = Math.min(min, cnt);// 최솟값 갱신
			max = Math.max(max, cnt);// 최댓값 갱신
			return;// 종료
		}
	}

	private static int count(String N) {// N의 홀수 갯수 세기
		int cnt = 0;// 홀수 개수
		int n = Integer.parseInt(N);// 정수 N
		while (n != 0) {// N이 0이 될때까지 반복
			if ((n % 10) % 2 == 1) {// 1의 자리 수가 홀수인 경우
				cnt++;// 홀수 개수 +1
			}
			n /= 10;// 10으로 나누기
		}
		return cnt;// 홀수 개수 반환
	}
}
