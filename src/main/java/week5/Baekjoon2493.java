package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//메모리초과로 인해 인스턴트 객체를 생성하지 않으려고 헀다.
public class Baekjoon2493 {

    static int N;
    static int[] towers;
    static Stack<Integer> stack;

    public void solution() {
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && towers[stack.peek()] <= towers[i]) {
                stack.pop();
            }
            if (stack.size() == 0) {
                System.out.print(0 + " ");
            } else {
                System.out.print((stack.peek()+ 1)+" ");
            }
            stack.push(i);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        Baekjoon2493 T = new Baekjoon2493();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stack = new Stack<>();
        towers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }
        T.solution();
    }
}
