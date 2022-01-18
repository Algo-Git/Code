import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2064 {

    static int[] ip;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // IP 주소는 4개의 바이트로 구성 되어 있다.
        // 네트워크 주소와 네트워크 마스크를 구해내는 프로그램을 작성해라
        // 답이 여러개인 경우에는 가장 크기가 작은 네트워크를 구하도록 한다.

        int N = Integer.parseInt(br.readLine());
        ip = new int[N];

        String[] input;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split("[.]");
            ip[i] =  makeIp(input);
        }

        int snbnet = 0;

        Loop : for (int i = 31 ; i >= 0 ; i--){
            int bit = 1 << i;
            boolean end = false;

            for (int j = 1 ; j < N ; j++){
                if ((ip[0] & bit) != (ip[j] & bit)){
                    break Loop;
                }
            }
            snbnet += bit;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(convertIp(ip[0] & snbnet)).append('\n');
        sb.append(convertIp(snbnet));

        System.out.println(sb.toString());

    }

    private static StringBuilder convertIp(int ip) {
        StringBuilder sb = new StringBuilder();

        int result;

        for (int i = 24; i > -1; i = i - 8) {
            result = (ip >>> i) & 0xff;
            if (i == 0){
                sb.append(result);
                break;
            }
            sb.append(result).append(".");
        }

        return sb;
    }

    private static int makeIp(String[] input) {
        int ip = 0;
        for (int i = 0; i < 4; i++) {
            ip = (ip << 8);
            ip += Integer.parseInt(input[i]);
        }
        return ip;
    }
}
