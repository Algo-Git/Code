import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N16120 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'P') {
                cnt += 1;
            } else {
                if (cnt < 2 || str.length() - i < 2) {
                    cnt = -100;
                    break;
                }
                if (str.charAt(i + 1) == 'P') {
                    cnt -= 1;
                    i += 1;
                }
            }
        }
        if (cnt != 1) {
            System.out.println("NP");
        } else {
            System.out.println("PPAP");
        }
    }
}
