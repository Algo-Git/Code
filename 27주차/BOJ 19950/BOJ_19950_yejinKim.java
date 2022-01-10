import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [골드5] 3차원 막대기 연결하기
public class BOJ_19950_yejinKim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int z1 = Integer.parseInt(st.nextToken());
        int x2 = Math.abs(Integer.parseInt(st.nextToken())-x1);
        int y2 = Math.abs(Integer.parseInt(st.nextToken())-y1);
        int z2 = Math.abs(Integer.parseInt(st.nextToken())-z1);

        double distance = Math.sqrt(x2*x2+y2*y2+z2*z2);

        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        st = new StringTokenizer(br.readLine());

        int Max = 0;
        for(int i = 0; i<N; i++){
            int k = Integer.parseInt(st.nextToken());
            sum += k;
            if(Max<k) Max = k;
        }

        if(sum<distance) {
            System.out.println("NO");
            return;
        }

        if(Max>distance && Max-(sum-Max)>distance){
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
    }
}
