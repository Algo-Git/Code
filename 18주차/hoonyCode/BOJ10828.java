import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ10828 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();

        String[] input;
        for(int i = 0 ; i < n ; i++){
            input = br.readLine().split(" ");

            switch (input[0]){
                case "push":
                    stack.push(Integer.parseInt(input[1]));
                    break;
                case "top":
                    if(stack.isEmpty()){
                        sb.append(-1).append('\n');
                        continue;
                    }
                    sb.append(stack.peek()).append('\n');;
                    break;
                case "pop":
                    if(stack.isEmpty()){
                        sb.append(-1).append('\n');
                        continue;
                    }
                    sb.append(stack.pop()).append('\n');
                    break;
                case "size":
                    sb.append(stack.size()).append('\n');
                    break;
                case "empty":
                    if(stack.isEmpty()){
                        sb.append(1).append('\n');
                        continue;
                    }
                    sb.append(0).append('\n');
                    break;
            }
        }
        System.out.println(sb.toString());

    }
}
