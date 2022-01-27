import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N19590 {// BOJ 19590. 비드맨

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());// 비드맨이 가지고 있는 구슬 종류
        if (N == 1) {// 1가지면 하나도 못 없앰
            System.out.println(Integer.parseInt(br.readLine()));// 구슬 수 출력
        } else if (N == 2) {// 2가지인 경우
            // 두 종류 구슬 수의 차
            System.out.println(Math.abs(Integer.parseInt(br.readLine()) - Integer.parseInt(br.readLine())));
        } else {// 3가지 이상인 경우
            long sum = 0;// 구슬의 총 갯수
            long max = 0;// 가장 갯수가 많은 구슬의 수
            for (int i = 0; i < N; i++) {// 구슬 수 입력받기
                long bead = Long.parseLong(br.readLine());// 구슬 수
                sum += bead;// 구슬 수 합하기
                max = Math.max(max, bead);// 최대 구슬 수 구하기
            }
            if (sum - max <= max) {// 갯수가 제일 많은 구슬 수가 나머지의 합보다 큰 경우
                System.out.println(max - (sum - max));// 제일 수가 많은 구슬에서 나머지를 뺀 갯수
            } else {// 갯수가 제일 많은 구슬이 나머지 구슬의 합보다 적은 경우
                System.out.println(sum % 2);// 합이 홀: 1, 짝: 0 출력
            }
        }
    }
}
