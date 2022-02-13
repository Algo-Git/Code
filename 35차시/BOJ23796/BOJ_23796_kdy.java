import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class N23796 {// BOJ 23796. 2,147,483,648 게임

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[][] board = new long[8][8];// 게임판
        for (int i = 0; i < 8; i++) {// 게임판 입력받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j++) {
                board[i][j] = Long.parseLong(st.nextToken());
            }
        }
        char dir = br.readLine().charAt(0);// 방향
        switch (dir) {
            case 'U':// 위로
                for (int i = 0; i < 8; i++) {// 왼쪽부터 한줄씩
                    Stack<Long> stack = new Stack<>();// 0이 아닌 수를 저장할 스택
                    boolean flag = false;// 수를 합치는 것을 한 번만 수행 => 합쳤으면 true표시
                    for (int j = 0; j < 8; j++) {// 벽에 가까운 수 부터
                        if (board[j][i] == 0) continue;// 0이면 지나가기
                        if (!stack.isEmpty() && stack.peek() == board[j][i] && !flag) {// 이전값과 같은데 합치지않은 수인 경우
                            stack.push(stack.pop() << 1);// 합치기
                            flag = true;// 합쳤다고 표시
                        } else {// 그냥 넣기
                            stack.push(board[j][i]);
                            flag = false;// 합치기 X
                        }
                    }
                    for (int j = stack.size(); j < 8; j++) {// 스택부분 제외하고 다 0으로 채우기
                        board[j][i] = 0;
                    }
                    for (int j = stack.size() - 1; j >= 0; j--) {// 스택에 넣은 수 거꾸로 넣기
                        board[j][i] = stack.pop();
                    }
                }
                break;
            case 'D':// 아래로
                for (int i = 0; i < 8; i++) {// 왼쪽에서 오른쪽으로
                    Stack<Long> stack = new Stack<>();// 수 저장할 스택
                    boolean flag = false;// 합치기 표시
                    for (int j = 7; j >= 0; j--) {// 아래부터 살펴보기
                        if (board[j][i] == 0) continue;// 0이면 지나가기
                        if (!stack.isEmpty() && stack.peek() == board[j][i] && !flag) {// 스택값과 같으면서 합친 수 아니면
                            stack.push(stack.pop() << 1);// 합치기
                            flag = true;// 합치기 표시
                        } else {// 나머지
                            stack.push(board[j][i]);// 스택에 숫자 넣기
                            flag = false;// 합치기 X
                        }
                    }
                    for (int j = 0; j < 8 - stack.size(); j++) {// 스택 크기뺴고 0으로 채우기
                        board[j][i] = 0;
                    }
                    for (int j = 8 - stack.size(); j < 8; j++) {// 스택 숫자 넣기
                        board[j][i] = stack.pop();
                    }
                }
                break;
            case 'L':// 왼쪽
                for (int i = 0; i < 8; i++) {// 위->아래
                    Stack<Long> stack = new Stack<>();
                    boolean flag = false;
                    for (int j = 0; j < 8; j++) {// 왼쪽->오른쪽
                        if (board[i][j] == 0) continue;
                        if (!stack.isEmpty() && stack.peek() == board[i][j] && !flag) {// 숫자가 같고 합친적 없는 수면
                            stack.push(stack.pop() << 1);// 합치기
                            flag = true;
                        } else {
                            stack.push(board[i][j]);// 값 넣기
                            flag = false;
                        }
                    }
                    for (int j = stack.size(); j < 8; j++) {// 0으로 채우기
                        board[i][j] = 0;
                    }
                    for (int j = stack.size() - 1; j >= 0; j--) {// 숫자 넣기
                        board[i][j] = stack.pop();
                    }
                }
                break;
            case 'R':// 오른쪽
                for (int i = 0; i < 8; i++) {// 위->아래
                    Stack<Long> stack = new Stack<>();
                    boolean flag = false;
                    for (int j = 7; j >= 0; j--) {// 오른쪽->왼쪽
                        if (board[i][j] == 0) continue;
                        if (!stack.isEmpty() && stack.peek() == board[i][j] && !flag) {// 숫자 같으면서 안 합친 수
                            stack.push(stack.pop() << 1);// 합치기
                            flag = true;
                        } else {
                            stack.push(board[i][j]);// 숫자 넣기
                            flag = false;
                        }
                    }
                    for (int j = 0; j < 8 - stack.size(); j++) {// 0으로 채우기
                        board[i][j] = 0;
                    }
                    for (int j = 8 - stack.size(); j < 8; j++) {// 숫자 넣기
                        board[i][j] = stack.pop();
                    }
                }
                break;
        }
        for (int i = 0; i < 8; i++) {// 게임판 출력
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
