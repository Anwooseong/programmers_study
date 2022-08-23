package week3;

import java.util.Scanner;
import java.util.Stack;

//문자열 폭발
//https://www.acmicpc.net/problem/9935
public class Baekjoon9935 {
    static String arr, bomb;
    public String solution() {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < arr.length(); i++) {
            stack.push(arr.charAt(i));
            if (stack.peek() == bomb.charAt(bomb.length() - 1) && bomb.length() <= stack.size()) {
                boolean bombCheck = true;
                for (int j = 0; j < bomb.length() - 1; j++) {
                    Character equalArr = stack.get(stack.size() - bomb.length() + j);
                    char equalBomb = bomb.charAt(j);
                    if (equalArr != equalBomb) {
                        bombCheck = false; //폭탄이 아니다.
                        break;
                    }
                }
                if (bombCheck) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        for(char c : stack) {
            answer.append(c);
        }
        if (answer.length() == 0) {
            return "FRULA";
        }
        return answer.toString();
/*        문자열을 합칠 때마다 새로운 String 객체를 생성해야 하고 새로운 String 객체에 현재의 문자열의 크기만큼 옮겨주는 연산을 해줘야 하기 때문에 매번 O(문자열의 크기)의 시간 복잡도를 가진다.
        또한 사용되지 않는 String 객체는 가비지 컬렉터에 의해 제거돼야 하기 때문에 String을 이용해서 문자열을 합치는 방법은 상당히 비효율적이다.
​다만, String으로 선언한 객체는 불변하기 때문에 단순 조회 연산에서는 상당히 효율적이며 다중 스레드 환경에서 동기화를 신경 쓸 필요가 없는 장점이 있다.
​
        이와 달리 StringBuilder와 StringBuffer로 선언한 객체는 가변(Mutable)하는 객체이다. new 연산을 통해 객체를 한 번만 생성한 후 문자열 연산이 발생하게 되면 새로운 객체를 생성하는 것이 아니라 이미 선언한 객체의 메모리 공간을 변경하여 처리하는 방식이다. 정적 배열과 동적 배열을 생각하면 이해하기 쉬울 것이다.*/
    }

    public static void main(String[] args) {
        Baekjoon9935 T = new Baekjoon9935();
        Scanner kb = new Scanner(System.in);
        arr = kb.nextLine();
        bomb = kb.nextLine();
        System.out.println(T.solution());
    }
}
