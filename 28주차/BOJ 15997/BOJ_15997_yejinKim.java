import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [골드3] 승부 예측
// 브루트 포스 + 조건
public class BOJ_15997_yejinKim {
    static int[] scores;
    static Data[] datas;
    static double[] percentages;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] name = br.readLine().split(" ");
        datas = new Data[6];
        percentages = new double[4];
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String team1name = st.nextToken();
            String team2name = st.nextToken();
            int idx1 = 0;
            for (int j = 0; j < 4; j++) {
                if (team1name.equals(name[j])) {
                    idx1 = j;
                    break;
                }
            }

            int idx2 = 0;
            for (int j = 0; j < 4; j++) {
                if (team2name.equals(name[j])) {
                    idx2 = j;
                }
            }

            double win1 = Double.parseDouble(st.nextToken());
            double draw = Double.parseDouble(st.nextToken());
            double win2 = Double.parseDouble(st.nextToken());

            datas[i] = new Data(idx1, idx2, win1, draw, win2);
        }
        scores = new int[4];
        dfs(0, 1);
        for(int i = 0; i<4; i++){
            System.out.println(percentages[i]);
        }
    }

    private static void dfs(int cnt, double percentage) {
        if (cnt == 6) {
            Item[] items = new Item[4];
            for (int i = 0; i < 4; i++) {
                items[i] = new Item(i, scores[i]);
            }
            Arrays.sort(items);

            int maxs = items[0].score;
            int equalCnt1 = 1;
            for (int i = 1; i < 4; i++) {
                if (items[i].score == maxs) equalCnt1++;
                else break;
            }

            if (equalCnt1 != 1) { // 여러명이 일등
                for (int i = 0; i < equalCnt1; i++) {
                    percentages[items[i].idx] += percentage * 2 / equalCnt1;
                }
                return;
            }

            // 한명이 일등
            percentages[items[0].idx] += percentage;

            int maxs2 = items[1].score;
            int equalCnt2 = 1;
            for (int i = 2; i < 4; i++) {
                if (items[i].score == maxs2) equalCnt2++;
                else break;
            }

            // 2등인 사람들
            for (int i = 0; i < equalCnt2; i++) {
                percentages[items[1 + i].idx] += percentage / equalCnt2;
            }
            return;
        }

        Data data = datas[cnt];
        // 1이 이긴 경우
        scores[data.idx1] += 3;
        dfs(cnt + 1, percentage * data.win1);
        scores[data.idx1] -= 3;

        // 1과 2가 비긴 경우
        scores[data.idx1]++;
        scores[data.idx2]++;
        dfs(cnt + 1, percentage * data.draw);
        scores[data.idx1]--;
        scores[data.idx2]--;

        // 2가 이긴 경우
        scores[data.idx2] += 3;
        dfs(cnt + 1, percentage * data.win2);
        scores[data.idx2] -= 3;
    }

    private static class Data {
        int idx1;
        int idx2;
        double win1;
        double draw;
        double win2;

        public Data(int idx1, int idx2, double win1, double draw, double win2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.win1 = win1;
            this.draw = draw;
            this.win2 = win2;
        }
    }

    private static class Item implements Comparable<Item> {
        int idx;
        int score;

        public Item(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }

        @Override
        public int compareTo(Item o) {
            return o.score - this.score;
        }
    }
}
