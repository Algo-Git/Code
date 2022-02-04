import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N6603 {// BOJ 6603. 로또

    static int N;
    static int[] num, com;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());// 집합의 원소 수
        while (N != 0) {// 테스트케이스 반복
            num = new int[N];// 집합 배열
            com = new int[6];// 로또 배열
            for (int i = 0; i < N; i++) {// 집합 입력받기
                num[i] = Integer.parseInt(st.nextToken());
            }
            comp(0, 0);// 조합
            System.out.println();
            st = new StringTokenizer(br.readLine());// 다음 테스트케이스 입력받기
            N = Integer.parseInt(st.nextToken());// 테스트케이스 첫번째 수 받기
        }
    }

    private static void comp(int st, int cnt) {// 조합
        if (cnt == 6) {// 6개 뽑았으면
            for (int i = 0; i < 6; i++) {// 뽑은 수 출력
                System.out.print(com[i] + " ");
            }
            System.out.println();
            return;// 종료
        }
        for (int i = st; i < N; i++) {// 숫자 뽑기
            com[cnt] = num[i];// 뽑기
            comp(i + 1, cnt + 1);// 다음 수 뽑기
        }
    }
}
