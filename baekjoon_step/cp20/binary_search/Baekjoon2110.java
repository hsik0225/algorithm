package cp20.binary_search;

import java.io.*;
import java.util.Arrays;

/*
2110. 공유기 설치

https://www.acmicpc.net/problem/2110

 */
public class Baekjoon2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        String[] input = br.readLine()
                           .split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
    
        int[] houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
    
        Arrays.sort(houses);
        
        // start와 end는 가능한(acceptable) 떨어진 거리를 의미
        int start = 1;
        int end = houses[n - 1] - houses[0];
        int mid = 0;
        
        while (start <= end) {
            int count = 1;
            int prev = houses[0];
            mid = (start + end) / 2;
    
            for (int house : houses) {
                if (house - prev >= mid) {
                    count++;
                    prev = house;
                }
            }
            
            if (count >= k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    
        System.out.println(end);
    }
}
