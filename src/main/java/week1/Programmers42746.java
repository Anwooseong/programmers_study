package week1;

import java.util.*;

//가장 큰 수
//https://school.programmers.co.kr/learn/courses/30/lessons/42746
public class Programmers42746 {

    public String solution(int[] numbers) {
        String answer = "";

        String[] numberString = new String[numbers.length];
        int breakNumber = 0;

        for (int i = 0; i < numbers.length; i++) {
            breakNumber += numbers[i];
            numberString[i] = Integer.toString(numbers[i]);
        }

        if (breakNumber == 0) {
            return "0";
        }

        Arrays.sort(numberString, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return (t1+s).compareTo(s+t1);
            }
        });

        for (String s : numberString) {
            answer += s;
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers42746 T = new Programmers42746();
        int[] numbers = {6, 10, 2};
        System.out.println(T.solution(numbers));
    }

}

