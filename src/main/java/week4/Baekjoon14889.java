package week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon14889 {
    //해결방법
    //1. size C size/2 조합으로 멤버 먼저 뽑기
    //2. 최소인거 찾기
    static int n, m;
    static int[][] memberMatrix;
    static int[] memberNumber;
    static int[] startTeam;
    static int gap;

    public static void DFS(int L, int s) {
        if (L == m) {
            solution();
        } else {
            for (int i = s; i < n; i++) {
                startTeam[L] = i;
                DFS(L + 1, i + 1);
            }
        }
    }

    public static void solution() {
        boolean[] visited = new boolean[n];
        int startTeamScore = 0;
        int linkTeamScore = 0;

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                startTeamScore += (memberMatrix[startTeam[i]][startTeam[j]] + memberMatrix[startTeam[j]][startTeam[i]]);
                visited[startTeam[i]] = true;
                visited[startTeam[j]] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && i != j) {
                    linkTeamScore += (memberMatrix[i][j]);
                }
            }
        }
        gap = Math.min(gap, Math.abs(startTeamScore - linkTeamScore));
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = n/2;
        memberMatrix = new int[n][n];
        memberNumber = new int[n];
        startTeam = new int[m];
        gap = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            memberNumber[i] = i;
            for (int j = 0; j < n; j++) {
                memberMatrix[i][j] = kb.nextInt();
            }
        }
        DFS(0, 0);
        System.out.println(gap);
    }
}