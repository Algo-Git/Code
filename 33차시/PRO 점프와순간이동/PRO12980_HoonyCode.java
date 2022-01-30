import java.util.Scanner;

public class PRO12980 {


    public static void main(String[] args) {
        System.out.println(new PRO12980().solution(new Scanner(System.in).nextInt()));
    }

    public int solution(int n) {
        int ans = 0;

        for (int i = 0; i <= 31 ; i++){
            if ((n & (1 << i)) != 0){
                ans++;
            }
        }

        return ans;
    }
}