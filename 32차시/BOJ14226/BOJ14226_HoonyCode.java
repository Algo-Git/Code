import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14226 {

    /**
     * 2022-01-24 by 김영훈
     *
     * 문제이름 : 이모티콘
     * 설명 : 화면에 있는 이모티콘 복사 클립보드 저장
     * 클립보드에 있는 모든 이모티콘 화면에 붙여넣기
     * 화면에 있는 이모티콘 중 하나를 삭제한다.
     */
    static int[] dp = new int[2000];
    static int S;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();

        Arrays.fill(dp, Integer.MAX_VALUE);
        bfs();
    }

    private static void bfs() {
        Queue<Pair> que = new LinkedList<>();
        que.offer(new Pair(1, 0, 0));

        Pair cur;
        while (!que.isEmpty()){
            cur = que.poll();

            if (cur.index == S){
                System.out.println(cur.cnt);
                return;
            }

            int cnt = cur.cnt + 1;

            // 삭제 부분
            if (cur.index > 0){
                if(dp[cur.index-1] >= cnt){
                    dp[cur.index-1] = cnt;
                    que.offer(new Pair(cur.index-1, cur.copy, cnt));
                }
            }


            // 붙여넣기 부분
            int copy = cur.index + cur.copy;
            if (copy < 2000 && cur.copy != 0){
                if (dp[copy] >= cnt){
                    dp[copy] = cnt;
                    que.offer(new Pair(copy, cur.copy, cnt));
                }
            }


            // 저장 부분
            if (cur.index != cur.copy){
                que.offer(new Pair(cur.index, cur.index, cnt));
            }

        }

    }

    static class Pair{
        int index;
        int copy; // 복사한 양
        int cnt; //

        public Pair(int index, int copy, int cnt) {
            this.index = index;
            this.copy = copy;
            this.cnt = cnt;
        }
    }
}
