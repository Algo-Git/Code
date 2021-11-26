
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ1966 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Pair> dque = new LinkedList<>();

        int t = Integer.parseInt(br.readLine());

        String[] input;

        int N, M;

        for (int tc = 0; tc < t; tc++) { // 테스트 케이스 갯수
            input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            dque.clear(); // 큐 초기화

            // 수를 받아온다.
            input = br.readLine().split(" ");
            int[] numArr = new int[10];

            for (int i = 0; i < N; i++) {
                int val = Integer.parseInt(input[i]);
                dque.offerLast(new Pair(val, i)); // 큐에 수를 넣어줌
                numArr[val]++; // 숫자가 몇개 있는지 센다.
            }



            //카운트된 숫자가 0이면 num을 내림

            int res = 0;
            int num = 9;
            Pair cur;
            while (true) {
                while (numArr[num] == 0) {
                    num--;
                }

                cur = dque.poll();


                if(cur.val == num && cur.index == M){
                    sb.append(++res).append('\n');
                    break;
                }else if(cur.val == num){
                    numArr[num]--;
                    ++res;
                }else{
                    dque.addLast(cur);
                }
            }
        }
        System.out.print(sb.toString());
    }


    static class Pair {
        int val, index;

        public Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}