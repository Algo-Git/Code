import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ6603 {

    static StringBuilder sb = new StringBuilder();
    static int[] arr = new int[14];
    static String[] in;
    static int end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true){
            in = br.readLine().split(" ");
            // 종료문
            if (Integer.parseInt(in[0]) == 0) break;
            //6개 고르는 방식
            // 수가 겹치면 안된다.
            end = Integer.parseInt(in[0]);
            dfs(0, 1);

            sb.append('\n');
        }

        System.out.print(sb.toString());

    }

    private static void dfs(int index,int start) {

        if (index == 6){
            for (int i = 0 ; i  < 6 ; i++){
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }


        for (int i = start; i <= end; i++){
            arr[index] = Integer.parseInt(in[i]);
            dfs(index + 1, i + 1);
        }

    }
}
