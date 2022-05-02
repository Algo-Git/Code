package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_왕위계승_HoonyCode {

    static int N, M; //N 가족정보 M / 왕위를 계승받기를 주장하는 사람의 이름
    static Map<String, Data> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        String king = br.readLine();

        map.put(king, new Data(king, 1D, new ArrayList<>(), 0));

        //첫 번째 이름은 자식의 이름
        //뒤의 두 이름은 부모의 이름이다

        //피가 덜 섞인 사람이다 모든 사람은 아버지의 혈통과 어머니의 혈통을 반 씩 받게 된다

        // 나라를 세운 자식은 1/2 왕족이며, 그 아들이 왕족이 아닌 사람과 결혼 경우에는 아들의 자식은 1/4 왕족이 된다.
        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            String child = in[0];
            String p1 = in[1];
            String p2 = in[2];

            if (map.containsKey(child)) {
                Data data = map.get(child);
                data.cnt += 2;
            } else {
                map.put(child, new Data(child, 0D, new ArrayList<>(), 2));
            }


            if (map.containsKey(p1)) {
                Data parent = map.get(p1);
                parent.list.add(child);
            } else {
                List<String> list = new ArrayList<>();
                list.add(child);
                map.put(p1, new Data(p1, 0D, list, 0));
            }

            if (map.containsKey(p2)) {
                Data parent = map.get(p2);
                parent.list.add(child);
            } else {
                List<String> list = new ArrayList<>();
                list.add(child);
                map.put(p2, new Data(p2, 0D, list, 0));
            }
        }

        Queue<Data> queue = new LinkedList<>();

        for (Data temp : map.values()) {
            if (temp.cnt == 0) {
                queue.offer(temp);
            }
        }

        Data cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            for (String name : cur.list) {
                Data next = map.get(name);
                next.cnt--;
                next.answer += (cur.answer / 2);
                if (next.cnt == 0) {
                    queue.offer(next);
                }
            }
        }

        Data[] arr = new Data[M];

        for (int i = 0; i < M; i++) {
            String name = br.readLine();

            if (map.containsKey(name)){
                arr[i] = map.get(name);
            }else{
                arr[i] = new Data(name, 0D, null, 0);
            }
        }

        Arrays.sort(arr, (o1, o2) -> -Double.compare(o1.answer, o2.answer));


        System.out.println(arr[0].name);
    }

    static class Data {
        String name;
        double answer = 0;
        List<String> list;
        int cnt = 0;

        public Data(String name, double answer, List<String> list, int cnt) {
            this.name = name;
            this.answer = answer;
            this.list = list;
            this.cnt = cnt;
        }

    }
}
