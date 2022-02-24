import java.io.*;
import java.util.*;

public class BOJ_19644_HoonyCode {

    public static void main(String[] args) throws IOException {
        int L, Ml, Mk, C, zombie[], Bomb;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Ml = Integer.parseInt(st.nextToken());
        Mk = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(br.readLine());
        boolean flag = true;
        zombie = new int[L];
        boolean bomber[] = new boolean[L];
        int start = -Ml + 1;
        int end = 0;
        Bomb = Ml - 1;
        int shootDam = 0;
        int fullDam = Mk * Ml;
        while (true) {
            zombie[end] = Integer.parseInt(br.readLine());
            shootDam = fullDam - Bomb * Mk;
            if (shootDam < zombie[end]) {
                if (--C < 0) {
                    flag = false;
                    break;
                }
                Bomb++;
                bomber[end] = true;
            }
            if (start < 0 || bomber[start]) {
                Bomb--;
            }
            start++;
            if (++end == L)
                break;
        }

        System.out.println(flag ? "YES" : "NO");
    }
}
