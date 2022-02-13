import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1198 {// BOJ 1198. 삼각형으로 자르기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());// 다각형의 점 수
        StringTokenizer st = null;
        int[][] xy = new int[N][2];// 다각형 점의 좌표
        for (int i = 0; i < N; i++) {// 점 입력받기
            st = new StringTokenizer(br.readLine());
            xy[i][0] = Integer.parseInt(st.nextToken());
            xy[i][1] = Integer.parseInt(st.nextToken());
        }

        double area = 0;// 점 3개로 만든 삼각형의 최대 넓이
        for (int i = 0; i < N - 2; i++) {// 점 3개 조합
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    area = Math.max(area, getArea(xy[i][0], xy[i][1], xy[j][0], xy[j][1], xy[k][0], xy[k][1]));
                }
            }
        }

        System.out.println(area);// 삼각형의 최대 넓이 출력
    }

    private static double getArea(int x1, int y1, int x2, int y2, int x3, int y3) {// 좌표로 넓이 구하기
        return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - (x2 * y1 + x3 * y2 + x1 * y3));
    }
}
