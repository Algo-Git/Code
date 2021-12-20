import java.util.*;
import java.io.*;

// dfs 말고는 생각이 안남
// 백트래킹 필수! => 면적이 0이면, 중간단계에서 면적이 작으면 continue
public class Main
{
    static int K;
    static int Min;
    static ArrayList<Data>[] color;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new ArrayList[K+1];
        for(int i = 1; i<=K; i++){
            color[i] = new ArrayList<Data>();
        }

        // color별 인덱스에 데이터를 저장한다.
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            color[c].add(new Data(x,y));
        }
        Min = 5000000; // 4000000가 원래 최대라고 생각함
        for(int i = 0; i<color[1].size(); i++){
            dfs(color[1].get(i).x, color[1].get(i).y, color[1].get(i).x, color[1].get(i).y, 2);
        }
        
        System.out.print(Min);
    }

    private static void dfs(int minX, int minY, int maxX, int maxY, int cnt){
        // 기저조건
        int S = (maxX-minX) * (maxY-minY);
        
        // 가지치기
        if(S>Min){
            return;
        }

        // 
        if(cnt == K+1){
            Min = Math.min(S, Min);
            if(Min == 0){
                System.out.print(Min);
                System.exit(0);
            }
            return;
        }

        for(Data data: color[cnt]){
            dfs(Math.min(minX,data.x), Math.min(minY, data.y), Math.max(maxX, data.x), Math.max(maxY, data.y), cnt+1);
        }

    }

    private static class Data{
        int x;
        int y;

        Data(int x , int y){
            this.x = x;
            this.y = y;
        }
    }
}
