package week1;

//피로도
//https://school.programmers.co.kr/learn/courses/30/lessons/87946
public class Programmers87946 {
    static int answer;
    static int[] ch;
    public void DFS(int L,int[][] dungeons, int k, int ans) { //순열개념사용했음
        if (L == dungeons.length) {
            answer = Math.max(answer, ans);
        } else {
            for (int i = 0; i < dungeons.length; i++) {
                if (ch[i] == 0) {
                    ch[i] = 1;
                    if (k >= dungeons[i][0]) {
                        DFS(L + 1, dungeons, k - dungeons[i][1], ans + 1);
                    } else {
                        DFS(L + 1, dungeons, k, ans);
                    }
                    ch[i] = 0;
                }
            }
        }
    }

    public int solution(int k, int[][] dungeons) {
        answer = -1;
        ch = new int[dungeons.length]; //dungeons 행길이 DFS 함수 방문여부 변수
        DFS(0, dungeons, k, 0);

        return answer;
    }

    public static void main(String[] args) {
        Programmers87946 T = new Programmers87946();
        int k = 100;
        int[][] dungeons = {{10, 10}, {20, 20}, {30, 80}};
//        int k = 80;
//        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(T.solution(k, dungeons));
    }
}
