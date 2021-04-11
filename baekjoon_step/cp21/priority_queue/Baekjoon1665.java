package cp21.priority_queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Baekjoon1665 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();

        int N = scanner.nextInt();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());

        for (int i = 0; i < N; i++) {
            int number = scanner.nextInt();

            if (i % 2 == 0) {
                maxHeap.add(number);
            } else {
                minHeap.add(number);
            }

            if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                if (maxHeap.peek() > minHeap.peek()) {
                    int temp = maxHeap.poll();
                    maxHeap.add(minHeap.poll());
                    minHeap.add(temp);
                }
            }

            stringBuilder.append(maxHeap.peek())
                    .append("\n");
        }

        System.out.println(stringBuilder);
    }
}
