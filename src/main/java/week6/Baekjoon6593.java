package week6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//0 0 0 = 끝
//BFS
class Point {
    public int x;
    public int y;
    public int z;

    public Point(int z, int x, int y) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
public class Baekjoon6593 {
    static int L, R, C;
    static char[][][] building;
    static boolean[][][] visited;
    static int[] dirX = {-1, 1, 0, 0};
    static int[] dirY = {0, 0, -1, 1};
    static int[] dirZ = {-1, 1};
    static int answer = 0;

    public void solution(Queue<Point> queue) {
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (building[cur.z][cur.x][cur.y] == 'E') break;
            answer++;
            for (int i = 0; i < 4; i++) { //한 층 이동
                int nextX = cur.x + dirX[i];
                int nextY = cur.y + dirY[i];
                if (nextX >= 0 && nextY >= 0 && nextX < R && nextY < C) {
                    if (building[cur.z][nextX][nextY] !='#' &&!visited[cur.z][nextX][nextY]) {
                        queue.offer(new Point(cur.z, nextX, nextY));
                        visited[cur.z][nextX][nextY] = true;
                    }
                }
            }
            for (int i = 0; i < 2; i++) {
                int nextZ = cur.z + dirZ[i];
                if (nextZ >= 0 && nextZ < L) {
                    if (building[nextZ][cur.x][cur.y] != '#' && !visited[nextZ][cur.x][cur.y]) {
                        queue.offer(new Point(nextZ, cur.x, cur.y));
                        visited[nextZ][cur.x][cur.y] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Baekjoon6593 T = new Baekjoon6593();
        Scanner kb = new Scanner(System.in);
        L = kb.nextInt();
        R = kb.nextInt();
        C = kb.nextInt();
        building = new char[L][R][C];
        visited = new boolean[L][R][C];
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                String str = kb.next();
                for (int k = 0; k < C; k++) {
                    building[i][j][k] = str.charAt(k);
                    if (building[i][j][k] == 'S') {
                        queue.offer(new Point(i, j, k));
                        visited[i][j][k] = true;
                    }
                }
            }
            kb.nextLine();
        }
        //TODO 이동하기 메서드 실행
        T.solution(queue);
        for (boolean[][] booleans : visited) {
            for (boolean[] aBoolean : booleans) {
                for (boolean b : aBoolean) {
                    System.out.print(b+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println(answer);
//        for (char[][] chars : building) {
//            for (char[] aChar : chars) {
//                for (char c : aChar) {
//                    System.out.print(c +" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }//end of for
    }
}
