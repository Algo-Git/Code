import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_19950 {

    /**
     * 1. 조건의 거리가 막대기길이 총합보다 짧거나 같을것
     * 2. 제일긴 막대기에서 나머지막대기 길이를 합한값을 뺀값이 조건의 거리보다 짧거나 같을것
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        Coordinate start,end;
        start = new Coordinate(Integer.parseInt(in[0]), Integer.parseInt(in[1]), Integer.parseInt(in[2]));
        end = new Coordinate(Integer.parseInt(in[3]), Integer.parseInt(in[4]), Integer.parseInt(in[5]));
        int N = Integer.parseInt(br.readLine());
        double len = Math.sqrt(
                (start.x - end.x) * (start.x - end.x)
                        + (start.y - end.y) * (start.y - end.y)
                        + (start.z - end.z) * (start.z - end.z));

        List<Integer> stick = new ArrayList<>();
        in = br.readLine().split(" ");
        for (int i = 0 ; i < N ; i++) {
            stick.add(Integer.parseInt(in[i]));
        }

        Collections.sort(stick, Collections.reverseOrder());
        double stickLen = 0;
        double stickLen2 = 0;

        if (stick.get(0) > len){
            stickLen2 = stick.get(0);
            for (int i = 1 ; i < stick.size(); i++){
                stickLen2 -= stick.get(i);
            }
            System.out.println(stickLen2 <= len ? "YES" : "NO");
        }else{
            for (int st : stick){
                stickLen += st;
            }
            System.out.println(stickLen >= len ? "YES" : "NO" );
        }


    }

    static class Coordinate{
        int x;
        int y;
        int z;

        public Coordinate(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}
