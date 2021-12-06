package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [실버3] 나무자르기
// binary search
// 자료형 주의
// 기저조건 주의
public class BOJ_2805_kyj {
	static long M;
	static int N;
	static long[] height;
	static long ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		
		height = new long[N];
		st =new StringTokenizer(br.readLine());
		long m = 0;
		for(int i = 0; i<N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			m = Math.max(m, height[i]);
		}
		
		binarysearch(0,m);
		
		System.out.println(ans);

	}

	private static void binarysearch(long start, long end) {
		if(end-start ==1) {
			return;
		}
		long mid = (start + end)/2;
		
		// M보다는 커야함.
		long sum = 0;
		for(int i = 0; i<N;i++) {
			if(height[i]>mid) {
				sum += height[i]-mid;
			}
		}
		
		if(sum<M) {
			binarysearch(start,mid);
		}else {
			ans = mid;
			binarysearch(mid,end);
		}
	}
}
