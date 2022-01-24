import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14226 {

    /**
     * 2022-01-24 by 김영훈
     * <p>
     * 문제이름 : 이모티콘
     * 설명 : 화면에 있는 이모티콘 복사 클립보드 저장
     * 클립보드에 있는 모든 이모티콘 화면에 붙여넣기
     * 화면에 있는 이모티콘 중 하나를 삭제한다.
     */
    static boolean[][] v = new boolean[1001][1001];
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        bfs();
    }

    private static void bfs() {
        Queue<Emoticon> que = new LinkedList<>();
        que.offer(new Emoticon(0, 1, 0));
        v[0][1] = true;

        Emoticon cur;
        while (!que.isEmpty()) {
            cur = que.poll();

            if (cur.emoticon == S) {
                System.out.println(cur.time);
                return;
            }

            //1. 이모티콘을 모두 복사해서 클립보드에 저장한다.
            que.offer(new Emoticon(cur.emoticon, cur.emoticon, cur.time + 1));

            //2. 클립보드에 있는 이모티콘을 화면에 붙여넣기 한다
            if (cur.clipboard != 0
                    && cur.clipboard + cur.emoticon <= S
                    && !v[cur.clipboard][cur.emoticon + cur.clipboard]) {
                que.offer(new Emoticon(cur.clipboard, cur.emoticon + cur.clipboard, cur.time + 1));
                v[cur.clipboard][cur.emoticon + cur.clipboard] = true;
            }

            //3. 화면에 있는 이모티콘 중 하나를 삭제한다.
            if (1 <= cur.emoticon && !v[cur.clipboard][cur.emoticon - 1]) {
                que.offer(new Emoticon(cur.clipboard, cur.emoticon - 1, cur.time + 1));
                v[cur.clipboard][cur.emoticon - 1] = true;
            }

        }
    }

    static class Emoticon {
        int clipboard; // 클립보드
        int emoticon; // 앞에 있는 이모티콘
        int time; // 시간

        public Emoticon(int clipboard, int emoticon, int time) {
            this.clipboard = clipboard;
            this.emoticon = emoticon;
            this.time = time;
        }
    }
}
