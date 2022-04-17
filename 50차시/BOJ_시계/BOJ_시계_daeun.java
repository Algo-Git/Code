import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_8989_시계 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String[] s = br.readLine().split(" ");
            Data[] datas = new Data[5];
            for (int i = 0; i < 5; i++) {
                double hour = Integer.parseInt(s[i].substring(0, 2)); // 13시부터 있으므로 12로 나눈 나머지
                double min = Integer.parseInt(s[i].substring(3, 5));
                double angle1 = Math.abs(((hour % 12) * 30) + (min * 0.5) - (min * 6)); // 시(시*30도 + 분*0.5도) - 분(분*6도)
                double angle2 = Math.abs(360 - angle1);
                double minAngle = Math.min(angle1, angle2); // 두 각도중 작은 값
                datas[i] = new Data(s[i], hour, min, minAngle);
            }

            Arrays.sort(datas);

            sb.append(datas[2].s).append("\n");
        }

        System.out.print(sb);
    }

    static class Data implements Comparable<Data> {
        String s;
        double hour, min, angle;

        public Data(String s, double hour, double min, double angle) {
            this.s = s;
            this.hour = hour;
            this.min = min;
            this.angle = angle;
        }

        @Override
        public int compareTo(Data o) {
            if (this.angle == o.angle) {  // 각도가 같으면 시 기준 정렬
                if (this.hour == o.hour) // 시가 같으면 분 기준 정렬
                    return Double.compare(this.min, o.min);
                return Double.compare(this.hour, o.hour);
            }
            return Double.compare(this.angle, o.angle); // 각도 기준 정렬
        }
    }
}