package cp7.string;

import java.util.Scanner;

/*

https://www.acmicpc.net/problem/11720

문제
N개의 숫자가 공백 없이 쓰여있다. 이 숫자를 모두 합해서 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 숫자의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄에 숫자 N개가 공백없이 주어진다.

 */
public class Baekjoon11720 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();

        final int sum = scanner.nextLine()
                               .chars()
                               .map(number -> number - '0')
                               .sum();

        System.out.println(sum);
    }
}
