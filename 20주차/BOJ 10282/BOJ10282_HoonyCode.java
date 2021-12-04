import java.awt.datatransfer.ClipboardOwner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10282_HoonyCode {

    static int n, d, c;
    static List<Pair>[] list;
    static int computerCount, totalTime;
    static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        String[] input;
        for (int tc = 0 ; tc < T ; tc++){
            input = br.readLine().split(" ");

            n =  Integer.parseInt(input[0]);
            d = Integer.parseInt(input[1]);
            c = Integer.parseInt(input[2]);

            list = new List[n+1];

            for (int i = 1 ; i <= n ; i++){
                list[i] = new ArrayList<Pair>();
            }

            // b -> a s초뒤에 공격가능하다.
            int a, b, s;
            for (int i = 0 ; i < d;  i++){
                input = br.readLine().split(" ");
                a = Integer.parseInt(input[0]);
                b = Integer.parseInt(input[1]);
                s = Integer.parseInt(input[2]);
                list[b].add(new Pair(a, s));
            }

            computerCount = 0;
            totalTime = 0;

            bfs();

            sb.append(computerCount).append(" ").append(totalTime).append('\n');
        }
        System.out.print(sb.toString());
    }

    private static void bfs() {

        PriorityQueue<Pair> que = new PriorityQueue<>();
        boolean[] v = new boolean[n+1];
        res = new int[n+1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[c] = 0;
        que.offer(new Pair(c, 0));

        Pair now;
        while (!que.isEmpty()){
            now = que.poll();
            if (v[now.computNum]) continue;
            v[now.computNum] = true;

            for (Pair temp : list[now.computNum]){
                res[temp.computNum] = Math.min(res[temp.computNum], temp.time + res[now.computNum]);
                que.offer(new Pair(temp.computNum, res[temp.computNum]));
            }
        }

        for (int i = 1; i <= n ; i++){
            if(res[i] == Integer.MAX_VALUE) continue;
            totalTime = Math.max(totalTime, res[i]);
            computerCount++;
        }

    }

    static class Pair implements Comparable<Pair>{
        int computNum;
        int time;

        public Pair(int computNum, int time) {
            this.computNum = computNum;
            this.time = time;
        }


        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.time, o.time);
        }
    }
}
