import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：门栓:
 *     latch.await()：门栓等待,门栓值为0时,门栓打开
 *     注意:门栓打开后,两个线程同时执行,而不是阻塞当前线程打开门栓线程
 */
public class Thread16 {
    public int count = 0;

    public static void main(String[] args) {
        Thread15 thread15 = new Thread15();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("t1 start....");
            if (thread15.count != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end....");

        }, "t1").start();

        new Thread(() -> {
            System.out.println("t2 start....");
            for (int i = 0; i < 10; i++) {
                thread15.count++;
                System.out.println(thread15.count);
                if (thread15.count == 5) {
                    latch.countDown();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end....");
        }, "t2").start();
    }
}

