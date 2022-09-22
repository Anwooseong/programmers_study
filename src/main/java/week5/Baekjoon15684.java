package week5;

import java.util.Scanner;

public class Baekjoon15684 {

    static int N, M, H;
    static int end;
    static int[][] ladder;
    static int[] combi;
    static boolean gameSuccessFlag = true;
    static boolean finishFlag = false;


    public void game(int x, int y, int finishNum, int startNum) {
        if (x == (H + 1)) {
            if (startNum != finishNum) {
                gameSuccessFlag = false;  //당장 break
            }
            else gameSuccessFlag = true;  //계속 참이면 결과출력
        }else {
            if (ladder[x][y] == 1) {  //오른쪽아래
                game(x + 1, y + 1, finishNum + 1, startNum);
            } else if (ladder[x][y - 1] == 1) {  //왼쪽아래
                game(x + 1, y - 1, finishNum - 1, startNum);
            } else if (ladder[x][y] == 0 && ladder[x][y - 1] == 0) {  //아래
                game(x + 1, y, finishNum, startNum);
            }
        }
    }


    public void refreshLadder(int size) {

        //조합으로 뽑을 사다리 새로고침
        for (int i = 0; i < size; i++) {
            int x = combi[i] / N + 1;
            int y = combi[i] % N + 1;
            ladder[x][y] = 1;
        }

        //사다리 타기 시작
        for (int startNum = 1; startNum < N + 1; startNum++) {
            game(1, startNum, startNum, startNum);
            if (!gameSuccessFlag) break;
        }


        //사다리 타기 전부 성공
        if (gameSuccessFlag) {
            System.out.println(size);       //가장 적게 뽑는거부터해서 바로 끝남
            finishFlag = true;              //조합 그만 뽑기
            return;                         //돌려놓지도 않고 그냥 종료(시간단축)
        }

        //사다리 다시 원래대로 돌려놓기
        for (int i = 0; i < size; i++) {
            int x = combi[i] / N + 1;
            int y = combi[i] % N + 1;
            ladder[x][y] = 0;
        }
    }

    public void combination(int L, int s) {
        if (finishFlag) return;   //조합뽑기 그만두기
        if (L == end) {
            refreshLadder(end);  //사다리 새로고침후 타기
        } else {

            for (int i = s; i < N*H ; i++) {  //이차원을 일차원으로 생각
                int x = i / N + 1;
                int y = i % N + 1;
                if (isLadderOnOff(x, y)) continue;  //조합 뽑기 제외
                combi[L] = i;
                combination(L + 1, i + 1);

            }
        }
    }

    public boolean isLadderOnOff(int x, int y) { //사다리 놓는 조건
        if (ladder[x][y] == 1 || ladder[x][y - 1] == 1) { //그 자리와 왼쪽
            return true;
        }
        if (y == N) return true;     //마지막은 놓을 수 없음
        if (ladder[x][y + 1] == 1) {  //오른쪽
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
        T.refreshLadder(end);  //사다리타기

        //뽑는 값 조절(1개부터 3개)
        for (int i = 1; i < 4; i++) {
            end = i;
            T.combination(0, 0);
        }

        if (!finishFlag) System.out.println(-1);
    }
}
