package week3;

import java.util.Scanner;
import java.util.Stack;

public class Baekjoon9935 {
    public String solution(String arr, String bomb) {
        Stack<String> stack = new Stack<>();
        char[] charArr = arr.toCharArray();
        char[] charBomb = bomb.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            stack.push(String.valueOf(charArr[i]));
            int size = charBomb.length - 1;
            String imsi = "";
            while (String.valueOf(charBomb[size]).equals(stack.peek()) && size > -1) {
                imsi += stack.pop();
                size--;
            }
            System.out.println(imsi);
            boolean flag = true;
            if (imsi.equals(bomb)) {
                flag = false;
            }
            if (flag) {
                stack.push(imsi);
            }
        }
        String answer = "";
        for (String s : stack) {
            answer += s;
        }
        if (answer.length() == 0) {
            return "FRULA";
        }
        return answer;
    }

    public static void main(String[] args) {
        Baekjoon9935 T = new Baekjoon9935();
        Scanner kb = new Scanner(System.in);
        String arr = kb.nextLine();
        String bomb = kb.nextLine();
        System.out.println(T.solution(arr, bomb));
    }
}
