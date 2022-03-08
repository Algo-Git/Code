package PROGRAMMERS;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PRO_디스크컨트롤러_HoonyCode {


    class Solution {
        public int solution(int[][] jobs) {
            int answer = 0;

            int length = jobs.length;
            int jobsIdx = 0;
            int end = 0;
            int cnt = 0; // 완료된 개수

            Arrays.sort(jobs, (o1, o2) -> Integer.compare(o1[0], o2[0]));

            PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1[1], o2[1])));
            while (cnt < length) {
                while (jobsIdx < length && jobs[jobsIdx][0] <= end) {
                    pq.offer(jobs[jobsIdx++]);
                }


                if (pq.isEmpty()) {
                    end = jobs[jobsIdx][0];
                } else {
                    int[] cur = pq.poll();
                    answer += cur[1] + end - cur[0];
                    end += cur[1];
                    cnt++;
                }

            }

            return answer/length;
        }
    }
}
