package week2;

import java.util.Scanner;
import java.util.Stack;

//괄호
//https://www.acmicpc.net/problem/9012
public class Baekjoon9012 {

    public String solution(String testCase) {
        Stack<Character> stack = new Stack<>();
        if (testCase.charAt(0) == ')') {
            return "NO";
        } else {
            stack.push('(');
            for (int i = 1; i < testCase.length(); i++) {
                if (testCase.charAt(i) == '(') {
                    stack.push('(');
                } else {
                    if (stack.isEmpty()) {
                        return "NO";
                    } else {
                        stack.pop();
                    }
                }
            }
            if (stack.isEmpty()) return "YES";
            else return "NO";
        }
    }

    public static void main(String[] args) {
        Baekjoon9012 M = new Baekjoon9012();
        Scanner kb = new Scanner(System.in);
        int T = kb.nextInt();
        for (int i = 0; i < T; i++) {
            String testCase = kb.next();
            System.out.println(M.solution(testCase));
        }

    }
}
