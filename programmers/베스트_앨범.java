import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class 베스트_앨범 {

    public static Stream<Arguments> 가장_앞_숫자_정렬() {
        return Stream.of(
                Arguments.arguments(
                        new String[]{"classic", "pop", "classic", "classic", "pop"},
                        new int[]{500, 600, 150, 800, 2500},
                        new int[]{4, 1, 3, 0}
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void 가장_앞_숫자_정렬(String[] genres, int[] plays, int[] answer) {
        assertThat(solution(genres, plays)).containsExactly(answer);
    }

    public int[] solution(String[] genres, int[] plays) {
        return IntStream.range(0, genres.length)
                 .mapToObj(i -> new Song(i, genres[i], plays[i]))
                 .collect(Collectors.groupingBy(song -> song.genre))
                 .entrySet().stream()
                 .sorted((a, b) -> sum(b.getValue()) - sum(a.getValue()))
                 .flatMap(x -> x.getValue().stream().sorted().limit(2))
                 .mapToInt(x -> x.index).toArray();
    }

    private int sum(List<Song> value) {
        return value.stream().mapToInt(song -> song.play).sum();
    }

    private static class Song implements Comparable<Song> {
        private final int index;
        private final String genre;
        private final int play;

        public Song(int index, String genre, int play) {
            this.index = index;
            this.genre = genre;
            this.play = play;
        }

        @Override
        public int compareTo(Song other) {
            if (this.play == other.play) {
                return this.index - other.index;
            }

            return other.play - this.play;
        }
    }
}
