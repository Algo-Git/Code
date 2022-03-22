import java.util.*;

public class PRO_42884_단속카메라 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(new int[][]{{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}}));

    }

    static class Solution {
        public static int solution(int[][] routes) {
            int answer = 0;

            // 진출 지점 순으로 오름차순, 진출 지점이 같다면 진입 지점 순으로 오름차순
            Arrays.sort(routes, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[1] == o2[1])
                        return o1[0] - o2[0];
                    return o1[1] - o2[1];
                }
            });

            ArrayList<Integer> list = new ArrayList<>();
            list.add(routes[0][1]); // 첫번째 차량의 진출지점을 list에 추가

            for (int i = 1; i < routes.length; i++){
                // i번째 차량의 진입지점이 list의 마지막(진출지점)보다 크면 list에 추가
                if(list.get(list.size() - 1) < routes[i][0]){
                    list.add(routes[i][1]);
                }
            }

            answer = list.size();   // list의 개수 == 단속 카메라 개수

            return answer;
        }
    }
}