package PROGRAMMERS;

import java.util.*;

public class PRO_베스트앨범_HoonyCode {

    class Solution {
        public int[] solution(String[] genres, int[] plays) {
            List<Integer> answer = new ArrayList<>();

            /*
            속한 노래가 많이 재생된 장르를 먼저 수록합니다.
            장르 내에서 많이 재생된 노래를 먼저 수록합니다.
            장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
             */
            //genres 노래의 장르
            //plays : 노래의 재생 횟수
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                if (map.containsKey(genres[i])) {
                    map.replace(genres[i], map.get(genres[i]) + plays[i]);
                } else
                    map.put(genres[i], plays[i]);
            }

            List<Like> list = new ArrayList<>();


            for (int i = 0 ; i < genres.length; i++)
                list.add(new Like(i, plays[i], map.get(genres[i])));

            list.sort(new Comparator<Like>() {
                @Override
                public int compare(Like o1, Like o2) {
                    if (o1.sum == o2.sum) {
                        if (o1.play == o2.play) {
                            return 0;
                        }
                        return Integer.compare(o2.play, o1.play);
                    }
                    return Integer.compare(o2.sum, o1.sum);
                }
            });

            int cnt = 0;
            int nowSum = list.get(0).sum;
            for (Like like : list){
                if (nowSum != like.sum){
                    nowSum = like.sum;
                    cnt = 0;
                }
                cnt++;
                if (cnt > 2)
                    continue;
                answer.add(like.index);
            }


            return answer.stream().mapToInt(Integer::intValue).toArray();
        }

    }

    class Like{
        int index;
        int play;
        int sum;

        public Like(int index,int play, int sum) {
            this.index = index;
            this.play = play;
            this.sum = sum;
        }
    }
}
