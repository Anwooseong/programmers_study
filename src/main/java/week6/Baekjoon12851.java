package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon12851 {

    static int time = 0;
    static int count = 0;
    static Map<Integer, Integer> map;
    public void bfs(int subin, int brother) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(subin);
        map.put(subin, map.getOrDefault(map.get(subin), 0));
        map.put(brother, map.getOrDefault(map.get(brother), 0));
        if (subin == brother) {
            time = 0;
            count = 1;
            return;
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();


            //현재에서 다음거 계산
            int left = cur - 1;
            if (isCheck(left)) {
                refreshMap(left, brother, cur, queue);
            }

            int right = cur + 1;
            if (isCheck(right)) {
                refreshMap(right, brother, cur, queue);
            }

            int doublePosition =cur * 2;
            if (isCheck(doublePosition)) {
                refreshMap(doublePosition, brother, cur, queue);
            }
        }
    }

    public void refreshMap(int position, int brother, int cur, Queue<Integer> queue) {
        if (!map.containsKey(position)) {
            map.put(position, 0);
        }
        if (position == brother) {
            if (map.get(brother) == 0) {
                time = map.get(cur) + 1;
                map.put(brother, map.get(cur) + 1);
                count++;
            } else if (map.get(brother) == map.get(cur) + 1) {
                count++;
            }
        } else if (map.get(position) == 0) {
            map.put(position, map.get(cur) + 1);
            queue.offer(position);
        } else if (map.get(position) == map.get(cur) + 1) {
            queue.offer(position);
        }
    }
    public boolean isCheck(int position) {
        if (position >= 0 && position <= 100000) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Baekjoon12851 T = new Baekjoon12851();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int brother = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        T.bfs(subin, brother);
        System.out.println(time);
        System.out.println(count);
    }

}
