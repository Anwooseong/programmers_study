package week3;

import java.util.*;

public class Baekjoon17140 {

    public void solution(int[][] array, String rowOrColumn) {
        if (rowOrColumn.equals("column")) {
            //배열이름.length 행길이
            //배열이름[열].length 각행의 열길이
            int maxColumn = 0;
            for (int i = 0; i < array.length; i++) {
                int columnCount = 0;
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = 0; j < array[i].length; j++) {
                    int key = array[i][j];
                    if (!map.containsKey(key)) columnCount++;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
                maxColumn = Math.max(maxColumn, columnCount);  //최대 열 크기로 새로운 array 만들기
                List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());
                entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        return o2.getValue() - o1.getValue();
                    }
                });
                //TODO 새로운 array 만들어서 값넣고 기존 행 길이와 새로운 열 길이 중에 무엇이 큰 지 판발하여 무슨 연산실행할 지 그리고, 카운트 증가하기

                for (Map.Entry<Integer, Integer> entry : entryList) {  //값 잘 들어오는 거 확인했음
                    System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
                }
            }
            if (array.length <= maxColumn) { //열계산

            } else { //행계

            }

        } else { //rowOrColumn.equals("row")

        }
    }

    public static void main(String[] args) {
        Baekjoon17140 T = new Baekjoon17140();
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
        T.solution(array, "column");
    }
}
