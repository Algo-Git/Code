import java.util.Scanner;

public class BOJ1072 {



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double x = sc.nextDouble(); // 게임 횟수
        double y = sc.nextDouble(); // 이긴 게임


        //형택이는 앞으로의 모든 게임에서 지지 않는다.
        double per = (int)(y * 100 / x);
        if (per >= 99) {
            System.out.println(-1);
            return;
        }

        int low = 0;
        int high = 1_000_000_000;
        int mid = 0;

        while (low < high){
            mid = ((low + high)/2);
            if (per < (int)((y + mid) * 100 / (x + mid))){
                high = mid;
            }else
                low = mid + 1;
        }
        System.out.println(low);
    }
}
