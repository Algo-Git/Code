import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15927 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] in = br.readLine().toCharArray();
        int len = in.length;

        boolean allStringCheck = true; // 모든 str이 같은지 판단.

        char temp = in[0];
        for (int i = 0; i < len; i++) {
            if (in[i] != in[len - 1 - i]) {
                System.out.println(len);
                return;
            }
            if (temp != in[i]){
                allStringCheck = false;
            }
        }

        if (allStringCheck == true){
            System.out.println(-1);
            return;
        }else{
            System.out.println(len-1);
            return;
        }
    }

}
