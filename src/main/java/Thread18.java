import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.lockInterruptibly():只是一种加锁方式,这种加锁方式可被打断.而普通lock方法无法被其他线程中断加锁
 *
 */
public class Thread18 {
//    public  void m1(){
//        lock.lock();
//        try {
//            lock.lockInterruptibly();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(Thread.currentThread().getName());
//        try {
//            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        lock.unlock();
//    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            lock.unlock();
        }, "t2");
        thread2.start();
        thread2.interrupt();




    }
}
