package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [실버1] 계란으로 계란치기
// 완전탐색 dfs
public class BOJ_16987_kyj {
	static int N;
	static int Max;
	static int[] weight;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] durability = new int[N];
		weight = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			durability[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, durability, 0);
		System.out.println(Max);
	}

	private static void dfs(int num, int[] durability, int cnt) {
		if (num == N || cnt == N) {
			Max = Math.max(cnt, Max);
			return;
		}
		// num번 계란을 손에든다.
		if (durability[num] <= 0) {
			dfs(num+1, durability,cnt);
		} else {
			for (int i = 0; i < N; i++) {
				// 깨진 계란이라면 continue
				if (durability[i] <= 0) {
					dfs(num+1, durability, cnt);
					continue;
				}
				if (i == num) {
					continue;
				}

				int[] nextDura = durability.clone();
				int breaknow = 0;
				nextDura[i] -= weight[num];
				nextDura[num] -= weight[i];
				if(nextDura[i]<=0) {
					breaknow ++;
				}
				if(nextDura[num]<=0) {
					breaknow ++;
				}
				dfs(num+1, nextDura, cnt+breaknow);
			}
		}
	}
}
