import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ15997 {

    static Map<String, Integer> Team = new HashMap<>();
    static int score[] = new int[4];
    static double probability[] = new double[4];
    static Game[] games = new Game[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");

        for (int i = 0; i < 4; i++) {
            //팀이름에 따른 팀번호를 저장
            Team.put(in[i], i);
        }

        //경기를 저장한다.
        for (int i = 0; i < 6; i++) {
            in = br.readLine().split(" ");
            games[i] = new Game(in[0], in[1], Double.parseDouble(in[2]), Double.parseDouble(in[3]), Double.parseDouble(in[4]));
        }


        dfs(0, 1.0);

        for (int i = 0 ; i < 4; i++){
            System.out.printf("%.10f\n", probability[i]);
        }


    }

    private static void dfs(int dept, double prob) { // 깊이와 확률을 나타냄


        if (dept == 6) { //기저조건
            if (prob == 0) { // 가지치기 조건
                return;
            }
            TeamScore[] teamScores = new TeamScore[4];
            for (int i = 0; i < 4; i++) {
                teamScores[i] = new TeamScore(i, score[i]);
            }

            //점수 순으로 정렬
            Arrays.sort(teamScores, (o1, o2) -> Integer.compare(o1.score, o2.score));

            if (teamScores[2].score != teamScores[1].score) { // 상위 2개가 존재할 떄
                probability[teamScores[2].teamId] += prob;
                probability[teamScores[3].teamId] += prob;
            } else if (teamScores[0].score == teamScores[3].score) { //4개팀이 동률일 때
                probability[teamScores[0].teamId] += prob / 2;
                probability[teamScores[1].teamId] += prob / 2;
                probability[teamScores[2].teamId] += prob / 2;
                probability[teamScores[3].teamId] += prob / 2;
            } else if (teamScores[1].score == teamScores[3].score) { //상위 3개의 팀이 동률일 때
                probability[teamScores[1].teamId] += prob * 2 / 3;
                probability[teamScores[2].teamId] += prob * 2 / 3;
                probability[teamScores[3].teamId] += prob * 2 / 3;
            } else if (teamScores[0].score == teamScores[2].score) { //하위 3개의 팀이 동률일 때
                probability[teamScores[0].teamId] += prob / 3;
                probability[teamScores[1].teamId] += prob / 3;
                probability[teamScores[2].teamId] += prob / 3;
                probability[teamScores[3].teamId] += prob;
            } else{
                probability[teamScores[1].teamId] += prob / 2;
                probability[teamScores[2].teamId] += prob / 2;
                probability[teamScores[3].teamId] += prob;
            }

            return;
        }

        Game temp = games[dept];

        //A가 이긴경우
        score[Team.get(temp.A)] += 3;
        dfs(dept + 1, prob * temp.W);
        score[Team.get(temp.A)] -= 3;

        //A B 가 비긴경우
        score[Team.get(temp.A)] += 1;
        score[Team.get(temp.B)] += 1;
        dfs(dept + 1, prob * temp.D);
        score[Team.get(temp.A)] -= 1;
        score[Team.get(temp.B)] -= 1;

        //B 가 이긴경우
        score[Team.get(temp.B)] += 3;
        dfs(dept + 1, prob * temp.L);
        score[Team.get(temp.B)] -= 3;
    }

    static class TeamScore {
        int teamId;
        int score;

        public TeamScore(int teamId, int score) {
            this.teamId = teamId;
            this.score = score;
        }
    }


    static class Game {
        String A, B;
        double W, D, L;

        public Game(String a, String b, double w, double d, double l) {
            A = a;
            B = b;
            W = w;
            D = d;
            L = l;
        }
    }
}
