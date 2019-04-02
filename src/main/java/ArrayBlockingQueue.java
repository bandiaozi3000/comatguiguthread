import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界阻塞队列:
 *     队列满的时候,put方法会阻塞,add方法会报错,offer会放回一个false值
 */
public class ArrayBlockingQueue {
    private static BlockingQueue<Integer> queue = new java.util.concurrent.ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            queue.offer(i);
        }
        queue.add(1);
        try {
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.offer(1);
        try {
            queue.offer(1,1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
