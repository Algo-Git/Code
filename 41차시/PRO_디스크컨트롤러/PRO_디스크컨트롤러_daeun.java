import java.util.Arrays;
import java.util.Comparator;

public class PRO_42627_디스크컨트롤러 {
    public static void main(String[] args) {

//        System.out.print(Solution.solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}));
        System.out.print(Solution.solution(new int[][]{{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 34}, {15, 2}, {35, 43}, {26, 1}}));// 72

    }

    static class Solution {
        public static int solution(int[][] jobs) {
            int answer = 0;

            // 요청 시간 순서대로 정렬
            Arrays.sort(jobs, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0])  // 요청 시간이 같다면 소요 시간이 작은 순서대로 정렬
                        return Integer.compare(o1[1], o2[1]);
                    return Integer.compare(o1[0], o2[0]);   // 요청 시간이 작은 순서대로 정렬
                }
            });


            boolean[] v = new boolean[jobs.length]; // 작업했는지 체크
            v[0] = true;
            answer += jobs[0][1];    // 첫번째 작업의 소요 시간

            int end = jobs[0][0] + jobs[0][1];  // 마지막 작업의 종료시간
            int idx = 0;    // 다음 작업의 idx
            for (int cnt = 1; cnt < jobs.length; cnt++) {   // 작업의 개수만큼 반복
                // 다음 작업 구하기
                boolean isWorking = false;  // 하드디스크가 작업중인지 체크하는 변수
                int time = 1001;    // 다음 작업의 소요시간 (처음에 1000으로 초기화했더니 테케 틀림)
                for (int i = 1; i < jobs.length; i++) {
                    if (v[i]) continue;
                    if (end > jobs[i][0]) {  // 마지막 작업의 종료시간 이전에 요청한 작업이 있을 때
                        int hereTime = jobs[i][1]; // i번째 작업의 소요시간
                        if (time > hereTime) {    // i번째 작업의 소요시간이 더 작다면 다음 작업의 idx 변경
                            idx = i;
                            time = hereTime;
                            isWorking = true;   // 하드디스크 작업중 체크
                        }
                    }
                }

                // 하드디스크가 작업중일 때
                if (isWorking) {
                    v[idx] = true;
                    answer += (end - jobs[idx][0]) + time;    // 요청시간부터 종료시간까지 총 시간
                    end += jobs[idx][1];   // 마지막 작업의 종료시간 변경
                }
                // 하드디스크가 작업중이지 않을 때 다음 작업 구하기
                else {
                    for (int i = 0; i < jobs.length; i++) { // 이미 정렬을 해놨으므로, 방문 안한 작업을 찾으면 바로 작업
                        if (v[i]) continue;
                        v[i] = true;
                        answer += jobs[i][1];
                        end = jobs[i][0] + jobs[i][1];
                        idx = i;
                        break;
                    }
                }
            }

            return answer / jobs.length;
        }
    }
}