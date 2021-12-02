import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2056 {

    static int N;
    static Job[] job;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // N 입력
        job = new Job[N + 1];
        v = new boolean[N + 1];

        //초기화
        for (int i = 0; i <= N; i++) {
            job[i] = new Job();
        }

        //입력
        String[] input;
        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            job[i].time = Integer.parseInt(input[0]);
            job[i].cnt = Integer.parseInt(input[1]);

            for (int j = 2; j < 2 + job[i].cnt; j++) {
                //예시 1->2를 가리키면 1으 리스트에 2를 넣어둠
                job[Integer.parseInt(input[j])].list.add(i);
            }
        }


        //해결부분
        int total = 0;


        ArrayList<Job> list = new ArrayList<>();

        while (total != N) {

            list.clear();

            for (int i = 1; i <= N; i++) {
                if (v[i] || job[i].cnt != 0) continue;
                v[i] = true;
                list.add(job[i]);
                total++;
            }

            //시간순으로 정렬
            Collections.sort(list);

            for (Job temp : list) {
                for (int i = 0; i < temp.list.size(); i++) {
                    if (job[temp.list.get(i)].cnt == 1) {
                        job[temp.list.get(i)].time += temp.time;
                    }
                    job[temp.list.get(i)].cnt--;
                }
            }
        }

        int res = 0;

        for (int i = 1; i <= N; i++) {
            if (res < job[i].time) res = job[i].time;
        }

        System.out.print(res);

    }


    static class Job implements Comparable<Job> {
        int time, cnt;
        ArrayList<Integer> list = new ArrayList<>();

        public Job() {
        }

        @Override
        public int compareTo(Job o) {
            return Integer.compare(this.time, o.time);
        }
    }
}



