package BOJ;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_세훈이의선물가게_HoonyCode {

    static int A, B, N;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //손님 어떤 구매 할지 선택 x
        // 세훈이의 취향으로 랜덤하게 준비된 설물 중 몇개 구매할것인지 파란색, 빨간색 포장만 받을 수 있음

        // 파란생 상민
        // 빨간색 지수
        // 상민이 A초
        // 지수 B초
        // 같은 시간이면 상미민이가 먼저 지수 늦게
        //서로 같은 시간에 주문한 손님은 없다.

        String[] in = br.readLine().split(" ");
        A = Integer.parseInt(in[0]);
        B = Integer.parseInt(in[1]);
        N = Integer.parseInt(in[2]);

        int Atime = 0;
        int Btime = 0;

        List<Data> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            int t = Integer.parseInt(in[0]);
            char color = in[1].charAt(0);
            int cnt = Integer.parseInt(in[2]);

            if (color == 'B') {
                Atime = Math.max(Atime, t);

                for (int j = 0; j < cnt; j++) {
                    list.add(new Data(Atime, color));
                    Atime += A;
                }
            } else {
                Btime = Math.max(Btime, t);

                for (int j = 0; j < cnt; j++) {
                    list.add(new Data(Btime, color));
                    Btime += B;
                }
            }
        }

        Collections.sort(list, (o1, o2) -> {
            if (o1.time == o2.time) {
                return Integer.compare(o1.color, o2.color);
            }
            return Integer.compare(o1.time, o2.time);
        });


        StringBuilder sb = new StringBuilder();

        List<Integer> AList = new ArrayList<>();
        List<Integer> BList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Data data = list.get(i);
            if (data.color == 'R') {
                BList.add(i + 1);
            } else {
                AList.add(i + 1);
            }
        }

        sb.append(AList.size()).append('\n');
        for (int i = 0; i < AList.size(); i++) {
            sb.append(AList.get(i)).append(' ');
        }
        sb.append('\n');

        sb.append(BList.size()).append('\n');
        for (int i = 0; i < BList.size(); i++) {
            sb.append(BList.get(i)).append(' ');
        }

        System.out.println(sb.toString());
    }

    static class Data {
        int time;
        char color;

        public Data(int time, char color) {
            this.time = time;
            this.color = color;
        }
    }
}
