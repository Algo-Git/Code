package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[브론즈2] 시험감독
public class BOJ_13458_kyj {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] people = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		long cnt = 0;
		for(int i = 0; i<N; i++) {
			people[i] -= b;
			cnt++;
			if(people[i]>0) {
				int mok = people[i]/c;
				if(people[i]%c == 0) {
					cnt += mok;
				}else {
					cnt += (mok+1);
				}
			}
		}
		System.out.println(cnt);
	}
}
