import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class 프린터 {

    public static Stream<Arguments> 프린터() {
        return Stream.of(
                Arguments.arguments(
                        new int[]{2, 1, 3, 2},
                        2,
                        1
                ),
                Arguments.arguments(
                        new int[]{1, 1, 9, 1, 1, 1},
                        0,
                        5
                ),
                Arguments.arguments(
                        new int[]{1, 1, 9, 1, 1, 1},
                        2,
                        1
                ),
                Arguments.arguments(
                        new int[]{1},
                        0,
                        1
                ),
                Arguments.arguments(
                        new int[]{2, 1, 9, 1, 9, 1},
                        1,
                        4
                ),
                Arguments.arguments(
                        new int[]{1, 2, 1, 1, 1},
                        0,
                        5
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void 프린터(int[] priorities, int location, int answer) {
        assertThat(solution(priorities, location)).isEqualTo(answer);
    }

    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int priority : priorities) {
            priorityQueue.add(priority);
        }

        int answer = 1;
        while (!priorityQueue.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorityQueue.peek() == priorities[i]) {
                    if (i == location) {
                        return answer;
                    }

                    priorityQueue.poll();
                    answer++;
                }
            }
        }


        return answer;
    }
}
