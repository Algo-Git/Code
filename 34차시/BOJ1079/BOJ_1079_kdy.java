import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1079 {// BOJ 1079. 마피아

    static int N, eunjin, night;
    static int[] val;
    static int[][] R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());// 참가자 수
        val = new int[N];// 유죄 지수
        R = new int[N][N];// R 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {// 유죄 지수 입력받기
            val[i] = Integer.parseInt(st.nextToken());
        }
        // R 배열 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        eunjin = Integer.parseInt(br.readLine());// 은진이의 참가자 번호
        night = 0;// 밤
        mafia(0, new boolean[N], N);// 마피아 게임하기
        System.out.println(night);// 은진이가 최선의 선택을 했을 때 지난 밤의 수
    }

    private static void mafia(int n, boolean[] die, int cnt) {// 마피아 게임(밤 수, 죽은 사람 체크배열, 남은 참가자 수)
        if (night == N >> 1)// 이미 최대 횟수가 정답인 경우
            return;// 종료
        if (cnt == 1 || die[eunjin]) {// 은진이 혼자 남거나 은진이가 죽음 => 게임 끝
            night = Math.max(night, n);// 밤의 수 저장
            return;// 종료
        }
        if ((cnt & 1) == 0) {// 참가자가 짝수명 남은 경우 => 밤
            for (int i = 0; i < N; i++) {// 모두 죽여보기
                if (die[i] || i == eunjin) continue;// 이미 죽은 사람이거나 은진이는 제외
                for (int j = 0; j < N; j++) {// 유죄 지수 변경
                    val[j] += R[i][j];
                }
                die[i] = true;// 죽음 표시
                mafia(n + 1, die, cnt - 1);// 다음 턴 진행
                for (int j = 0; j < N; j++) {// 유죄 지수 돌려놓기
                    val[j] -= R[i][j];
                }
                die[i] = false;// 죽음 표시 취소
            }
        } else {// 남은 참가자가 홀수 명 남은 경우 => 낮
            int p = -100;// 유죄지수가 가장 높은 참가자
            for (int i = 0; i < N; i++) {// 유죄 지수가 가장 높은 참가자 찾기
                if (die[i]) continue;// 죽은 사람은 제외
                if (p < 0 || val[p] < val[i])// 첫번째 사람이거나 유죄 지수가 가장 높으면
                    p = i;// 참가자 번호 저장
            }
            die[p] = true;// 죽음 표시
            mafia(n, die, cnt - 1);// 다음 턴 진행
            die[p] = false;// 죽음 표시 취소
        }
    }
}
