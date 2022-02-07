import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class N14220 {// BOJ 14220. 양아치 집배원

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());// 방문하는 도시 수
        StringTokenizer st = null;
        ArrayList<Data>[] list = new ArrayList[n];// 도시로 가는 거리
        int[][] dist = new int[n][2];// (이전까지 방문한 거리, 지금까지 방문한 거리) 배열
        for (int i = 0; i < n; i++) {// 리스트, 거리 초기화
            list[i] = new ArrayList<>();
            dist[i][0] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < n; i++) {// 도시까지 거리 입력받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int dis = Integer.parseInt(st.nextToken());// 도시까지 거리
                if (dis == 0) continue;// 갈 수 없는 경우 => 넘기기
                list[i].add(new Data(j, dis));// i->j 거리 저장
                dist[j][0] = Math.min(dist[j][0], dis);// j로 오는 최단거리 저장
            }
        }
        if (n == 1)// 도시가 하나일 때
            System.out.println(0);// 최단거리 0 출력
        else if (check(dist))// 최소가 되는 경로가 없는 경우
            System.out.println(-1);// -1 출력
        else {
            for (int i = 0; i < n - 2; i++) {// 현재 도시는 방문 처리, n-1개의 도로 가야 함 => 0~n-2
                for (int j = 0; j < n; j++) {// 거리 초기화
                    dist[j][1] = Integer.MAX_VALUE;
                }
                for (int j = 0; j < n; j++) {// 모든 도시 살펴보기
                    for (Data cur : list[j]) {// 도시와 연결된 도로 최단 거리 저장
                        dist[cur.city][1] = Math.min(dist[cur.city][1], dist[j][0] + cur.dis);
                    }
                }
                for (int j = 0; j < n; j++) {// 이전까지 방문한 경로로 옮기기
                    dist[j][0] = dist[j][1];
                }
            }
            int ans = Integer.MAX_VALUE;// 전체 이동이 최소가 되는 경로의 거리
            for (int i = 0; i < n; i++) {// 전체 도시 중에 최단 경로 찾기
                ans = Math.min(ans, dist[i][0]);// 최단 거리 저장
            }
            System.out.println(ans);// 최소가 되는 경로의 이동 거리 출력
        }
    }

    private static boolean check(int[][] dist) {// 최소가 되는 경로를 찾을 수 없는 경우
        for (int i = 0; i < dist.length; i++) {// 도시 살펴보기
            if (dist[i][0] == Integer.MAX_VALUE) return true;// 도시로 가는 도로가 없는 경우 X
        }
        return false;// 최단 경로 있음
    }

    static class Data {// 도로 정보
        int city, dis;// 도시 번호, 거리

        public Data(int city, int dis) {
            this.city = city;
            this.dis = dis;
        }
    }
}
