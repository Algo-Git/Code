package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_2715 {

	static int[] arr;
	static int[] sol;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		List<Integer> list = new ArrayList<>();

		for (int t = 0; t < tc; t++) {
			String[] in = br.readLine().split(" ");
			N = Integer.parseInt(in[0]);
			list.clear();
			
			arr = new int[N];
			sol = new int[N];
			

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(in[i + 1]);
				sol[i] = Math.abs(arr[i]);
			}

			Arrays.sort(sol);


			//끝부터 -> 0까지
			for (int i = N - 1; i > -1; i--) {
				int K = sol[i]; // 답 = 3
				int len = 0; // 길이 = 0

				for (int j = i; j > -1; j--) {
					if (K == Math.abs(arr[j])) { // 답 == arr[j] 절대값이
						len = j; //길이 반환
						break;
					}
				}
				
				if(len == i && arr[len] > 0) continue; //길이가 정답의 길이와 같고 양수
				
				//길이가 정답이 길이와 같지않으면 위치를 이동시켜줘야함!

				if (len > 0) {
					list.add(len + 1); // 답 출력하기 위한 리스트
					change(len + 1); //
				}
				
				
				// 3 2 1  -> // -3 2 1
				if(arr[0] > 0 && i != 0) {
					list.add(1); 
					change(1);
				}
				
				// -1 -2 3 
				list.add(i+1);
				change(i+1);
			}
			
			sb.append(list.size()).append(' ');
			for(int temp : list) {
				sb.append(temp).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.print(sb.toString());
	}

	public static void change(int len) {
		int[] temp = Arrays.copyOf(arr, len);

		for (int i = 0; i < len; i++) {
			arr[i] = -temp[len - 1 - i];  // -1 -2 -3 ->  3 2 1
		}
	}
}
