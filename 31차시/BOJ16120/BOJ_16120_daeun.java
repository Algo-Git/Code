import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_16120_PPAP {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int cnt = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'P') cnt++;
            else{   // A 다음 P 이면 -1
                if(i+1 < s.length() && s.charAt(i+1) == 'P') {
                    cnt--;
                    i++;
                }else{  // A 다음 A 를 만나면 0, break
                    cnt = 0;
                    break;
                }
            }
            if(cnt < 1) break;
        }

        if(cnt == 1) System.out.print("PPAP");
        else System.out.print("NP");
    }
}