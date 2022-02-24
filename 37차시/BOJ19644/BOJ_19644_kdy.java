import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N19644 {// BOJ 19644. 좀비 떼가 기관총 진지에도 오다니
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());// 기관총 진지 앞쪽 길의 거리
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Ml = Integer.parseInt(st.nextToken());// 유효 사거리
        int Mk = Integer.parseInt(st.nextToken());// 기관총 화력
        int C = Integer.parseInt(br.readLine());// 수평 세열 지향성 지뢰 수
        Queue<Integer> q = new LinkedList<>();// 지뢰 사용 위치 저장할 큐
        for (int i = 0; i < L; i++) {// 진지 앞쪽 길이만큼 반복
            int zombie = Integer.parseInt(br.readLine());// 해당 위치의 좀비 체력
            if (!q.isEmpty() && q.peek() + Ml - 1 < i) q.poll();// 지뢰를 사용했던 기록이 있고 사용한 위치가 현재 위치와 상관없는 경우 => 기록 제거
            if (zombie != 0 && zombie - ((Ml - q.size()) * Mk) > 0) {// 좀비가 있으면서 기관총으로 좀비를 죽일 수 없는 경우
                C -= 1;// 지뢰 사용
                if (C < 0) break;// 사용할 지뢰가 없는 경우 => 죽음
                q.offer(i);// 지뢰 사용 위치 기록
            }
        }
        if (C >= 0) System.out.println("YES");// 살아남을 수 있다면 YES 출력
        else System.out.println("NO");// 살아남을 수 없다면 NO 출력
    }
}
