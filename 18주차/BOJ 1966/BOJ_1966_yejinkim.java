import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1966_yejinkim{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case<=T; test_case++) {
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int want = Integer.parseInt(st.nextToken());
			
			List<Integer> prior = new ArrayList<>();
			Queue<Data> q = new LinkedList<>();
			
			st =  new StringTokenizer(br.readLine());
			
			for(int i = 0; i<n; i++) {
				int p= Integer.parseInt(st.nextToken());
				q.offer(new Data(i, p));
				prior.add(p);
			}
			
			Collections.sort(prior, Collections.reverseOrder());
			
			int k = 0; // 높은 우선순위가 뭔지 알아오기 위한 변수 
			
			int Max = prior.get(k);
			int cnt = 0;
			int ans = 0;
			while(true) {
				Data data = q.poll();
				if(data.y >= Max) { // 출력 가능
					cnt ++;
					if(data.x == want) { // 원하는 번호라면
						ans = cnt; 
						break;
					} else { // 아니라면
						k++;
						Max = prior.get(k);
					}
				} else { // 출력 불가능하다면
					q.offer(data);
				}
				
			}
			System.out.println(ans);
		}
	}
	
	private static class Data{
		int x; // 
		int y;
		public Data(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
