package cp22.dynamic_programmin2;

import java.io.*;
import java.util.StringTokenizer;

/*
https://www.acmicpc.net/problem/1520
 */
public class Baekjoon1520 {
    static int vertical;
    static int horizontal;
    static int[][] map; // 지도
    static int[][] memoization; // 메모이제이션

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        vertical = Integer.parseInt(st.nextToken());
        horizontal = Integer.parseInt(st.nextToken());
        map = new int[vertical][horizontal];
        memoization = new int[vertical][horizontal];

        for (int i = 0; i < vertical; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < horizontal; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                memoization[i][j] = -1;
            }
        }

        System.out.println(findWay(0, 0));
    }

    private static int findWay(int x, int y) {

        if (x == vertical - 1 && y == horizontal - 1) { // 도착지점에 도달하면 경로의 수 1을 리턴한다
            return 1;
        }

        if (memoization[x][y] == -1) { // 경로의 수가 계산된 적 없고, 방문한 적 없는 경우만 계산
            memoization[x][y] = 0;

            // 위로 이동
            if (x > 0 && map[x][y] > map[x - 1][y]) {
                memoization[x][y] += findWay(x - 1, y);
            }
            // 아래로 이동
            if (x < vertical - 1 && map[x][y] > map[x + 1][y]) {
                memoization[x][y] += findWay(x + 1, y);
            }
            // 왼쪽으로 이동
            if (y > 0 && map[x][y] > map[x][y - 1]) {
                memoization[x][y] += findWay(x, y - 1);
            }
            // 오른쪽으로 이동
            if (y < horizontal - 1 && map[x][y] > map[x][y + 1]) {
                memoization[x][y] += findWay(x, y + 1);
            }
        }
        return memoization[x][y]; // 이미 와봤던 경로라면 계산된 경로의 수를 return

    }
}
