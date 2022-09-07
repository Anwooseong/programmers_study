package week4;

import java.util.ArrayList;
import java.util.Scanner;

class Region {
    public int x;
    public int y;

    public Region(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Baekjoon2580 {

    static int[][] sudoku = new int[9][9];
    static ArrayList<Region> arrayList;
    static boolean flag;
    public void solution(int level) {
        if (flag) return;
        if (level == arrayList.size()) {
            for (int[] ints : sudoku) {
                for (int anInt : ints) {
                    System.out.print(anInt+" ");
                }
                System.out.println();
            }
            flag = true;
            return;
        }

        boolean[] checkNumber = new boolean[10];

        Region region = arrayList.get(level);
        int row = region.x;
        int column = region.y;

        //네모칸 확인
        int startRow = row / 3 * 3;
        int startColumn = column / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startColumn; j < startColumn + 3; j++) {
                checkNumber[sudoku[i][j]] = true;
            }
        }

        //행 값 확인
        for (int i = 0; i < 9; i++) {
            checkNumber[sudoku[row][i]] = true;
        }

        //열 값 확인
        for (int i = 0; i < 9; i++) {
            checkNumber[sudoku[i][column]] = true;
        }

        for (int i = 1; i < 10; i++) {
            if (!checkNumber[i]) {
                sudoku[row][column] = i;
                solution(level + 1);
                sudoku[row][column] = 0;
                if (flag) return;
            }
        }
    }

    public static void main(String[] args) {
        Baekjoon2580 T = new Baekjoon2580();
        arrayList = new ArrayList<>();
        Scanner kb = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = kb.nextInt();
                if (sudoku[i][j] == 0) arrayList.add(new Region(i, j));
            }
        }
        T.solution(0);
    }

}
