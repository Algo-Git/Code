import java.util.*;

public class PRO_42579_베스트앨범 {
    public static void main(String[] args) {
        System.out.print(Solution.solution(new String[]{"classic", "pop", "classic", "classic", "pop"},
                new int[]{500, 600, 150, 800, 2500}));
    }

    static class Solution {
        static class Data{
            String genre;   // 장르
            int total, play, idx; // 장르의 재생수, 곡의 재생수, 인덱스번호

            public Data(String genre, int total, int play, int idx) {
                this.genre = genre;
                this.total = total;
                this.play = play;
                this.idx = idx;
            }
        }

        public static int[] solution(String[] genres, int[] plays) {
            // 장르별 재생수 계산
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < genres.length; i++){
                if(!map.containsKey(genres[i])){
                    map.put(genres[i], plays[i]);
                }else{
                    map.put(genres[i], map.get(genres[i]) + plays[i]);
                }
            }

            // 리스트에 (장르 이름, 장르의 재생수, 곡의 재생수, 인덱스번호) 추가
            ArrayList<Data> list = new ArrayList<>();
            for (int i = 0; i < genres.length; i++){
                list.add(new Data(genres[i], map.get(genres[i]), plays[i], i));
            }

            // 장르의 재생수 높은순, 곡의 재생수 높은순, 번호 낮은순으로 정렬
            Collections.sort(list, new Comparator<Data>() {
                @Override
                public int compare(Data o1, Data o2) {
                    if(o1.total == o2.total){
                        if(o1.play == o2.play)
                            return o1.idx - o2.idx; // 재생수 같다면 번호 오름차순
                        return -(o1.play - o2.play);    // 장르가 같다면 재생수 내림차순
                    }
                    return -(o1.total - o2.total); // 장르가 다르다면 장르의 재생수 내림차순
                }
            });

            // 장르별 2개씩 골라 결과 구하기
            ArrayList<Integer> res = new ArrayList<>();

            String here = list.get(0).genre;
            int cnt = 0;
            for (int i = 0; i < list.size(); i++){
                if(here.equals(list.get(i).genre)){ // 장르가 같다면
                   if(cnt < 2){
                       res.add(list.get(i).idx);
                       cnt++;
                   }
                }else{  // 장르가 다르다면
                    here = list.get(i).genre;
                    res.add(list.get(i).idx);
                    cnt = 1;
                }
            }

            int[] answer = new int[res.size()];
            for (int i = 0; i < answer.length; i++){
                answer[i] = res.get(i);
            }

            return answer;
        }
    }
}