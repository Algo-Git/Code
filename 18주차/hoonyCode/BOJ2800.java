import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2800 {

    static String str;
    static List<String> list;
    static Set<String> set;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        list = new ArrayList<>();
        arr = new int[str.length()];
        set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        int index = 1;
        // 예시 (1 + ( 1 + 1 ) + 1)
        // arr => 1 -- 2 ---- 2 --- 1 이렇게 만들어 준다.
        for (int i = 0 ; i < str.length() ; i ++){
            if(str.charAt(i) == '(' ){
                arr[i] = index++;
            }else if(str.charAt(i) == ')') {
                arr[i] = --index;
            }
        }

        dfs(0, new Stack<>(), new String());
        list.addAll(set); // 복사하고
        Collections.sort(list); // 사전순으로 정리한다.
        for (int i = 1 ; i < list.size() ; i++){ // 1부터 시작하는 이유는 첫번째를 빼기위함이다.
            sb.append(list.get(i)).append('\n');
        }

        System.out.print(sb.toString());

    }

    static void dfs(int dep, Stack<Integer> stack, String s){
        if(dep == str.length()){
            set.add(s); // set에 넣는 이유는 겹치는 것을 방지하기 위함
            return;
        }

        if(str.charAt(dep) == '('){
            stack.add(arr[dep]);
            //넣을 경우
            dfs(dep+1, stack, s+'(');
            stack.pop();
            //안 넣을 경우
            dfs(dep+1, stack, s);
        }
        else if(str.charAt(dep) == ')') {
            //스택에  값이 있고 arr[dep]과 같이 같으면
            if (!stack.isEmpty() && stack.peek() == arr[dep]) {
                stack.pop(); // 스택에서 기록한 것을 빼고
                dfs(dep + 1, stack, s + ')'); // 괄호를 닫아준다.
                stack.add(arr[dep]);
            } else
                dfs(dep + 1, stack, s); // arr[dep]와 값이 같지 않다면 그냥 넘긴다.
        }else{
            //아무것도 없을 경우 원래 문자열을 집어 넣는다.
            dfs(dep+1, stack, s+str.charAt(dep));
        }
    }

}
