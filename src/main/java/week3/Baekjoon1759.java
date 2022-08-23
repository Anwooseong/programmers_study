package week3;

import java.util.Arrays;
import java.util.Scanner;

//암호 만들기
//https://www.acmicpc.net/problem/1759
public class Baekjoon1759 {

    static char[] passChars, answer;
    static int L, C;

    public void DFS(int level, int s, int consonantCount, int vowelCount) {
        if (level == L && consonantCount >= 2 && vowelCount >= 1) {
            for (char c : answer) {
                System.out.print(c);
            }
            System.out.println();
        } else if (level != L) {
            if (s < C) {
                if (passChars[s] == 'a' || passChars[s] == 'e' || passChars[s] == 'i' || passChars[s] == 'o' || passChars[s] == 'u') { //모음이라면
                    answer[level] = passChars[s];
                    DFS(level + 1, s + 1, consonantCount, vowelCount + 1);
                    DFS(level, s + 1, consonantCount, vowelCount);
                } else { //자음이라면
                    answer[level] = passChars[s];
                    DFS(level + 1, s + 1, consonantCount + 1, vowelCount);
                    DFS(level, s + 1, consonantCount, vowelCount);
                }
            }
        }
    }

    public static void main(String[] args) {
        Baekjoon1759 T = new Baekjoon1759();
        Scanner kb = new Scanner(System.in);
        L = kb.nextInt();
        C = kb.nextInt();
        passChars = new char[C];
        answer = new char[L];
        for (int i = 0; i < C; i++) {
            passChars[i] = kb.next().charAt(0);
        }
        Arrays.sort(passChars);
        T.DFS(0, 0, 0, 0);
    }
}
