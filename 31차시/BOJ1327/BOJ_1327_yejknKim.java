import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// [실버1] 소트 게임
// N과 K가
public class BOJ_1327_yejknKim {
    static int K;
    static int N;
    static int[] orders;
    static String want;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        orders = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            orders[i] = Integer.parseInt(st.nextToken());
        }

        int[] temp = orders.clone();
        Arrays.sort(temp);

        StringBuilder wantSb = new StringBuilder();

        StringBuilder origin = new StringBuilder();
        for(int i = 0; i<N; i++){
            wantSb.append(temp[i]);
            origin.append(orders[i]);
        }

        want = wantSb.toString(); // 원하는 오름차순

        if (origin.toString().equals(want)){
            System.out.println(0);
        }else{
            bfs(origin.toString());
            System.out.println(ans);
        }
    }

    private static void bfs(String origin){
        Queue<Data> queue = new LinkedList<>();
        queue.add(new Data(origin,0));

        Set<String> set = new HashSet<>();

        outer: while(true){
            Data data = queue.poll();
            if(data == null) {
                ans = -1;
                break;
            }
            
            for(int i = 0; i<=N-K; i++){
                StringBuilder makeSb = new StringBuilder(data.string);
                StringBuilder cut = new StringBuilder(makeSb.substring(i,i+K));
                cut.reverse();
                makeSb.replace(i,i+K,cut.toString());

                String make = makeSb.toString();

                if(make.equals(want)){
                    ans = data.cnt+1;
                    break outer;
                } else{
                    if(set.contains(make)){
                        continue;
                    }else{
                        set.add(make);
                        queue.offer(new Data(make, data.cnt+1));
                    }
                }
            }
        }
    }

    private static class Data{
        String string;
        int cnt;

        Data(String string, int cnt){
            this.string = string;
            this.cnt = cnt;
        }

    }
}
