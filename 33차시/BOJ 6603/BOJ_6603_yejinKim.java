import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [실버2] 로또
public class BOJ_6603_yejinKim {
    static int[] inputs;
    static int[] nums;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nums = new int[6];

        // 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있다.
        // 첫 번째 수는 k (6 < k < 13)이고, 다음 k개 수는 집합 S에 포함되는 수이다. S의 원소는 오름차순으로 주어진다.
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0){
                break;
            }

            inputs = new int[k];
            for(int i = 0; i<k; i++){
                inputs[i] = Integer.parseInt(st.nextToken());
            }

            // 조합이다.
            comb(0,0);
            System.out.println();
        }


    }

    private static void comb(int cnt, int start) {
        if(cnt == 6){
            for(int num: nums){
                System.out.print(inputs[num] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = start; i<k; i++){
            nums[cnt] = i;
            comb(cnt+1, i+1);
        }
    }
}
