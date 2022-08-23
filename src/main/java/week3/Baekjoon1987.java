package week3;

import java.util.Scanner;

//알파벳
//https://www.acmicpc.net/problem/1987
public class Baekjoon1987 {
    static int R, C;
    static int[][] board;
    static int[] alphabet = new int[26];
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    static int answer = 0;

    public void DFS(int index, int x, int y, int cnt) {

        alphabet[index] = 1;
        answer = Math.max(answer, cnt + 1);

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];
            if (nextX >= 0 && nextY >= 0 && nextX < R && nextY < C && alphabet[board[nextX][nextY]] == 0) {
                DFS(board[nextX][nextY], nextX, nextY, cnt + 1);
                alphabet[board[nextX][nextY]] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Baekjoon1987 T = new Baekjoon1987();
        Scanner kb = new Scanner(System.in);
        R = kb.nextInt();
        C = kb.nextInt();
        kb.nextLine();
        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = kb.nextLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j) - 'A';
            }
        }

        T.DFS(board[0][0], 0, 0, 0);
        System.out.println(answer);
    }
}
