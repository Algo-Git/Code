package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_시계_HoonyCode {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();


        for (int tc = 0; tc < T; tc++) {
            String[] in = br.readLine().split(" ");
            Data[] data = new Data[5];
            for (int i = 0; i < 5; i++) {
                String[] sp = in[i].split(":");
                data[i] = new Data(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]));
            }

            Arrays.sort(data);

            sb.append(String.format("%02d:%02d", data[2].time, data[2].min)).append('\n');
        }

        System.out.println(sb.toString());
    }

    static class Data implements Comparable<Data> {
        int time;
        int min;
        double angle;

        public Data(int time, int min) {
            this.time = time;
            this.min = min;
            solveAngle(time, min);
        }

        public void solveAngle(int time, int min) {
            // 한칸은 6도
            // 360움직이면 -> 30 간다
            // 1분에 시침은 0.5도
            if (time >= 12) time -= 12;
            Double timeAngle = time * 30 + min * 0.5;
            Double minAngle = min * 6.0;
            Double result;

            if (timeAngle > minAngle) {
                result = timeAngle - minAngle;
            } else {
                result = minAngle - timeAngle;
            }

            this.angle = Math.min(360 - (double) result, result);
        }


        @Override
        public int compareTo(Data o) {
            int val = time * 100 + min;
            int oVal = o.time * 100 + min;

            if (this.angle == o.angle) {
                return Integer.compare(val, oVal);
            }

            return Double.compare(angle, o.angle);
        }
    }
}
