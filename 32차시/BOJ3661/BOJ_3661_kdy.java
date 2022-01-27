import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class N3661 {// BOJ 3661. 생일선물

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());// 테스트케이스 수
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());// 선물 가격
            int n = Integer.parseInt(st.nextToken());// 친구 수
            st = new StringTokenizer(br.readLine());
            ArrayList<Data> list = new ArrayList<>();// 선물 가격 리스트
            int minus = 0;// 뺄 돈
            int sum = 0;// 친구 돈 합
            int[] res = new int[n];// 결과 배열
            int mod = p / n;// 몫
            for (int i = 0; i < n; i++) {// 입력 받기, 몫보다 작으면 돈 다 내기
                int max = Integer.parseInt(st.nextToken());// 최대로 낼 수 있는 돈
                sum += max;// 돈 더하기
                if (max <= mod) {// 몫보다 작거나 같으면
                    res[i] = max;// 돈 다 내기
                    minus -= max;// 낼 수 있는 돈에서 빠짐
                } else {// 몫보다 큼
                    list.add(new Data(i, max));// 리스트에 추가
                }
            }
            if (sum < p) {// 돈이 선물 가격보다 낮으면
                System.out.println("IMPOSSIBLE");// 지불할 수 없음
            } else if (sum + minus == 0) {// 이미 돈을 다 지불했으면
                for (int i = 0; i < n; i++) {// 지불한 돈 출력
                    System.out.print(res[i] + " ");
                }
                System.out.println();
            } else {// 낼 돈 계산하기
                Collections.sort(list, new Comparator<Data>() {// 리스트 정렬 => 돈 적은 순, 인덱스 뒤부터(앞사람이 많이)
                    @Override
                    public int compare(Data o1, Data o2) {
                        if (o1.n == o2.n)
                            return o2.i - o1.i;
                        else
                            return o1.n - o2.n;
                    }
                });
                p += minus;// 낸 돈은 빼기
                mod = p / list.size();// 남은 돈의 몫
                for (int i = 0; i < list.size(); i++) {
                    // 마지막 사람이면 남은 돈 다 내기
                    if (i == list.size() - 1) res[list.get(i).i] = p;
                    else {// 마지막 사람 아니면
                        res[list.get(i).i] = Math.min(mod, list.get(i).n);// 몫과 낼 수 있는 돈 중에 낮은 금액 내기
                        p -= Math.min(mod, list.get(i).n);// 낸 돈 빼기
                        mod = p / (list.size() - 1 - i);// 남은 돈의 몫
                    }
                }
                for (int i = 0; i < n; i++) {// 지불할 금액 출력
                    System.out.print(res[i] + " ");
                }
                System.out.println();
            }
        }
    }

    static class Data {// 낼 수 있는 돈 정보
        int i, n;// 순서, 돈

        public Data(int i, int n) {
            this.i = i;
            this.n = n;
        }
    }
}