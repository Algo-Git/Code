## 풀이방법

- k개의 범위에서 가장 큰수를 찾고 적용한다
- 재귀를 이용해서 다은 범위의 큰 값을 찾는다
- 위를 반복한다.



### 깨달은 점

`String` 에서 `+` 연산자 사용을 지양하자

사용할 일이 생긴다면 `StringBuilder` 에서 `append` 함수를 사용하자!



## String + 연산자를 사용해서 풀었을 때

```java
class Solution {

     String answer = "";

     public String solution(String number, int k) {

        int[] arr = new int[number.length()];

        for (int i = 0; i < number.length(); i++) {
            arr[i] = number.charAt(i) - '0';
        }

        makeAnswer(arr,0, arr.length, k, arr.length - k);

        return answer;
    }


     void makeAnswer(int[] arr, int start, int end, int k, int total) {

        if (total == 0) return;

        if (k == 0){
            if (total != 0){
                for (int i = end - total ; i < end ; i++){
                    answer += Integer.toString(arr[i]);
                }
            }

            return ;
        }

        int index = 0;
        int value = -1;
        for (int i = start; i <= start + k; i++) {
            if (arr[i] > value) {
                value = arr[i];
                index = i;
            }
        }
        answer += Integer.toString(value);

        makeAnswer(arr, index + 1, end, k - (index - start), total - 1);
    }
}
```



### `결과`

![image](https://user-images.githubusercontent.com/44612896/158821892-c4d15844-616c-4343-915c-c833a48d1968.png)



## StringBuilder로 고쳤을 때

```java
class Solution {

    StringBuilder sb = new StringBuilder();

    public String solution(String number, int k) {

        int[] arr = new int[number.length()];

        for (int i = 0; i < number.length(); i++) {
            arr[i] = number.charAt(i) - '0';
        }

        makeAnswer(arr,0, arr.length, k, arr.length - k);

        return sb.toString();
    }


    void makeAnswer(int[] arr, int start, int end, int k, int total) {

        if (total == 0) return;

        if (k == 0){
            if (total != 0){
                for (int i = end - total ; i < end ; i++){
                    sb.append(arr[i]);
                }
            }

            return ;
        }

        int index = 0;
        int value = -1;
        for (int i = start; i <= start + k; i++) {
            if (arr[i] > value) {
                value = arr[i];
                index = i;
            }
        }
        sb.append(value);

        makeAnswer(arr, index + 1, end, k - (index - start), total - 1);
    }
}
```



### `결과`

![image](https://user-images.githubusercontent.com/44612896/158821910-0bd77091-c802-4580-b237-6399233bb573.png)



## Stack을 이용해서 풀었을 때

```java
import java.util.Stack;

 class Solution {
     public String solution(String number, int k) {
        char[] answer = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        char cur;
        for (int i = 0 ; i < number.length() ; i++){
            cur = number.charAt(i);

            while (!stack.isEmpty() && stack.peek() < cur && k-- > 0){
                stack.pop();
            }
            stack.push(cur);
        }

        for (int i = 0; i < answer.length ; i++){
            answer[i] = stack.get(i);
        }
        return new String(answer);
    }
}
```



### `결과`

![image](https://user-images.githubusercontent.com/44612896/158821932-d6ac5c56-ac4e-4fa5-a674-44ef2cbc5793.png)