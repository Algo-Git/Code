import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//행복유치원
//그리디라는 것을 모르고 풀었을때 쉽게 접근할 수 있었는게 dfs로 나누는 거였다. => 시간초과 발생
//그 이후 했던것은 dp로 문제를 풀려고했지만 부분문제를 찾지 못했다.
//마지막으로 생각해내었던것이 그리디였다. => 그리디인것을 찾는 연습?을 해야한다.

public class BOJ13164_HoonyCode_행복유치원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long res = 0; // 결과값
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        input = br.readLine().split(" ");
        int[] len = new int[N];

        int subtract_len;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            len[i] = Integer.parseInt(input[i]);
            if (i > 0) {
                subtract_len = len[i] - len[i-1];
                list.add(subtract_len);
                res += subtract_len;
            }
        }
        Collections.sort(list, Collections.reverseOrder());

        for (int i = 0 ; i < K-1 ; i++){
            res -= list.get(i);
        }

        System.out.println(res);

    }
}
