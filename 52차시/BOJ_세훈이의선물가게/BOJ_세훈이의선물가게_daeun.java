import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17225_세훈이의선물가게 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());   // 상민 포장 시간
        int B = Integer.parseInt(st.nextToken());   // 지수 포장 시간
        int N = Integer.parseInt(st.nextToken());   // 손님 수

        List<Integer> list1 = new ArrayList();  // 상민 포장 번호
        List<Integer> list2 = new ArrayList();  // 지수 포장 번호

        PriorityQueue<Gift> pq = new PriorityQueue<Gift>(new Comparator<Gift>() {
            @Override
            public int compare(Gift o1, Gift o2) {
                if (o1.start_time == o2.start_time) {   // 같은 시각에 끝나면 상민이부터
                    return Integer.compare(o1.color, o2.color);
                }
                return o1.start_time - o2.start_time;   // 종료 시간 순서대로 정렬
            }
        });

        int end_time1 = -1, end_time2 = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int order_time = Integer.parseInt(st.nextToken());  // 주문 시간
            char color = st.nextToken().charAt(0);  // 포장지 색깔
            int cnt = Integer.parseInt(st.nextToken()); // 선물 개수

            if (color == 'B' && end_time1 < order_time)
                end_time1 = order_time;  // 상민, 주문시간이 더 크면 포장 끝 시간 변경
            if (color == 'R' && end_time2 < order_time)
                end_time2 = order_time;  // 지수, 주문시간이 더 크면 포장 끝 시간 변경

            for (int j = 0; j < cnt; j++) { // 선물 개수만큼 pq에 추가
                if (color == 'B') {
                    pq.offer(new Gift(end_time1, color));
                    end_time1 += A;
                } else {
                    pq.offer(new Gift(end_time2, color));
                    end_time2 += B;
                }
            }
        }

        int idx = 1;
        while (!pq.isEmpty()) {
            Gift gift = pq.poll();
            if (gift.color == 'B') {    // 상민이 리스트에 선물 번호 저장
                list1.add(idx++);
            } else {    // 지수 리스트에 선물 번호 저장
                list2.add(idx++);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list1.size()).append("\n");
        for (int temp : list1) {
            sb.append(temp).append(" ");
        }
        sb.append("\n").append(list2.size()).append("\n");
        for (int temp : list2) {
            sb.append(temp).append(" ");
        }

        System.out.print(sb);
    }

    static class Gift {
        int start_time;
        char color;

        public Gift(int start_time, char color) {
            this.start_time = start_time;
            this.color = color;
        }
    }
}