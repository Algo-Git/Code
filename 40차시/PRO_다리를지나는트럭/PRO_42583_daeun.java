import java.util.*;

public class PRO_42583_다리를지나는트럭 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(2, 10, new int[]{7,4,5,6}));
    }

    static class Solution {
        public static int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 0;
            int len = truck_weights.length;

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < len; i++){
                q.offer(i);
            }

            int[] length = new int[len];    // 트럭마다 다리를 다 건넜는지 체크
            while (!q.isEmpty()){
                answer++;   // 경과 시간
                int idx = q.peek();

                // 현재 다리를 건너고 있는 트럭의 총 무게 구하기
                int doing = 0;
                boolean check = false;
                for (int i = 0; i < len; i++){
                    if(length[i] == bridge_length) continue;
                    if(doing + truck_weights[i] <= weight){
                        if(length[i] == 0) check = true;    // 처음 다리에 들어온 트럭인지 체크
                        length[i]++;    // 현재 인덱스의 트럭이 건넌 다리 길이 + 1
                        doing += truck_weights[i];  // 현재 다리를 건너고 있는 트럭 총 무게에 더하기
                    }else break;
                    if(check) break; // 처음 다리에 들어온 트럭이면 이후 트럭은 현재 시간에 못들어오니 break
                }

                if(length[idx] == bridge_length){   // 다리를 다 건넌 트럭이면 q에서 빼기
                    q.poll();
                }
            }

            return answer + 1;  // 모든 트럭이 다리를 건넌 시간을 포함하기 위해 + 1
        }
    }
}