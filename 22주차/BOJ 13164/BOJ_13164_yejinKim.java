import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [골드5] 행복유치원
// 순서대로 정렬되어 있음
// 모든 아이들의 키 차이를 누적 => hap 변수
// 우선순위 큐에 아이들의 키차이를 저장해 놓음(내림차순)
// 키 차이 누적에서 가장 큰 키차이~K-1번째로 큰 키차이까지 누적된 것에서 뺀다 
public class BOJ_13164_yejinKim {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		st = new StringTokenizer(br.readLine());
		
		long hap = 0;
		
		int height_before = Integer.parseInt(st.nextToken()); // 첫번째 아이
		pq.add(0);
		
		for(int i = 1; i<N; i++) {
			int height = Integer.parseInt(st.nextToken());
			int diff = height-height_before;
			hap += diff;
			pq.add(diff);
			height_before= height;
		}
		
		// 가장 큰 키차이 부터 k-1 번째로 큰 키차이 뺀다.
		for(int i = 0; i<K-1; i++) {
			hap -= pq.poll();
		}
		
		System.out.println(hap);
	}

}
