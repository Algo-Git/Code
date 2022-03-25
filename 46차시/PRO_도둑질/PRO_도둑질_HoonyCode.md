# 문제

###### 문제 설명

도둑이 어느 마을을 털 계획을 하고 있습니다. 이 마을의 모든 집들은 아래 그림과 같이 동그랗게 배치되어 있습니다.

![image.png](https://grepp-programmers.s3.amazonaws.com/files/ybm/e7dd4f51c3/a228c73d-1cbe-4d59-bb5d-833fd18d3382.png)

각 집들은 서로 인접한 집들과 방범장치가 연결되어 있기 때문에 인접한 두 집을 털면 경보가 울립니다.

각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.

##### 제한사항

- 이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
- money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.



## 문제 풀이

> 동적 계획법



문제를 풀때, 처음에는 일일히 방문한지 안 한지 검사할려고 했다. => 시간 초과

다시 문제를 보고 생각을 해보니 처음 집에서 마지막집 전까지 터는 경우와 두번째 집에서 마지막집 전까지 터는 경우를 구해서

둘 중 Max 값을 구하면 된다고 생각했다.



그렇게 해서 문제를 푸니 다행히 성공적으로 문제가 풀렸다.



```java
import java.util.*;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        int[] dp = new int[money.length];
        int[] dp2 = new int[money.length];
        
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        dp2[1] = money[1];
        
        
        for(int i = 2 ; i < money.length; i++){
            dp[i] = Math.max(dp[i-2] + money[i], dp[i-1]);
            dp2[i] = Math.max(dp2[i-2] + money[i], dp2[i-1]);
        }
        
        
        answer = Math.max(dp2[money.length-1], dp[money.length-2]);
        
        return answer;
    }
}
```