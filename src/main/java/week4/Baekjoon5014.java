package week4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/5014
 * 스타트링크
 */
public class Baekjoon5014 {

    static int F, G, S, U, D;
    static int[] dp;

    public void solution(int cur, int count) {
        dp[cur] = count;
        if (cur == G) return;
        if (checkFloor(cur, U, count)) {
            solution(cur + U, count + 1);
        }
        if (checkFloor(cur, D, count)) {
            solution(cur + D, count + 1);
        }
    }

    public boolean checkFloor(int cur, int upDown, int count) {
        if (cur < 1 || cur > F) {
            return false;
        }
        if (cur + upDown < 1 || cur + upDown > F) return false;
        if (dp[cur + upDown] <= count + 1) return false;
        return true;
    }


    public static void main(String[] args) {
        Baekjoon5014 T = new Baekjoon5014();
        Scanner kb = new Scanner(System.in);
        F = kb.nextInt();
        S = kb.nextInt();
        G = kb.nextInt();
        U = kb.nextInt();
        D = -kb.nextInt();
        dp = new int[F + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        T.solution(S, 0);
        if (dp[G] == Integer.MAX_VALUE) {
            System.out.println("use the stairs");
        } else {
            System.out.println(dp[G]);
        }
    }

}
