import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [실버2] 가장 긴 감소하는 부분 수열
public class baekjoon_11722_yejinKim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N+1];
        int Max = 0;
        int[] LSI = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = N; i>0; i--){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i<=N; i++){
            int localMax = 0;
            for(int j = i-1; j>=0; j--){
                if(nums[j]<nums[i] && localMax<LSI[j]){
                    localMax = LSI[j];
                }
            }
            LSI[i] = localMax + 1;
            if(LSI[i]>Max){
                Max = LSI[i];
            }
        }
        System.out.println(Max);
    }
}
