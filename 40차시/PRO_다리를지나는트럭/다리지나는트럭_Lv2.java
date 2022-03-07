package com.ssafy.programas.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class 다리지나는트럭_Lv2 {
    public static void main(String[] args) {
        int[] truck_weights = {7, 4, 5, 6};
        int bridge_length = 2;
        int weight = 10;
        System.out.println(solution(bridge_length, weight, truck_weights));
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int weight_sum = truck_weights[0];
        Queue<Truck> bridge = new LinkedList<>(); //다리에 있는 트럭
        Queue<Truck> wait = new LinkedList<>();//겉널 트럭들

        for (int truck_weight : truck_weights) {
            wait.offer(new Truck(truck_weight, 0));
        }
        bridge.offer(wait.poll());

        while (!wait.isEmpty()) {

            //다리위에 이동한 거리를 나타냄
            for (Truck truck : bridge) {
                truck.move++;
            }

            //다리의 길이만큼 이동하면 도착한 차는 뺀다.
            if (bridge_length == bridge.peek().move) {
                Truck truck = bridge.poll();
                weight_sum -= truck.weight;
            }

            if (bridge_length >= bridge.size()) {
                int next = weight_sum + wait.peek().weight;
                if (next <= weight) {
                    bridge.offer(wait.poll());
                    weight_sum = next;
                }
                answer++;
            }

        }

        return answer + bridge_length + 1;
    }

    static class Truck {
        int weight, move;

        public Truck(int weight, int move) {
            this.weight = weight;
            this.move = move;
        }
    }
}
