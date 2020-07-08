package shangguigu;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueue {
    static Queue<String> queue = new ConcurrentLinkedQueue<>();

    //static Queue<String> queue = new ConcurrentLinkedDeque<>();  双端队列

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            queue.offer("queue"+i);
        }
        System.out.println(queue);
        System.out.println(queue.size());
        //取出队首并移除
        System.out.println(queue.poll());
        //取出队首不移除
        System.out.println(queue.peek());
        System.out.println(queue.size());

    }
}
