package PRO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PRO_가장먼노드_HoonyCode

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        List<Integer>[] list = new List[n + 1];
        for (int i = 1; i < n + 1; i++) list[i] = new ArrayList<>();

        for (int[] ed : edge) {
            list[ed[0]].add(ed[1]);
            list[ed[1]].add(ed[0]);
        }

        int[] cnt = new int[n + 1];
        boolean[] v = new boolean[n + 1];
        Queue<Pair> queue = new LinkedList<>()inkedList<>();
        queue.offer(new Pair(1, 0));
        v[1] = true;

        Pair cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            cnt[cur.cnt]++;

            for (int next : list[cur.val]) {
                if (v[next]) continue;
                v[next] = true;
                queue.offer(new Pair(next, cur.cnt + 1));
            }
        }

        for (int i = n; i > 0; i--) {
            if (cnt[i] > 0) {
                answer = cnt[i];
                break;
            }
        }

        return answer;
    }
}

class Pair {
    int val;
    int cnt;

    public Pair(int val, int cnt) {
        this.val = val;
        this.cnt = cnt;
    }
}
