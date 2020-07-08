package shangguigu;

import java.util.concurrent.LinkedTransferQueue;

/**
 * LinkedTransferQueue：阻塞队列.
 *    特殊方法:transfer:直接将消息传给消费线程,而不会先放入queue队列,消费者再从队列获取
 *    如果消费者线程在方法后开启,会造成阻塞.而put等方法则不会
 * SynchronousQueue：特殊的LinkedTransferQueue.其内容量为0，即队列里有内容应该立即被消费者消费.调用add()方法则会报:Queue full
 */
public class TransferQueue {
    public static void main(String[] args) {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        //SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(()-> {
        try {
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }).start();
        try {
            queue.transfer("message");
//            queue.add("message");   针对SynchronousQueue,不能用该方法
//            queue.put("message");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            System.out.println(queue.take());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
