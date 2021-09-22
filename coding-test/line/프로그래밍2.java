package line;

import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class 프로그래밍2 {

    public static Stream<Arguments> 프로그래밍2() {
        return Stream.of(
                Arguments.arguments(
                        new String[]{"abaaaa", "aaa", "abaaaaaa", "fzfffffffa"}, 2, 2, "a"
                ),
                Arguments.arguments(
                        new String[]{"yxxy", "xxyyy"}, 2, 1, "x"
                ),
                Arguments.arguments(
                        new String[]{"yxxy", "xxyyy", "yz"}, 2, 1, "y"
                ),
                Arguments.arguments(
                        new String[]{"xy", "xy"}, 1, 1, "None"
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void 프로그래밍2(String[] research, int n, int k, String answer) {
        assertThat(solution(research, n, k)).isEqualTo(answer);
    }

    public String solution(String[] research, int n, int k) {
        Set<Character> alphabets = new HashSet<>();
        Map<Integer, Map<Character, Long>> all = new HashMap<>();
        for (int i = 0; i < research.length; i++) {
            Map<Character, Long> count = new HashMap<>();
            final char[] chars = research[i].toCharArray();
            for (char character : chars) {
                count.merge(character, 1L, Long::sum);
                alphabets.add(character);
            }
            all.put(i, count);
        }

        Map<Character, Integer> issue = new HashMap<>();
        for (int i = 0; i < research.length - (n - 1); i++) {
            List<Character> pass = new ArrayList<>(alphabets);
            Set<Character> removes = new HashSet<>();
            for (int j = i; j < i + n; j++) {
                for (Character alphabet : pass) {
                    final Long count = all.get(j).get(alphabet);

                    if (count == null || count < k) {
                        removes.add(alphabet);
                    }
                }
            }

            pass.removeAll(removes);

            for (Character alphabet : pass) {
                int sum = 0;
                for (int j = i; j < i + n; j++) {
                    sum += all.get(j).get(alphabet);
                }

                if (sum >= 2 * k * n) {
                    issue.merge(alphabet, 1, Integer::sum);
                }
            }
        }

        if (issue.isEmpty()) {
            return "None";
        }

        Map<Integer, List<Character>> issueAlphabet = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : issue.entrySet()) {
            issueAlphabet.merge(entry.getValue(), new ArrayList<>(List.of(entry.getKey())), (characters, characters2) -> {
                characters.addAll(characters2);
                return characters;
            });
        }

        final Integer max = issueAlphabet.keySet()
                                         .stream()
                                         .max(Comparator.comparingInt(integer -> integer))
                                         .get();

        final List<Character> answer = issueAlphabet.get(max);
        Collections.sort(answer);
        return answer.get(0).toString();
    }
}
