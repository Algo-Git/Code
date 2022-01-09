import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [골드4] DSLR 
// 백트래킹 + BFS로 풀어봤다.
public class BOJ_9019_yejinKim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T;test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            String ans = "";

            boolean[] visited = new boolean[10000];
            PriorityQueue<Data> pq = new PriorityQueue<>();
            pq.offer(new Data(A,0 ,""));
            while(!pq.isEmpty()){
                Data data = pq.poll();
//                System.out.println(data.toString());
                int d = mD(data.n);
                int s = mS(data.n);
                int l = mL(data.n);
                int r = mR(data.n);

                if(!visited[d]){
                    visited[d] = true;
                    ans = data.s+"D";
                    if(d == B){
                        break;
                    }
                    pq.offer(new Data(d,data.cnt+1, ans));
                }
                if(!visited[s]){
                    visited[s] = true;
                    ans = data.s+"S";
                    if(s == B){
                        break;
                    }
                    pq.offer(new Data(s,data.cnt+1, ans));
                }
                if(!visited[l]){
                    visited[l] = true;
                    ans = data.s+"L";
                    if(l == B){
                        break;
                    }
                    pq.offer(new Data(l,data.cnt+1, ans));
                }
                if(!visited[r]){
                    visited[r] = true;
                    ans = data.s+"R";
                    if(r == B){
                        break;
                    }
                    pq.offer(new Data(r,data.cnt+1, ans));
                }
            }
            System.out.println(ans);

        }
    }

    private static int mD(int n){
        return 2*n % 10000;
    }
    private static int mS(int n){
        return n==0?9999:n-1;
    }
    private static int mL(int n){
        // 왼편으로
        int k = n/1000;
        // 10곱하고 10000의 모듈러
        return (n*10)%10000+k;
    }
    private static int mR(int n){
        int k = n%10;
        return k*1000+n/10;
    }

    private static class Data implements Comparable<Data>{
        int n;
        int cnt; // 몇번 연산했는지
        String s;

        public Data(int n, int cnt, String s) {
            this.n = n;
            this.cnt = cnt;
            this.s = s;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "n=" + n +
                    ", cnt=" + cnt +
                    ", s=" + s +
                    '}';
        }

        @Override
        public int compareTo(Data o) {
            return this.cnt-o.cnt;
        }
    }
}
