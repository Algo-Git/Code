import java.util.Scanner;

public class N16987 {// BOJ 16987. 계란으로 계란치기

	static int N, max;
	static int[][] egg;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		egg = new int[N][2];// 계란 배열
		for (int i = 0; i < egg.length; i++) {// 계란 정보 입력
			egg[i][0] = sc.nextInt();// 내구도
			egg[i][1] = sc.nextInt();// 무게
		}
		max = 0;// 최대로 깰 수 있는 계란 수
		if (N != 1) {// 계란이 1개 이상인 경우 => 1개인 경우 깨질 수 있는 계란 수 0
			go(0, 0);// 0번째 계란부터 시작, 깨진 계란 0개
		}
		System.out.println(max);//꺨 수 있는 계란의 최대 갯수
	}

	//순서대로 계란 깨기(가장 왼쪽 계란 번호, 깨진 계란 수)
	private static void go(int num, int cnt) {
		if (num == N) {//가장 최근에 든 계란이 가장 오른쪽에 위치한 계란인 경우
			max = Math.max(max, cnt);//깰 수 있는 계란의 최대 갯수 반영
			return;//계란을 치는 과정 종료
		}
		if (egg[num][0] <= 0) {//손에 든 계란이 깨졌을 때
			go(num + 1, cnt);//다음 계란으로 넘어가기
			return;
		}
		for (int i = 0; i < N; i++) {//깨지지않은 다른 계란 중 하나 쳐보기
			int c = 0;//추가로 깨진 계란 수
			if (i == num || egg[i][0] <= 0) {//손에 든 계란이거나 이미 깨진 계란인 경우 
				continue;//다른 계란 치기
			}
			egg[num][0] -= egg[i][1];//손에 든 계란의 내구도 감소
			egg[i][0] -= egg[num][1];//친 계란의 내구도 감소
			if (egg[num][0] <= 0) {//손에 든 계란이 깨진 경우
				c++;//깨진 계란 수 +1
			}
			if (egg[i][0] <= 0) {//친 계란이 깨진 경우
				c++;//깨진 계란 수 +1
			}
			go(num + 1, cnt + c);//다음 계란으로 넘어가기 => 추가로 깨진 계란 수 반영
			egg[num][0] += egg[i][1];//손에 든 계란 내구도 복구
			egg[i][0] += egg[num][1];//친 계란 내구도 복구
		}
		//다른 계란이 모두 깨져있는 경우를 위해 값 갱신
		max = Math.max(cnt, max);//지금까지 깬 계란 수 반영
	}
}
