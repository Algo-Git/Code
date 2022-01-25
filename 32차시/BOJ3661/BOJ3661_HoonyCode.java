import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 2022-01-25 by 김영훈
 * <p>
 * 문제이름 : BOJ 3661
 * 설명 : 생일 선물
 */
public class BOJ3661 {

    static int p, n;
    static Pair[] pairs;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for (int T = 0; T < tc; T++) {
            String[] in = br.readLine().split(" ");
            p = Integer.parseInt(in[0]);
            n = Integer.parseInt(in[1]);

            pairs = new Pair[n];
            int sum = 0;
            in = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                pairs[i] = new Pair(i, 0, Integer.parseInt(in[i]));
                sum += pairs[i].maxPay;
            }

            // 낼 수 있는 금액 초과 할 떄.
            if (p > sum){
                sb.append("IMPOSSIBLE").append('\n');
                continue;
            }

            // 낼수 있는 총액과 같을 때
            if (p == sum){
                for (int i = 0 ; i < n; i++){
                    sb.append(pairs[i].maxPay).append(' ');
                }
                sb.append('\n');
                continue;
            }

            Arrays.sort(pairs, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    if (o1.maxPay == o2.maxPay){
                        return Integer.compare(o1.index, o2.index);
                    }
                    return Integer.compare(o2.maxPay, o1.maxPay);
                }
            });

            while (p > 0){
                for (int i = 0 ; i < n ; i++){
                    if(p == 0) break;

                    if (pairs[i].pay < pairs[i].maxPay){
                        pairs[i].pay++;
                        p--;
                    }
                }
            }


            Arrays.sort(pairs, (o1, o2) ->
                Integer.compare(o1.index, o2.index));

            for (int i = 0 ; i < n ; i++){
                sb.append(pairs[i].pay).append(" ");
            }
            sb.append('\n');

        }

        System.out.print(sb.toString());
    }

    static class Pair{
        int index;
        int pay;
        int maxPay;

        public Pair(int index, int pay, int maxPay) {
            this.index = index;
            this.pay = pay;
            this.maxPay = maxPay;
        }
    }

}
