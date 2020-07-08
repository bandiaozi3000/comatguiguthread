package shangguigu;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock:tryLock:尝试加锁,无论是否加锁成功,方法都会执行.
 */
public class Thread17 {
    Lock lock = new ReentrantLock();
    public void m1(){
        lock.lock();
        System.out.println("m1执行......");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public void m2(){
        lock.lock();
        System.out.println("m2执行......");
        lock.unlock();

    }

    public void m3(){
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println(locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(locked){
            System.out.println("m3执行....");
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Thread17 thread17 = new Thread17();
        new Thread(()->thread17.m1(),"t1").start();
        new Thread(()->thread17.m2(),"t2").start();
        new Thread(()->thread17.m3(),"t3").start();
        Random random = new Random();
        random.nextInt(5);
    }
}
