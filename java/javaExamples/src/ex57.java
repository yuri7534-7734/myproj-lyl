import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ex57 {
    public static void main(String[] args) {
        //스택과 큐
        //스택(Stack) : FILO(First Input Last Out)
        //            : 맨 나중에 들어간 요소가 먼저 나오는 자료구조
        //            : 용도 ) 함수 호출시 이전 함수의 주소, 데이터를 보관
        //큐(Queue) : FIFO(First Input First Out)
        //          : 맨 처음 들어가 요소가 맨 처음에 나온다.
        //          : 용도 ) 버퍼(Buffer)
        //               입력속도와 출력속도가 다른 경우 완충역할을 한다.
        Stack<Integer> stack = new Stack<>();
        stack.push(10); //마지막에 추가
        System.out.println(stack);
        stack.push(20);
        System.out.println(stack);
        stack.push(30);
        System.out.println(stack);
        System.out.println(stack.pop()); //맨 마지막 요소를 반환하면서 지움 //30
        System.out.println(stack); //[10, 20]

        System.out.println(stack.peek()); //최상단(마지막)요소를 반환, 지우지는 않는다.
        System.out.println(stack); //[10, 20]
        System.out.println(stack.size()); //2
        System.out.println(stack.contains(20)); //20을 갖고 있니? //true
        System.out.println(stack.empty()); //비어있는지 아닌지를 true false로 //false

        //LinkedList가 Queue를 상속 받았으므로 업캐스팅되었다.
        Queue<Integer> queue = new LinkedList<>(); //LinkedList사용 //업캐스팅
        queue.add(10);
        System.out.println(queue);
        queue.add(20); //뒤로 추가
        System.out.println(queue);
        queue.add(30); //뒤로 추가
        System.out.println(queue);
        queue.offer(40); //뒤에 추가
        System.out.println(queue);

        //LinkedList는 가변용량이라서 용량제한이 거의 없음.
        //add : 실패시 Exception발생(반드시 추가될 것으로 알고 설계)
        //offer : 실패시 false 반환(실패를 가정하고 설계)
        //offer을 쓰는 것이 더 낫다.

        //제일 먼저 들어간 값을 제거, 그 값을 반환하면서 제거
        System.out.println(queue.poll());
        System.out.println(queue);

        //제일 먼저 들어간 값을 반환하고 제거는 하지 않는다.
        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println(queue.size()); //3




    }
}
