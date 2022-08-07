package week1;

import java.util.Stack;

//올바른 괄호
//https://school.programmers.co.kr/learn/courses/30/lessons/12909
public class Programmers12909 {

    boolean solution(String s) {
        boolean answer = true;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        if (chars[0] == ')') return false;
        stack.push('(');
        for (int i = 1; i < s.length(); i++) {
            if (stack.empty() && chars[i] == ')') return false;
            if (chars[i] == '(') {
                stack.push('(');
            } else {
                stack.pop();
            }
        }
        if (!stack.empty()) return false;

        return answer;
    }

    public static void main(String[] args) {
        Programmers12909 T = new Programmers12909();
        String s = "(()(";
        System.out.println(T.solution(s));
    }
}
