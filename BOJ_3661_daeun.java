import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3661_생일선물 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());    // 테케수
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());   // 선물의 가격
            int n = Integer.parseInt(st.nextToken());   // 선영이 친구의 수

            List<Data> list = new ArrayList<>();   // 각 사람이 낼 수 있는 금액
            List<Data> res = new ArrayList<>(); // 최종 리스트

            int total = 0;  // 친구들이 가진 총 금액

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                list.add(new Data(i, Integer.parseInt(st.nextToken())));
                res.add(new Data(i, 0));
                total += list.get(i).money;
            }

            // 선물 금액보다 친구들의 총 금액이 더 적으면 IMPOSSIBLE 출력
            if (total < p) {
                sb.append("IMPOSSIBLE\n");
                continue;
            }

            // 선물 금액과 친구들의 총 금액이 같을 경우
            if (total == p) {
                for (int i = 0; i < n; i++) {
                    sb.append(list.get(i).money).append(" ");
                }
                sb.append("\n");
                continue;
            }

            Collections.sort(list); // 오름차순 정렬

            int nbbang = p / n;    // 선물 금액의 1/n
            int nbbang_copy = nbbang;   // 남은 사람들 끼리의 n 빵
            int p_copy = p;
            int n_copy = n;
            for (int i = 0; i < n; i++) {
                Data cur = list.get(i);
                n_copy--;
                if (cur.money < nbbang_copy) {
                    res.get(cur.num).money += cur.money;
                    p_copy -= cur.money;
                    nbbang_copy = p_copy / n_copy;
                } else {
                    res.get(cur.num).money += nbbang_copy;
                    p_copy -= nbbang_copy;
                }
                if (n_copy == 0) break;
            }

            if(p_copy != 0) {
                for (int i = n - 1; i >= 0; i--) {
                    Data cur = list.get(i);
                    if (res.get(cur.num).money + 1 <= cur.money) {
                        res.get(cur.num).money++;
                        p_copy--;
                    }
                    if (p_copy == 0) break;
                    if (i == 0) i = n;
                }
            }

            for (int i = 0; i < n; i++) {
                sb.append(res.get(i).money).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static class Data implements Comparable<Data> {
        int num, money;

        public Data(int num, int money) {
            this.num = num;
            this.money = money;
        }

        @Override
        public int compareTo(Data o) {
            if (this.money == o.money)  // 같은 돈이면 인덱스 내림차순
                return Integer.compare(o.num, this.num);
            return Integer.compare(this.money, o.money);
        }
    }
}