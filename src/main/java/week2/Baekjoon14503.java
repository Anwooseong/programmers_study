package week2;

import java.util.Scanner;

public class Baekjoon14503 {
    static int[] dirX = {-1, 0, 1, 0};
    static int[] dirY = {0, 1, 0, -1};
    static int[][] region;
    static int answer, N, M;


    public void DFS(int r, int c, int d) {
        if (region[r][c] == 0){
            region[r][c] = 2; //청소했다는 의미
            answer++;
        }
        int cnt = 0;
        int nextDirection = d;
        while (cnt != 4) {
            nextDirection = directionClean(nextDirection);
            int nextRow = r + dirX[nextDirection];
            int nextColumn = c + dirY[nextDirection];
            if (nextRow > 0 && nextRow < N && nextColumn > 0 && nextColumn < M) {
                if (region[nextRow][nextColumn] == 0) {
                    DFS(nextRow, nextColumn, nextDirection);
                    break;
                }
            }
            cnt++;
        }
        if (cnt == 4) {
            nextDirection = directionBack(d);
            int nextRow = r + dirX[nextDirection];
            int nextColumn = c + dirY[nextDirection];
            if (nextRow > 0 && nextRow < N && nextColumn > 0 && nextColumn < M) {
                if (region[nextRow][nextColumn] != 1) {
                    DFS(nextRow, nextColumn, d);
                }
            }
        }
    }

    public int directionBack(int dir) {
        if (dir == 0) return 2;
        else if (dir == 1) return 3;
        else if (dir == 2) return 0;
        else return 1;
    }
    public int directionClean(int dir) {
        if (dir == 0) return 3;
        else if (dir == 1) return 0;
        else if (dir == 2) return 1;
        else return 2;
    }

    public static void main(String[] args) {
        Baekjoon14503 T = new Baekjoon14503();
        Scanner kb = new Scanner(System.in);
        N = kb.nextInt();
        M = kb.nextInt();
        int r = kb.nextInt();
        int c = kb.nextInt();
        int d = kb.nextInt();
        region = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                region[i][j] = kb.nextInt();
            }
        }
        T.DFS(r, c, d);
        System.out.println(answer);
    }
}
