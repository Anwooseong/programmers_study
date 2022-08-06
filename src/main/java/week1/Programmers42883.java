package week1;

import java.util.Stack;

//큰 수 만들기
//https://school.programmers.co.kr/learn/courses/30/lessons/42883
public class Programmers42883 {

    public String solution(String number, int k) {
        String answer = "";
        int count = k;
        char[] numberToString = number.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char c : numberToString) {
            if (stack.empty()) {
                stack.push(c);
            } else {
                while (!stack.empty() && count > 0 && stack.peek() < c) {
                    count--;
                    stack.pop();
                }
                stack.push(c);
            }
        }
        int i = 0;
        for (Character character : stack) {
            answer += character;
            i++;
            if ((number.length() - k) == i) {
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers42883 T = new Programmers42883();
        String num = "1924";
        int k = 2;
        System.out.println(T.solution(num, k));
    }
}
