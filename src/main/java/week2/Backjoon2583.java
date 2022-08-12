package week2;

import java.util.*;

//영역구하기
//https://www.acmicpc.net/problem/2583
public class Backjoon2583 {

    static int M, N, K;

    static int[][] gridPaper;
    int[] dirX = {0, 0, 1, -1};
    int[] dirY = {1, -1, 0, 0};

    public int BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        int sum = 1;
        gridPaper[x][y] = 1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dirX[i];
                int nextY = currentY + dirY[i];
                if (nextX >= 0 && nextX < M && nextY >= 0 && nextY < N && gridPaper[nextX][nextY] == 0) {
                    sum++;
                    gridPaper[nextX][nextY] = 1;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        Backjoon2583 T = new Backjoon2583();
        Scanner kb = new Scanner(System.in);
        ArrayList<Integer> answer = new ArrayList<>();
        M = kb.nextInt();
        N = kb.nextInt();
        K = kb.nextInt();
        gridPaper = new int[M][N];
        for (int i = 0; i < K; i++) { //x가 열이고 y가 행이된다.
            int x1 = kb.nextInt();
            int y1 = kb.nextInt();
            int x2 = kb.nextInt();
            int y2 = kb.nextInt();

            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    gridPaper[y][x] = 1;
                }
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (gridPaper[i][j] == 0) {
                    answer.add(T.BFS(i, j));
                }
            }
        }
        System.out.println(answer.size());
        Collections.sort(answer);
        for (Integer integer : answer) {
            System.out.print(integer + " ");
        }
    }
}
