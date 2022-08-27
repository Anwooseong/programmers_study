package week3;

import java.util.*;

//이차원 배열과 연산
//https://www.acmicpc.net/problem/17140
public class Baekjoon17140 {

    static int[][] array = new int[100][100];
    static int r, c, k;

    public void solution(String rowOrColumn, int row, int col, int sec) {
        if (array[r][c] == k) {
            System.out.println(sec);
            return;
        }
        if (sec == 101) {
            System.out.println("-1");
            return;
        }
        if (rowOrColumn.equals("row")) {
            int rowIndex = 0, maxColumn = 0;
            for (int i = 0; i < row; i++) {
                int columIndex = 0, columnCount = 0;
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = 0; j < col; j++) {
                    if (columnCount == 100) break; //실시간으로 100개면 반복분 종료
                    if (array[i][j] == 0) continue;
                    int key = array[i][j];
                    array[i][j] = 0;
                    if (!map.containsKey(key)) columnCount += 2;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                } //end of 각 행 계산
                maxColumn = Math.max(maxColumn, columnCount);  //최대 열 크기
                List<Map.Entry<Integer, Integer>> entryList = sortedMapKey(map);
                sortedMapValue(entryList);
                for (Map.Entry<Integer, Integer> integerIntegerEntry : entryList) {
                    if (columIndex == 100) break;
                    array[rowIndex][columIndex++] = integerIntegerEntry.getKey();
                    array[rowIndex][columIndex++] = integerIntegerEntry.getValue();
                }
                rowIndex++;
            }
            if (row >= maxColumn) {
                solution("row", row, maxColumn, sec + 1);
            } else {
                solution("column", row, maxColumn, sec + 1);
            }
        }//end of 최종 행계산

        else if (rowOrColumn.equals("column")) {////////////////////////////////////////////
            int columnIndex = 0, maxRow = 0;
            for (int i = 0; i < col; i++) {
                int rowIndex = 0, rowCount = 0;
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = 0; j < row; j++) {
                    if (rowCount == 100) break; //실시간으로 100개면 반복문 종료
                    if (array[j][i] == 0) continue;
                    int key = array[j][i];
                    array[j][i] = 0;
                    if (!map.containsKey(key)) rowCount += 2;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                } //end of 각 열 계산
                maxRow = Math.max(maxRow, rowCount);  //최대 열 크기
                List<Map.Entry<Integer, Integer>> entryList = sortedMapKey(map);
                sortedMapValue(entryList);
                for (Map.Entry<Integer, Integer> integerIntegerEntry : entryList) {
                    if (rowIndex == 100) break;
                    array[rowIndex++][columnIndex] = integerIntegerEntry.getKey();
                    array[rowIndex++][columnIndex] = integerIntegerEntry.getValue();
                }
                columnIndex++;
            }
            if (maxRow >= col) {
                solution("row", maxRow, col, sec + 1);
            } else {
                solution("column", maxRow, col, sec + 1);
            }
        }//end of 최종 열계산
    }

    private void sortedMapValue(List<Map.Entry<Integer, Integer>> entryList) {
        entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
    }

    private List<Map.Entry<Integer, Integer>> sortedMapKey(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        });
        return entryList;
    }

    public static void main(String[] args) {
        Baekjoon17140 T = new Baekjoon17140();
        Scanner kb = new Scanner(System.in);
        r = kb.nextInt() - 1;
        c = kb.nextInt() - 1;
        k = kb.nextInt();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = kb.nextInt();
            }
        }
        T.solution("row", 3, 3, 0);
    }
}