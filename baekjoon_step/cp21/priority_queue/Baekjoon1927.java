package cp21.priority_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1927 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        MinHeap h = new MinHeap(100001);

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                System.out.println(h.delete());
            } else {
                h.insert(x);
            }
        }
    }

    private static class MinHeap {
        int heap[];
        int size;

        public MinHeap(int size) {
            heap = new int[size];
        }

        public void insert(int x) {
            heap[++size] = x;

            for (int i = size; i > 1; i /= 2) {
                if (heap[i / 2] > heap[i]) {    //새로들어온 자식이랑 부모랑 비교함
                    swap(i / 2, i);
                } else {
                    break;
                }
            }
        }//insert 할 때마다 min값이 heap[1]에 들어간다.

        public int delete() {
            if (size == 0) {
                return 0;
            }

            int root = heap[1];
            heap[1] = heap[size];
            size--;

            for (int i = 1; i * 2 <= size; ) {
                if (heap[i] < heap[i * 2] && heap[i] < heap[i * 2 + 1]) {//부모 노드가 왼, 오 자식 보다 작으면 바꿀 필요 없음
                    break;

                } else if (heap[i * 2] < heap[i * 2 + 1]) {    //왼이 오보다 작으면
                    swap(i, i * 2);                    // 부모랑 왼이랑 바꾼다
                    i = i * 2;
                } else {
                    swap(i, i * 2 + 1);
                    i = i * 2 + 1;
                }
            }
            return root;
        }

        public void swap(int a, int b) {
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }
    }
}
