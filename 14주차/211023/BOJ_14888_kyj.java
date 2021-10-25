package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버1] 연산자 끼워넣기 2048개의 조합이라서 bf 쓸수있음
public class BOJ_14888_kyj {
	static int N;
	static int[] nums;
	static int[] oper;
	static int Min;
	static int Max;
	static boolean[] visited;
	static int[] orderedOper;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		int[] op = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		oper = new int[N - 1];
		int k = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < op[i]; j++) {
				oper[k] = i;
				k++;
			}
		}
		Min = Integer.MAX_VALUE;
		Max = Integer.MIN_VALUE;
		visited = new boolean[N - 1];
		orderedOper = new int[N - 1];

		dfs(0);
		System.out.println(Max);
		System.out.println(Min);
	}

	static void dfs(int cnt) {
		if (cnt == N - 1) {
			int ans = nums[0];
			for (int i = 0; i < N - 1; i++) {
				switch (orderedOper[i]) {
				case 0:
					ans += nums[i+1];
					break;
				case 1:
					ans -= nums[i+1];
					break;
				case 2:
					ans *= nums[i+1];
					break;
				case 3:
					ans /= nums[i+1];
					break;
				}
			}
			Max =Math.max(Max, ans);
			Min =Math.min(Min, ans);
			
			return;
		}
		
		for(int i =0; i<N-1; i++) {
			if(visited[i]) {
				continue;
			}
			visited[i]=true;
			orderedOper[cnt] = oper[i];
			
			dfs(cnt+1);
			visited[i] = false;
		}
		
	}
}
