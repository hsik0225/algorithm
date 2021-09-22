package line;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class 프로그래밍6 {

    public static Stream<Arguments> 프로그래밍6() {
        return Stream.of(
                Arguments.arguments(
                        new String[]{"2020-01-01 uid1000 pid5000"},
                        10,
                        "2020-01-11",
                        new String[]{"no result"}
                ),
                Arguments.arguments(
                        new String[]{"2020-02-02 uid141 pid141", "2020-02-03 uid141 pid32", "2020-02-04 uid32 pid32", "2020-02-05 uid32 pid141"},
                        10,
                        "2020-02-05",
                        new String[]{"pid32", "pid141"}
                ),
                Arguments.arguments(
                        new String[]{"2020-02-02 uid1 pid1", "2020-02-26 uid1 pid1", "2020-02-26 uid2 pid1", "2020-02-27 uid3 pid2", "2020-02-28 uid4 pid2", "2020-02-29 uid3 pid3", "2020-03-01 uid4 pid3", "2020-03-03 uid1 pid1", "2020-03-04 uid2 pid1", "2020-03-05 uid3 pid2", "2020-03-05 uid3 pid3", "2020-03-05 uid3 pid3", "2020-03-06 uid1 pid4"},
                        10,
                        "2020-03-05",
                        new String[]{"pid1", "pid3", "pid2"}
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void 프로그래밍6(String[] records, int k, String date, String[] answer) {
        assertThat(solution(records, k, date)).isEqualTo(answer);
    }

    public String[] solution(String[] records, int k, String date) {
        String[] answer = {};

        LocalDate endDate = LocalDate.parse(date);
        LocalDate startDate = endDate.minusDays(k);
        if (endDate.getDayOfMonth() < 10 && startDate.lengthOfMonth() > 30) {
            startDate = startDate.minusDays(1);
        }

        final LocalDate finalStartDate = startDate;

        List<Order> orders = new ArrayList<>();
        for (String record : records) {
            orders.add(new Order(record));
        }

        final List<Order> orderList = orders.stream()
                                            .filter(order -> order.localDate.compareTo(finalStartDate) >= 0 && order.localDate.compareTo(endDate) <= 0)
                                            .collect(Collectors.toList());

        Map<String, List<UserOrder>> userOrderMap = new HashMap<>();
        for (Order order : orderList) {
            userOrderMap.merge(order.productId,
                    new ArrayList<>(List.of(new UserOrder(order.userId, 1))),
                    (userOrders, userOrders2) -> {
                        for (UserOrder userOrder : userOrders2) {
                            if (userOrders.contains(userOrder)) {
                                final int i = userOrders.indexOf(userOrder);
                                final UserOrder userOrder1 = userOrders.get(i);
                                userOrder1.count = userOrder1.count + 1;
                                continue;
                            }

                            userOrders.add(userOrder);
                        }
                        return userOrders;
                    }
            );
        }


        return new String[]{"no result"};
    }

    private static class Order {
        public final LocalDate localDate;
        public final String userId;
        public final String productId;

        public Order(String record) {
            final String[] strings = record.split(" ");
            this.localDate = LocalDate.parse(strings[0]);
            this.userId = strings[1];
            this.productId = strings[2];
        }
    }

    private static class UserOrder {
        public String userId;
        public int count;

        public UserOrder(String userId, int count) {
            this.userId = userId;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof UserOrder))
                return false;
            UserOrder userOrder = (UserOrder) o;
            return Objects.equals(userId, userOrder.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId);
        }
    }
}
