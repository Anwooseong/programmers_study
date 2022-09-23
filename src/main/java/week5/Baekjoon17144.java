package week5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


//T는 0보다 크기때문에 0초일때 고려하지않는다.
class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}



public class Baekjoon17144 {
    static int R, C, T;
    static ArrayList<Integer> cleaner;

    static int[] dirX = {-1, 1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    public void refreshDust(int[][] room, Queue<Point> queue) {
        int[][] newRoom = new int[R][C];
        Queue<Point> newQueue = new LinkedList<>();
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int count = 0;
            int spreadDust = room[cur.x][cur.y] / 5;
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dirX[i];
                int nextY = cur.y + dirY[i];
                if (isCheck(nextX, nextY)) {  //이동가능
                    count++;
                    newRoom[nextX][nextY] += spreadDust;
                }
            }
            newRoom[cur.x][cur.y] += room[cur.x][cur.y] - (spreadDust * count);
        }

        //미세먼지 새로고침
        for (int[] ints : newRoom) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }

        //TODO 공기청정기 실행시 이동
        //TODO T 하나 줄이기 (T가 0이면 미세먼지 총량 출력)
    }

    public static void main(String[] args) {
        Baekjoon17144 D = new Baekjoon17144();
        Scanner kb = new Scanner(System.in);
        R = kb.nextInt();
        C = kb.nextInt();
        T = kb.nextInt();
        int[][] room = new int[R][C];
        cleaner = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                room[i][j] = kb.nextInt();
                if (room[i][j] == -1) cleaner.add(i);
                else if (room[i][j] != 0) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        D.refreshDust(room, queue);

    }

    public boolean isCheck(int x, int y) {
        if (y == 0 && (x == cleaner.get(0) || x == cleaner.get(1))) return false;
        if (x < R && x > -1 && y < C && y > -1) return true;
        return false;
    }
}
