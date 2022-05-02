package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_트리_HoonyCode {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] in = br.readLine().split(" ");
        List<Integer>[] lists = new List[N];

        for (int i = 0; i < N; i++) lists[i] = new ArrayList<>();

        int root = -1;
        for (int i = 0; i < N; i++) {
            int parentNode = Integer.parseInt(in[i]);

            if (parentNode == -1) {
                root = i;
            } else {
                lists[parentNode].add(i);
            }
        }

        int removeNode = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);

        Integer cur;
        int answer = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            if (cur == removeNode) continue;

            boolean flag = false;

            for (int next : lists[cur]) {
                if (next == removeNode) continue;
                queue.offer(next);
                flag = true;
            }

            if (!flag) answer++;

        }


        System.out.println(answer);
    }
}
