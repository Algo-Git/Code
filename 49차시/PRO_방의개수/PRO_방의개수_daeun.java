import java.util.*;

public class PRO_49190_방의개수 {
    public static void main(String[] args) {
//        System.out.print(solution(new int[]{6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0}));
//        System.out.print(solution(new int[]{6, 2, 4, 0, 5, 0, 6, 4, 2, 4, 2, 0}));
//        System.out.print(solution(new int[]{5, 2, 7, 1, 6, 3}));
        System.out.print(solution(new int[]{0, 2, 5}));
    }

    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    public static int solution(int[] arrows) {
        int answer = 0;

        Map<Point, List<Point>> map = new HashMap();
        map.put(new Point(0, 0), new ArrayList<>());

        int x = 0, y = 0;
        Point start, end;
        for (int d : arrows) {
            for(int i = 1; i <= 2; i++) {   // 대각선 교차점때문에 2칸으로 지정
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 점 객체 생성
                start = new Point(x, y);
                end = new Point(nx, ny);

                List<Point> list = map.get(start);  // 시작점이 가지고 있는 연결 점
                List<Point> list2 = map.get(end);   // 도착점이 가지고 있는 연결 점

                // 방문했던 도착점이고, 도착점 기준으로 연결되지 않은 시작점일 때 answer + 1
                if (list2 != null && !list2.contains(start)) {
                    answer++;
                    list.add(end);
                    list2.add(start);
                    map.put(start, list);
                    map.put(end, list2);
                } else if (list2 == null) { // 도착점 기준으로 연결되지 않은 시작점일 때 간선 생성
                    // 처음에 map.put(start, create(end)) 틀림 : 시작점은 이미 list가 생성되어 있으므로
                    list.add(end);
                    map.put(start, list);
                    map.put(end, create(start));
                }

                x = nx;
                y = ny;
            }
        }

        return answer;
    }

    static List<Point> create(Point point) {
        List<Point> list = new ArrayList<>();
        list.add(point);

        return list;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}