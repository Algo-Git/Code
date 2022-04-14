package PRO;

import java.util.*;

public class PRO_방의개수_HoonyCode {

    class Solution {

        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

        public int solution(int[] arrows) {
            int answer = 0;

            Map<Coordinate, List<Coordinate>> map = new HashMap<>();


            int x = 0, y = 0;

            List<Coordinate> a = new ArrayList<>();
            a.add(new Coordinate(0,0));

            map.put(new Coordinate(x,y), new ArrayList<>());

            int nx, ny;
            for (int arrow : arrows) {
                for (int i = 0; i < 2; i++) {
                    nx = x + dx[arrow];
                    ny = y + dy[arrow];

                    Coordinate next = new Coordinate(nx, ny);
                    Coordinate now = new Coordinate(x,y);

                    //next가 있으면
                    if (map.containsKey(next)) {
                        //주소값을 가져오고요
                        List<Coordinate> nextList = map.get(next);

                        //주소값에 now가 없다면
                        if (!nextList.contains(now)) {
                            answer++; // 답 ++
                            //시작점 -> 가는점
                            nextList.add(now);
                            //가는점 -> 시작점
                            map.get(now).add(next);
                        }

                    } else {//next가 없으면
                        map.put(next, makeList(now));
                        map.get(now).add(next);
                    }

                    x = nx;
                    y = ny;
                }
            }


            return answer;
        }

        private List<Coordinate> makeList(Coordinate coordinate) {
            List<Coordinate> result = new ArrayList<>();
            result.add(coordinate);

            return result;
        }
    }

    class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
