import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1327 {

    /** 2022-01-22 by 김영훈
    *
    * 문제이름 : 소트게임
    * 설명 : 1 ~ N 까지 정수로 이루어진 N 자리 순열을 이용한다.
     * K가 주어진다. -> 어떤 수를 뒤지으면 그 수부터 오른쪽으로 K개 수를 뒤집어야 한다.
    */

    static int N, K;
    static Map<String, Integer> map = new HashMap<>();
    static int answer = -1;
    static String resultNum;
    static String inputNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        // 입력 받음
        inputNum = br.readLine().replaceAll(" ", "");

        resultNum = "";
        for (int i = 1 ; i <= N ; i++){
            resultNum += i;
        }

        // 처음은 0임
        map.put(inputNum, 0);

        bfs();

        StringBuilder sb = new StringBuilder();


        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Pair> que = new LinkedList<>();
        que.offer(new Pair(inputNum, 0));

        Pair cur;
        char[] str;
        char[] str2;

        while (!que.isEmpty()){
            cur = que.poll();

            if (map.get(resultNum) != null){
                answer = map.get(resultNum);
                break;
            }

            String res = "";
            for (int i = 0 ; i <= N-K; i++){
                str = cur.num.toCharArray();
                str2 = cur.num.toCharArray();
                for (int j = 0 ; j < K ; j++){
                    str[i + j] = str2[i + K - j - 1];
                }

                res = "";
                for (int k = 0 ; k < N ; k++){
                    res += str[k];
                }


                if (map.get(res) != null) continue;
                map.put(res, cur.cnt+1);
                que.offer(new Pair(res, cur.cnt + 1));
            }



        }

    }

    static class Pair{
        String num;
        int cnt;

        public Pair(String num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

}
