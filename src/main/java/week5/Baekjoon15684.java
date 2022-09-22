package week5;

import java.util.Scanner;

public class Baekjoon15684 {

    static int N, M, H;
    static int end;
    static int[][] ladder;
    static int[] combi;
    static boolean flag = true;
    static boolean finishFlag = false;


    public void game(int x, int y, int finishNum, int startNum) {
        if (x == (H + 1)) {
            if (startNum != finishNum) {
                flag = false;  //당장 break
            }
            else flag = true;  //계속 참이면 결과출력
        }else {
            if (ladder[x][y] == 1) {
                game(x + 1, y + 1, finishNum + 1, startNum);
            } else if (ladder[x][y - 1] == 1) {
                game(x + 1, y - 1, finishNum - 1, startNum);
            } else if (ladder[x][y] == 0 && ladder[x][y - 1] == 0) {
                game(x + 1, y, finishNum, startNum);
            }
        }
    }


    public void refreshLadder(int size) {
        for (int i = 0; i < size; i++) {
            int x = combi[i] / N + 1;
            int y = combi[i] % N + 1;
            ladder[x][y] = 1;
        }

        int startNum;
        for (startNum = 1; startNum < N + 1; startNum++) {
            game(1, startNum, startNum, startNum);
            if (!flag) break;
        }


        //사다리 타기 전부 성공
        if (flag) {
            System.out.println(size);
            finishFlag = true;
        }


        for (int i = 0; i < size; i++) {
            int x = combi[i] / N + 1;
            int y = combi[i] % N + 1;
            ladder[x][y] = 0;
        }
    }

    public void combination(int L, int s) {
        if (finishFlag) return;
        if (L == end) {
            refreshLadder(end);
        } else {

            for (int i = s; i < N*H ; i++) { //N=5 H=6
                int x = i / N + 1;
                int y = i % N + 1;
                if (isLadderOnOff(x, y)) continue;
                combi[L] = i;
                combination(L + 1, i + 1);
            }
        }
    }

    public boolean isLadderOnOff(int x, int y) { //사다리 놓는 조건
        if (ladder[x][y] == 1 || ladder[x][y - 1] == 1) { //그 자리와 왼쪽
            return true;
        }
        if (y == N) return true;
        if (ladder[x][y + 1] == 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Baekjoon15684 T = new Baekjoon15684();
        Scanner kb = new Scanner(System.in);
        N = kb.nextInt(); //열
        M = kb.nextInt();
        H = kb.nextInt(); //행
        ladder = new int[H + 1][N + 1];
        combi = new int[3];

        for (int i = 0; i < M; i++) {
            int x = kb.nextInt();
            int y = kb.nextInt();
            ladder[x][y] = 1;
        }

        //0개 뽑을 때
        end = 0;
        T.refreshLadder(end);

        //뽑는 값 조절(1개부터 3개)
        for (int i = 1; i < 4; i++) {
            end = i;
            T.combination(0, 0);
        }

        if (!finishFlag) System.out.println(-1);
    }
}
