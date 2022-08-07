package week1;

import java.util.Stack;

//주식가격
//https://school.programmers.co.kr/learn/courses/30/lessons/42584
class StackNum {
    public int price;
    public int second;

    public StackNum(int price,  int second) {
        this.price = price;
        this.second = second;
    }
}
public class Programmers42584 {

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<StackNum> stack = new Stack<>();

        stack.push(new StackNum(prices[prices.length - 1],  0));
        answer[prices.length - 1] = 0;

        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] > stack.peek().price) {
                stack.push(new StackNum(prices[i], 1));
                answer[i] = 1;
                continue;
            }

            int second = 1;
            while (!stack.empty() && prices[i] <= stack.peek().price) {
                StackNum pop = stack.pop();
                second += pop.second;
            }
            stack.push(new StackNum(prices[i], second));
            answer[i] = second;
        }

        return answer;
    }

    public static void main(String[] args) {
        Programmers42584 T = new Programmers42584();
        int[] prices = {1, 2, 3, 2, 3};
        for (int i : T.solution(prices)) {
            System.out.println("i = " + i);
        }
    }
}
