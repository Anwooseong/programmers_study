package week5;

import java.util.Scanner;

public class Baekjoon15684 {

    static int N, M, H;
    static int end;
    static int[][] ladder;
    static int[] combi;

    public void combination(int L, int s) {
        if (L == end) {
            for (int i : combi) {
                System.out.print(i+" ");
            }
            System.out.println();
        } else {
            for (int i = s; i < N*H ; i++) { //N=5 H=6
                int x = i / N + 1;
                int y = i % N + 1;
                if (ladder[x][y] == 1) continue;
                combi[L] = i;
                combination(L + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        Baekjoon15684 T = new Baekjoon15684();
        Scanner kb = new Scanner(System.in);
        N = kb.nextInt(); //열
        M = kb.nextInt();
        H = kb.nextInt(); //행
        ladder = new int[H + 1][N + 1];
        combi = new int[3];

        for (int i = 0; i < H; i++) {
            int x = kb.nextInt();
            int y = kb.nextInt();
            ladder[y][x] = 1;
        }

        //뽑는 값 조절
        for (int i = 0; i < 4; i++) {
            end = i;
            T.combination(0, 1);
        }

    }
}
