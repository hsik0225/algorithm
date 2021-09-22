package line;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class 프로그래밍3 {

    public static Stream<Arguments> 프로그래밍3() {
        return Stream.of(
                Arguments.arguments(
                        new int[][]{{1, 5, 2, 3}, {2, 2, 3, 2}, {3, 1, 3, 3}, {5, 2, 1, 5}, {7, 1, 1, 1}, {9, 1, 1, 1}, {10, 2, 2, 9}}, new int[]{2, 1, 2, 3}
                ),
                Arguments.arguments(
                        new int[][]{{1, 2, 1, 5}, {2, 1, 2, 100}, {3, 2, 1, 5}, {5, 2, 1, 5}}, new int[]{1, 2}
                ),
                Arguments.arguments(
                        new int[][]{{0, 2, 3, 1}, {5, 3, 3, 1}, {10, 2, 4, 1}}, new int[]{3, 4}
                ),
                Arguments.arguments(
                        new int[][]{{0, 5, 1, 1}, {2, 4, 3, 3}, {3, 4, 4, 5}, {5, 2, 3, 2}}, new int[]{1, 3, 4}
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void 프로그래밍3(int[][] jobs, int[] answer) {
        assertThat(solution(jobs)).isEqualTo(answer);
    }

    public int[] solution(int[][] jobs) {
        List<Integer> answer = new ArrayList<>();

        Queue<Task> queue = new LinkedList<>();
        queue.add(new Task(jobs[0]));
        answer.add(jobs[0][2]);

        int now = jobs[0][0];

        List<Task> allTasks = new ArrayList<>();
        for (int[] job : jobs) {
            allTasks.add(new Task(job));
        }

        List<Task> tasks = new ArrayList<>();

        while (!queue.isEmpty()) {
            final Task task = queue.poll();
            allTasks.remove(task);
            tasks.remove(task);

            if (answer.get(answer.size() - 1) != task.sortNumber) {
                answer.add(task.sortNumber);
            }

            for (int i = 0; i < jobs.length; i++) {
                if (jobs[i][0] > now && jobs[i][0] <= task.duringTime + now) {
                    tasks.add(new Task(jobs[i]));
                }
            }

            final List<Task> todo = tasks.stream()
                                         .filter(t -> t.sortNumber == task.sortNumber)
                                         .collect(Collectors.toList());

            queue.addAll(todo);
            tasks.removeAll(todo);

            if (queue.isEmpty()) {
                if (tasks.isEmpty()) {
                    if (!allTasks.isEmpty()) {
                        queue.add(allTasks.get(0));
                        continue;
                    }

                    else {
                        break;
                    }
                }

                Map<Integer, Integer> next = new HashMap<>();
                for (Task task1 : tasks) {
                    next.merge(task1.sortNumber, task1.priority, Integer::sum);
                }

                int maxPriority = 0;
                int maxSort = 0;
                for (Integer sortNumber : next.keySet()) {
                    if (next.get(sortNumber) > maxPriority) {
                        maxPriority = next.get(sortNumber);
                        maxSort = sortNumber;
                    }
                }

                List<Task> nextTask = new ArrayList<>();
                for (Task task1 : tasks) {
                    if (task1.sortNumber == maxSort) {
                        nextTask.add(task1);
                    }
                }

                final List<Task> collect = nextTask.stream().sorted(Comparator.comparingInt(o -> o.requestTime)).collect(Collectors.toList());
                queue.add(collect.get(0));
            }

            now += task.duringTime;
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private static class Task {
        public final int requestTime;
        public final int duringTime;
        public final int sortNumber;
        public final int priority;

        public Task(int[] job) {
            this.requestTime = job[0];
            this.duringTime = job[1];
            this.sortNumber = job[2];
            this.priority = job[3];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Task))
                return false;
            Task task = (Task) o;
            return requestTime == task.requestTime && duringTime == task.duringTime && sortNumber == task.sortNumber && priority == task.priority;
        }

        @Override
        public int hashCode() {
            return Objects.hash(requestTime, duringTime, sortNumber, priority);
        }
    }
}
