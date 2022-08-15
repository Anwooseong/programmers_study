package week2;

import java.util.ArrayList;
import java.util.Scanner;

//쇠막대기
//https://www.acmicpc.net/problem/10799
class IronStick {
    public int start;
    public int end;
    public IronStick(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class Baekjoon10799 {

    static ArrayList<Integer> laserStarts = new ArrayList<>();
    static ArrayList<Integer> ironStarts = new ArrayList<>();
    static ArrayList<IronStick> ironSticks = new ArrayList<>();

    public int solution(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                if (str.charAt(i + 1) == ')') laserStarts.add(i);
                else ironStarts.add(i);
            } else if (str.charAt(i) == ')') {
                if (str.charAt(i - 1) == ')') {
                    Integer ironStartPoint = ironStarts.remove(ironStarts.size() - 1);
                    ironSticks.add(new IronStick(ironStartPoint, i));
                }
            }
        }

        int answer = 0;
        int count;
        for (IronStick ironStick : ironSticks) {
            count = 0;
            for (Integer laserStart : laserStarts) {
                if (laserStart > ironStick.start && laserStart < ironStick.end) {
                    count++;
                }
            }
            answer += (count + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        Baekjoon10799 T = new Baekjoon10799();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(T.solution(str));
    }
}
