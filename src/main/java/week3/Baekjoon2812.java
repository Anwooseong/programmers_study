package week3;

import java.util.Scanner;
import java.util.Stack;

public class Baekjoon2812 {

    public String solution(String number, int k) {
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
        StringBuilder answer = new StringBuilder();
        for (Character character : stack) {
            answer.append(character);
            i++;
            if ((number.length() - k) == i) {
                break;
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Baekjoon2812 T = new Baekjoon2812();
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();
        int K = kb.nextInt();
        String num = kb.next();
        System.out.println(T.solution(num, K));
    }
}
