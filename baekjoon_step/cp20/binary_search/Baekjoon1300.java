package cp20.binary_search;

import java.io.*;

/*
1300. K번째 수

https://www.acmicpc.net/problem/1300

https://devowen.com/265

 */
public class Baekjoon1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        
        long left = 1;
        long right = k;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
    
            for (int i = 1; i <= n; i++) {
                count += Math.min(mid / i, n);
            }
            
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        System.out.println(left);
    }
}
