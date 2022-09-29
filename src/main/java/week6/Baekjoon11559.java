package week6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Baekjoon11559 {
    static char[][] field;
    static int[] dirX = {-1, 1, 0, 0};
    static int[] dirY = {0, 0, -1, 1};
    static boolean[][] visited;

    public Queue<Position> isCheck(int x, int y) {

        Queue<Position> queue = new LinkedList<>();
        Queue<Position> returnQueue = new LinkedList<>();
        queue.offer(new Position(x, y));
        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            returnQueue.offer(cur);
            visited[cur.x][cur.y] = true;
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dirX[i];
                int nextY = cur.y + dirY[i];
                if (nextX >= 0 && nextY >= 0 && nextX < 12 && nextY < 6) {
                    if (!visited[nextX][nextY] && field[cur.x][cur.y] == field[nextX][nextY]) {
                        queue.offer(new Position(nextX, nextY));
                    }
                }
            }
        }
        return returnQueue;
    }

    public void bombField(Queue<Position> queue) {
        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            field[cur.x][cur.y] = '.';
        }
    }

    private static boolean searchBombField(Baekjoon11559 T) {
        boolean flag = false;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (field[i][j] != '.') {
                    Queue<Position> queue = T.isCheck(i, j);
                    if (queue.size() >= 4) {
                        flag = true;
                        T.bombField(queue); //없애기
                    } else {
                        queue.remove();
                    }
                }
            }
        }
        if (flag) return true;
        return false;
    }

    public static void main(String[] args) {
        Baekjoon11559 T = new Baekjoon11559();
        int answer = 0;
        field = new char[12][6];
        Scanner kb = new Scanner(System.in);
        for (int i = 0; i < 12; i++) {
            String str = kb.nextLine();
            for (int j = 0; j < 6; j++) {
                field[i][j] = str.charAt(j);
            }
        }

        while (true) {
            visited = new boolean[12][6];
            boolean flag = searchBombField(T);
            if (!flag) break;
            Queue<Character> queue = new LinkedList<>();
            for (int j = 5; j >= 0; j--) {
                for (int i = 11; i >= 0; i--) {
                    if (field[i][j] == '.')continue;
                    queue.offer(field[i][j]);
                    field[i][j] = '.';
                }
                int refreshRow = 11;
                while (!queue.isEmpty()) {
                    field[refreshRow][j] = queue.poll();
                    refreshRow--;
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}
