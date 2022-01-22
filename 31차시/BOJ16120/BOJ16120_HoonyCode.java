import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16120 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //PPAP
        char[] in = br.readLine().toCharArray();
        boolean flag = true;


        int count = 0;
        int L = in.length;


        for (int i = 0 ; i < L; i ++){
            if (in[i] == 'P'){
                count++;
            }else if(in[i] == 'A'){
                ++i;
                if (i < L && in[i] == 'P'){
                    count--;
                    if (count < 1) break;
                }else{
                    flag = false;
                    break;
                }
            }
        }

        if (count == 1 && flag)
            System.out.println("PPAP");
        else
            System.out.println("NP");

    }
}
