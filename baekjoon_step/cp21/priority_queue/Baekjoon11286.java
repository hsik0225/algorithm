package cp21.priority_queue;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Baekjoon11286 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            //절댓값 기준으로 오름차순 정렬
            if (Math.abs(o1) > Math.abs(o2)) {
                return 1;
            } else if (Math.abs(o1) == Math.abs(o2)) {
                return o1.compareTo(o2);
            } else return -1;
        });

        for (int i = 0; i < N; ++i) {
            int num = scanner.nextInt();
            if (num != 0) {
                priorityQueue.offer(num);
            } else {
                System.out.println(priorityQueue.size() == 0 ? 0 : priorityQueue.poll());
            }
        }
    }
}
