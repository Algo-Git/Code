import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_8911_거북이 {

    static int d, x, y;
    static int minX, minY, maxX, maxY;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());    // 테케수
        for (int t = 0; t < T; t++) {
            String s = br.readLine();

            // 시작 위치, 방향 초기화
            d = 0;  // 북쪽에서 시작
            x = 500;
            y = 500;

            minX = x;
            maxX = x;
            minY = y;
            maxY = y;

            for (int i = 0; i < s.length(); i++) {
                move(s.charAt(i));
            }

            int area = 0;   // 답
            int lenX = maxX - minX;
            int lenY = maxY - minY;
            if (lenX != 0 && lenY != 0) area = lenX * lenY;  // 선분이 아닐때만 넓이 구하기

            sb.append(area).append("\n");
        }

        System.out.print(sb);
    }

    static int[] dx = {0, 1, 0, -1};    // 상우하좌
    static int[] dy = {1, 0, -1, 0};

    static void move(char c) {
        int nx, ny;
        if (c == 'F' || c == 'B') {
            if (c == 'F') { // 한 눈금 앞으로
                nx = x + dx[d];
                ny = y + dy[d];
            } else {  // 한 눈금 뒤로
                nx = x + (dx[d] * -1);    // 방향 반대로 하기위해 -1 곱하기
                ny = y + (dy[d] * -1);
            }
            // 현재 위치 업데이트
            x = nx;
            y = ny;
            // 최소 최대 X, Y 업데이트
            minX = Math.min(minX, nx);
            maxX = Math.max(maxX, nx);
            minY = Math.min(minY, ny);
            maxY = Math.max(maxY, ny);
        } else if (c == 'L') {   // 왼쪽으로 90도 회전
            if (d == 0) d = 3;
            else d--;
        } else {  // 오른쪽으로 90도 회전
            if (d == 3) d = 0;
            else d++;
        }
    }
}