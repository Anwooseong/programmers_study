package week3;

import java.util.Scanner;

public class Baekjoon17140 {

    public void solution(int[][] array, String rowOrColumn) {
        if (rowOrColumn.equals("column")) {
            //배열이름.length 행길이
            //배열이름[열].length 각행의 열길이
            for (int i = 0; i < array.length; i++) {

            }
        } else { //rowOrColumn.equlas("row")

        }
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int r = kb.nextInt();
        int c = kb.nextInt();
        int k = kb.nextInt();
        r = r - 1;
        c = c - 1;
        int[][] array = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = kb.nextInt();
            }
        }
    }
}
