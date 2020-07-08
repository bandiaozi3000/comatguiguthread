package shangguigu;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Lock lock = new ReentrantLock(true):指定该锁为公平锁,此时线程会有序执行,而不是抢占式执行
 */
public class Thread19 {
//    Lock lock = new ReentrantLock(true);
    Lock lock = new ReentrantLock(true);
    public void m1(){
        for(int i=0;i<100;i++){
            lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread19 thread19 = new Thread19();
        new Thread(()->thread19.m1(),"t1").start();
        new Thread(()->thread19.m1(),"t2").start();
    }
}
