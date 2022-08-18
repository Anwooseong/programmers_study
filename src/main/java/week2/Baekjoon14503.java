package week2;

import java.util.Scanner;

public class Baekjoon14503 {
    static int[] dirX = {-1, 0, 1, 0};
    static int[] dirY = {0, 1, 0, -1};
    static int[][] region, ch;
    static int answer, N, M;


    public void DFS(int r, int c, int d) {
        if (ch[r][c] == 0 && region[r][c] == 0) {
            ch[r][c] = 1; //청소했다는 의미
            answer++;
        }
        int cnt = 0;
        int nextDirection = d;
        while (cnt != 4) {

            if (nextDirection == 0) nextDirection = 3;
            else if (nextDirection == 1) nextDirection = 0;
            else if (nextDirection == 2) nextDirection = 1;
            else if (nextDirection == 3) nextDirection = 2;

            if ((r + dirX[nextDirection]) > 0 && (r + dirX[nextDirection]) < N && (c + dirY[nextDirection]) > 0 && (c + dirY[nextDirection]) < M) {
                if (region[(r + dirX[nextDirection])][(c + dirY[nextDirection])] == 0 && ch[(r + dirX[nextDirection])][(c + dirY[nextDirection])] == 0) {
                    DFS((r + dirX[nextDirection]), (c + dirY[nextDirection]), nextDirection);
                    break;
                }
            }
            cnt++;
        }
        if (cnt == 4) {

            if (nextDirection == 0) nextDirection = 2;
            else if (nextDirection == 1) nextDirection = 3;
            else if (nextDirection == 2) nextDirection = 0;
            else if (nextDirection == 3) nextDirection = 1;

            if ((r + dirX[nextDirection]) > 0 && (r + dirX[nextDirection]) < N && (c + dirY[nextDirection]) > 0 && (c + dirY[nextDirection]) < M) {
                if (region[(r + dirX[nextDirection])][(c + dirY[nextDirection])] == 0) {
                    DFS((r + dirX[nextDirection]), (c + dirY[nextDirection]), d);
                }
            }
        }
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
        ch = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                region[i][j] = kb.nextInt();
            }
        }
        T.DFS(r, c, d);
        System.out.println(answer);
    }
}
