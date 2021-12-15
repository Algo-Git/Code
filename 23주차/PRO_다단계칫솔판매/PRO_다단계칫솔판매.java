import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        Map<String, Person> map = new HashMap<>();
        int[] answer = new int[enroll.length];
        map.put("-", new Person("-"));

        for (int i = 0; i < enroll.length; i++) {
            String er = enroll[i];
            String ref = referral[i];

            // 등록하는 부분
            Person cur = new Person(er);
            cur.list.add(ref);
            map.put(er, cur);

            //언급하는 부분
            cur = map.get(ref);
            cur.cnt++; // 날 가르키고 있는 갯수
            map.put(ref, cur);
        }


        for (int i = 0; i < seller.length; i++) {
            Person cur = map.get(seller[i]);
            cur.money.add(amount[i] * 100);
            map.put(cur.name, cur);
        }


        //위상정렬
        Queue<Person> que = new LinkedList<>();
        for (int i = 0 ; i < enroll.length ; i++){
            Person cur = map.get(enroll[i]);
            if (cur.cnt == 0){
                que.offer(cur);
            }
        }

        Person cur;
        List<Integer> givemoney = new ArrayList<>();
        while (!que.isEmpty()){
            cur = que.poll();

            givemoney.clear();
            for (int i = 0 ; i < cur.money.size(); i++){
                int money = (int)(cur.money.get(i) * 0.1);
                cur.result += cur.money.get(i) - money;
                if (money == 0) continue;
                givemoney.add(money);
            }
            map.put(cur.name, cur);

            Person next;
            for (String nextname : cur.list){
                next = map.get(nextname);

                next.cnt--;
                next.money.addAll(givemoney);

                if (next.cnt == 0){
                    que.offer(next);
                }else{
                    map.put(next.name, next);
                }
            }
        }

        // 답 출력
        for (int i = 0 ; i < enroll.length ; i++){
            answer[i] = map.get(enroll[i]).result;
        }

        return answer;
    }
}

class Person {
    String name;
    int cnt = 0;
    List<String> list = new ArrayList<>();
    List<Integer> money = new ArrayList<>();
    int result = 0;

    Person(){
    }

    Person(String name) {
        this.name = name;
    }
}