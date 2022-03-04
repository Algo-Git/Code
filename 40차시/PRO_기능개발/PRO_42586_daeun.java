import java.util.ArrayList;

public class PRO_42586_기능개발 {
    public static void main(String[] args) {
        System.out.print(Solution.solution(new int[]{93, 30, 55}, new int[]{1, 30, 5}));
    }

    static class Solution {
        public static int[] solution(int[] progresses, int[] speeds) {

            int len = speeds.length;

            ArrayList<Data> list = new ArrayList<>();

            int[] days = new int[len];
            for (int i = 0; i < len; i++) {
                if ((100 - progresses[i]) % speeds[i] > 0) // 나머지가 있으면 1일 추가
                    days[i] = (100 - progresses[i]) / speeds[i] + 1;
                else days[i] = (100 - progresses[i]) / speeds[i];   // 나머지가 0이면 몫이 작업날짜
            }

            list.add(new Data(days[0], 1));

            for (int i = 1; i < len; i++) {
                int size = list.size();
                if (list.get(size - 1).day >= days[i]) { // 뒤의 day가 더 작거나 같다면 리스트 마지막값 + 1
                    list.get(size - 1).cnt++;
                }
                else list.add(new Data(days[i], 1));   // 뒤의 day가 더 크면 리스트에 새로운 day 추가
            }

            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i).cnt;
            }

            return answer;
        }
    }

    static class Data{
        int day, cnt;

        public Data(int day, int cnt) {
            this.day = day;
            this.cnt = cnt;
        }
    }
}