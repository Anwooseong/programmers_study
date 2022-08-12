package week2;

import java.util.*;


//단지번호붙이기
//https://www.acmicpc.net/problem/2667
public class Backjoon2667 {

    static int[][] complex;
    static boolean[][] ch;
    static int N;
    int[] dirX = {0, 0, 1, -1};
    int[] dirY = {1, -1, 0, 0};

    public int BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        ch[x][y] = true;
        int sum = 1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dirX[i];
                int nextY = currentY + dirY[i];
                if (nextX < N && nextY < N && nextX >= 0 && nextY >= 0 && !ch[nextX][nextY] && complex[nextX][nextY] == 1) {
                    sum++;
                    ch[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        Backjoon2667 T = new Backjoon2667();
        Scanner kb = new Scanner(System.in);
        N = kb.nextInt();
        complex = new int[N][N];
        ch = new boolean[N][N];
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String row = kb.next();
            for (int j = 0; j < row.length(); j++) {
                complex[i][j] = Character.getNumericValue(row.charAt(j));
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (complex[i][j] == 1 && !ch[i][j]) {
                    answer.add(T.BFS(i, j));
                }
            }
        }
        Collections.sort(answer);
        System.out.println(answer.size());
        for (Integer integer : answer) {
            System.out.println(integer);
        }
    }
}
