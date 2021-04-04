package cp20.binary_search;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
12015. 숫자 카드2

https://www.acmicpc.net/problem/12015
 */
public class Baekjoon12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer> result = new ArrayList<>();
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result.add(0);

        for (int i : input) {
            int min = 1;
            int max = result.size() - 1;

            if (result.get(max) < i) {
                result.add(i);
            } else {
                while (min <= max) {
                    int mid = (max + min) / 2;
                    if (result.get(mid) < i) {
                        min = mid + 1;
                    } else {
                        max = mid - 1;
                    }
                }
                result.set(min, i);
            }
        }

        System.out.println(result.size() - 1);
    }
}
