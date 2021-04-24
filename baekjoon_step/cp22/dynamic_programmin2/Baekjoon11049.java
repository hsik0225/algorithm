package cp22.dynamic_programmin2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/11049
*/
public class Baekjoon11049 {
    private static int[][] input;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        input = new int[n][2];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        System.out.println(solve(0, n - 1));
    }

    private static int solve(int start, int end) {
        if (start == end) {
            return 0;
        }

        if (dp[start][end] != Integer.MAX_VALUE) {
            return dp[start][end];
        }

        // solve(start, i) : 현재 행렬을 기준으로 왼쪽 행렬들에서의 곱셈 횟수
        // solve(i + 1, end) : 현재 행렬을 기준으로 오른쪽 행렬들에서의 곱셈 횟수
        // input[start][0] * input[i][1] * input[end][1] : 현재 행렬에서 필요한 곱셈 횟수
        for (int i = start; i < end; i++) {
            int cost = solve(start, i) + solve(i + 1, end) + input[start][0] * input[i][1] * input[end][1];
            dp[start][end] = Math.min(dp[start][end], cost);
        }

        return dp[start][end];
    }
}
