import java.util.Arrays;
import java.util.ArrayList;

public class pro_섬연결하기_yejinKim {
    public static void main(String[] args) {

        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        int n = 4;
        System.out.println(solution(n,costs));

    }

    private static int solution(int n, int[][] costs) {
        int[] Dist = new int[n];
        for(int i= 1; i<n; i++){
            Dist[i] = Integer.MAX_VALUE;
        }

        ArrayList<Data>[] adjList = new ArrayList[n];
        for(int i = 0; i<n; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i<costs.length; i++){
            adjList[costs[i][0]].add(new Data(costs[i][1], costs[i][2]));
            adjList[costs[i][1]].add(new Data(costs[i][0], costs[i][2]));
        }

        int answer = 0;
        boolean[] visited = new boolean[n];

        int cnt = 0;
        while(true){
            int cur = -1;
            int min = Integer.MAX_VALUE;
            for(int i = 0; i<n; i++){
                if(!visited[i] && min>Dist[i]){
                    cur = i;
                    min = Dist[i];
                }
            }

            answer += min;
            cnt++;
            visited[cur] = true;

            if(cnt == n){
                break;
            }

            for (int i = 0; i < adjList[cur].size(); i++) {
                Data data = adjList[cur].get(i);
                if (!visited[data.to] && Dist[data.to] > data.weight) {
                    Dist[data.to] = data.weight;
                }
            }
        }
        return answer;
    }

    private static class Data implements Comparable<Data> {
        int to;
        int weight;

        Data(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Data o) {
            return this.weight-o.weight;
        }
    }
}
