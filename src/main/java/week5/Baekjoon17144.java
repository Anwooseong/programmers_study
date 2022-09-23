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
    static int R, C;
    static ArrayList<Integer> cleaner;

    static int[] dirX = {-1, 1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    public void refreshDust(int[][] room, Queue<Point> queue, int T) {
//        System.out.println("\nT값 : "+T);
        if (T == 0) {
            int sum = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sum += room[i][j];
                }
            }
            System.out.println(sum);
        } else {
            int[][] newRoom = new int[R][C];
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
                newRoom[cur.x][cur.y] += (room[cur.x][cur.y] - (spreadDust * count));
            }

            //미세먼지 새로고침
//            System.out.println("먼지 새로고침");
//            for (int[] ints : newRoom) {
//                for (int anInt : ints) {
//                    System.out.print(anInt+" ");
//                }
//                System.out.println();
//            }

            //공기청정기 실행시 이동
            //T 하나 줄이기 (T가 0이면 미세먼지 총량 출력)
            Queue<Point> newQueue = moveDust(newRoom);
            refreshDust(newRoom, newQueue, T - 1);
        }

    }

    public boolean isCheck(int x, int y) {
        if (y == 0 && (x == cleaner.get(0) || x == cleaner.get(1))) return false;
        if (x < R && x > -1 && y < C && y > -1) return true;
        return false;
    }

    public Queue<Point> moveDust(int[][] room) {
        int topCleaner = cleaner.get(0);
        int bottomClear = cleaner.get(1);
        Queue<Point> queue = new LinkedList<>();

        //topCleaner 기준 위로먼저
        for (int i = topCleaner - 1; i >= 1; i--) {
            room[i][0] = room[i - 1][0];
            if (room[i][0] != 0) queue.offer(new Point(i, 0));
            room[i - 1][0] = 0;
        }

        //topCleaner 최상단 왼쪽 오른쪽
        for (int i = 0; i < C - 1; i++) {
            room[0][i] = room[0][i + 1];
            if (room[0][i] != 0) queue.offer(new Point(0, i));
            room[0][i + 1] = 0;
        }

        //topCleaner 최상단 위 아래
        for (int i = 0; i < topCleaner; i++) {
            room[i][C - 1] = room[i + 1][C - 1];
            if (room[i][C - 1] != 0) queue.offer(new Point(i, C - 1));
            room[i + 1][C - 1] = 0;
        }

        //topCleaner 라인 오른쪽 왼쪽
        for (int i = C - 1; i >= 2; i--) {
            room[topCleaner][i] = room[topCleaner][i - 1];
            if (room[topCleaner][i] != 0) queue.offer(new Point(topCleaner, i));
            room[topCleaner][i - 1] = 0;
        }

        //bottomCleaner 기준 아래로 먼저
        for (int i = bottomClear + 1; i < R - 1; i++) {
            room[i][0] = room[i + 1][0];
            if (room[i][0] != 0) queue.offer(new Point(i, 0));
            room[i + 1][0] = 0;
        }

        //bottomCleaner 최하단 왼쪽 오른쪽
        for (int i = 0; i < C - 1; i++) {
            room[R - 1][i] = room[R - 1][i + 1];
            if (room[R - 1][i] != 0) queue.offer(new Point(R - 1, i));
            room[R - 1][i + 1] = 0;
        }

        //bottomCleaner 최하단 아래 위
        for (int i = R - 1; i >= bottomClear + 1; i--) {
            room[i][C - 1] = room[i - 1][C - 1];
            if (room[i][C - 1] != 0) queue.offer(new Point(i, C - 1));
            room[i - 1][C - 1] = 0;
        }

        //bottomCleaner 라인 오른쪽 왼쪽
        for (int i = C - 1; i >= 2; i--) {
            room[bottomClear][i] = room[bottomClear][i - 1];
            if (room[bottomClear][i] != 0) queue.offer(new Point(bottomClear, i));
            room[bottomClear][i - 1] = 0;
        }
        //순환되지않는거 큐에 넣기
        for (int i = 1; i < topCleaner; i++) {
            for (int j = 1; j < C - 1; j++) {
                if (room[i][j] != 0) queue.offer(new Point(i, j));
            }
        }

        for (int i = bottomClear + 1; i < R - 1; i++) {
            for (int j = 1; j < C - 1; j++) {
                if (room[i][j] != 0) queue.offer(new Point(i, j));
            }
        }

//        System.out.println();
//        System.out.println("미세먼지 이동");
//        for (int[] ints : room) {
//            for (int anInt : ints) {
//                System.out.print(anInt+" ");
//            }
//            System.out.println();
//        }

        return queue;
    }

    public static void main(String[] args) {
        Baekjoon17144 D = new Baekjoon17144();
        Scanner kb = new Scanner(System.in);
        R = kb.nextInt();
        C = kb.nextInt();
        int T = kb.nextInt();
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

        D.refreshDust(room, queue, T);
    }
}
