import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class 위장 {
    public static Stream<Arguments> 위장() {
        return Stream.of(
                Arguments.arguments(
                        new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}, 5
                ),
                Arguments.arguments(
                        new String[][]{{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}}, 3
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void 위장(String[][] clothes, int answer) {
        assertThat(solution(clothes)).isEqualTo(answer);
    }

    private int solution(String[][] clothes) {
        return Arrays.stream(clothes)
                     .collect(Collectors.groupingBy(clothe -> clothe[1], Collectors.counting()))
                     .values()
                     .stream()
                     .reduce(1L, (a, b) -> a * (b+1))
                     .intValue() - 1;
    }
}
