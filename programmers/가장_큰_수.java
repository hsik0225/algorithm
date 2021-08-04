import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class 가장_큰_수 {

    public static Stream<Arguments> testCase() {
        return Stream.of(
                Arguments.arguments(
                        new int[]{6, 10, 2}, "6210"
                ),
                Arguments.arguments(
                        new int[]{3, 30, 34, 5, 9}, "9534330"
                ),
                Arguments.arguments(
                        new int[]{0, 0, 0}, "0"
                ),
                Arguments.arguments(
                        new int[]{34, 343}, "34343"
                ),
                Arguments.arguments(
                        new int[]{90, 908, 89, 898, 10, 101, 1, 8, 9}, "990908898988110110"
                ),
                Arguments.arguments(
                        new int[]{10, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, "987654321101000"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testCase")
    void 가장_앞_숫자_정렬(int[] numbers, String answer) {
        assertThat(solution(numbers)).isEqualTo(answer);
    }

    private String solution(int[] numbers) {
        return Arrays.stream(numbers)
                     .boxed()
                     .map(number -> Integer.toString(number))
                     .sorted(((Comparator<String>) (o1, o2) -> (o1 + o2).compareTo(o2 + o1)).reversed())
                     .reduce("", (s, s2) -> s.equals("0") && s2.equals("0") ? "0" : s + s2);
    }
}
