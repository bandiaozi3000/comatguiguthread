package concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest2 {
    public static void main(String[] args) throws InterruptedException {
        Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();
        for(int i =0;i<5;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"被挂起");
                    waiters.add(Thread.currentThread());
                    LockSupport.park();
                    System.out.println(Thread.currentThread().getName()+"继续执行");
                }
            });
            thread.start();
        }
        do{
            System.out.println("释放线程");
            Thread thread = waiters.poll();
            LockSupport.unpark(thread);
            thread.join();
        }while(!waiters.isEmpty());
        System.out.println("线程释放完毕");

    }
}
