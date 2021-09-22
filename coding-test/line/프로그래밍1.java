package line;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class 프로그래밍1 {

    public static Stream<Arguments> 프로그래밍1() {
        return Stream.of(
                Arguments.arguments(
                        new int[]{0, 1, 0, 0}, 1, 6
                ),
                Arguments.arguments(
                        new int[]{0, 1, 0, 0, 1, 1, 0}, 2, 8
                ),
                Arguments.arguments(
                        new int[]{0, 1, 0}, 2, 0
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void 프로그래밍1(int[] student, int k, int answer) {
        assertThat(solution(student, k)).isEqualTo(answer);
    }

    public int solution(int[] student, int k) {
        int answer = -1;
        return answer;
    }
}
