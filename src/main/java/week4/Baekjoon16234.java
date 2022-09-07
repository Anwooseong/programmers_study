package week4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point{
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Baekjoon16234 {

    static int N, L, R;
    static int[][] countries;
    int[] dirX = {0, 0, -1, 1};
    int[] dirY = {1, -1, 0, 0};
    static int answer = 0;

    public void BFS(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> arrayList = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        int unionPeople = 0;

        Point point = new Point(x, y);
        queue.offer(point);
        arrayList.add(point);
        visited[x][y] = true;
        boolean flag = false;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int currentX = current.x;
            int currentY = current.y;
            unionPeople += countries[currentX][currentY];

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dirX[i];
                int nextY = currentY + dirY[i];
                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N) {
                    if (!visited[nextX][nextY]) {
                        int gap = Math.abs(countries[currentX][currentY]-countries[nextX][nextY]);
                        if (gap >= L && gap <= R) {
                            flag = true;
                            Point unionAdd = new Point(nextX, nextY);
                            queue.offer(unionAdd);
                            arrayList.add(unionAdd);
                            visited[nextX][nextY] = true;
                        }
                    }
                }
            }
        }
        if (flag) {
            int people = unionPeople / arrayList.size();
            for (Point region : arrayList) {
                countries[region.x][region.y] = people;
            }
            answer++;
        }

    }

    public static void main(String[] args) {
        Baekjoon16234 T = new Baekjoon16234();
        Scanner kb = new Scanner(System.in);
        N = kb.nextInt();
        L = kb.nextInt();
        R = kb.nextInt();
        countries = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                countries[i][j] = kb.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                T.BFS(i, j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(countries[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(answer);
    }
}
