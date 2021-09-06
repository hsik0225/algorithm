import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class 기능개발 {

    public static Stream<Arguments> 기능개발() {
        return Stream.of(
                Arguments.arguments(
                        new int[]{93, 30, 55},
                        new int[]{1, 30, 5},
                        new int[]{2, 1}
                ),
                Arguments.arguments(
                        new int[]{95, 90, 99, 99, 80, 99},
                        new int[]{1, 1, 1, 1, 1, 1},
                        new int[]{1, 3, 2}
                ),
                Arguments.arguments(
                        new int[]{20, 99, 93, 30, 55, 10},
                        new int[]{5, 10, 1, 1, 30, 5},
                        new int[]{3, 3}
                ),
                Arguments.arguments(
                        new int[]{2,2,1,2},
                        new int[]{1,1,1,1},
                        new int[]{2,2}
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void 기능개발(int[] progresses, int[] speeds, int[] answer) {
        assertThat(solution(progresses, speeds)).containsExactly(answer);
    }

    public int[] solution(int[] progresses, int[] speeds) {
        int[] dayOfEnd = new int[100];
        int day = -1;
        for (int i = 0; i < progresses.length; i++) {
            while (progresses[i] + (day * speeds[i]) < 100) {
                day++;
            }
            dayOfEnd[day]++;
        }

        return Arrays.stream(dayOfEnd).filter(i -> i != 0).toArray();
    }
}
