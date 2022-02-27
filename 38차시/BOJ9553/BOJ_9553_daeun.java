import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9553_양궁 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());    // 테케 수

        for (int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());    // 타겟 수
            int[][] Point = new int[N][4];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                Point[i][0] = Integer.parseInt(st.nextToken()); // 타겟 첫번째 x
                Point[i][1] = Integer.parseInt(st.nextToken()); // 타겟 첫번째 y
                Point[i][2] = Integer.parseInt(st.nextToken()); // 타겟 두번째 x
                Point[i][3] = Integer.parseInt(st.nextToken()); // 타겟 두번째 y
            }

            // 타겟의 두 점 사이 각도를 구하고, 360으로 나누면 확률이 된다.
            // 모든 타겟의 확률을 더하면 기댓값이 된다.
            double sum = 0;
            for (int i = 0; i < N; i++){
                // 타겟의 첫번째 x, y와 원점과의 각도 구하기
                double radian = Math.atan2(Point[i][1], Point[i][0]);
                double degree = radian * 180 / Math.PI;

                // 타겟의 두번째 x, y와 원점과의 각도 구하기
                double radian2 = Math.atan2(Point[i][3], Point[i][2]);
                double degree2 = radian2 * 180 / Math.PI;

                // 타겟의 두 점 사이 각도 구하기 (앞에서 구했던 두 각도의 차이)
                degree = Math.abs(degree - degree2);
                // 가장 작은 각도를 구하기 위해 180을 넘어가는 각도는 360에서 빼줌
                if(degree > 180) degree = 360 - degree;
                sum += degree / 360;
            }
            System.out.printf("%.5f\n", sum);   // 소수점 5번째 자리까지 출력
        }
    }
}