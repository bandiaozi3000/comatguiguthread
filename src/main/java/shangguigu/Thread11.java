package shangguigu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile,synchronized,Atomic原子类性能对比??
 * synchronized性能高,volatile不能保证原子性
 */
public class Thread11 {
    private AtomicInteger count = new AtomicInteger(0);
    private volatile int count1 = 0;
    private int count2 = 0;

    public void m1() {
        for (int i = 0; i < 1000000; i++) {
            count.incrementAndGet();
        }
    }

    public void m2() {
        for (int i = 0; i < 1000000; i++) {
            count1++;
        }
    }

    public synchronized void m3() {
        for (int i = 0; i < 1000000; i++) {
            count2++;
        }
    }

    public static void main(String[] args) {
        Thread11 thread11 = new Thread11();
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(() -> thread11.m1());
//            thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("代码执行时间为:" + (endTime - startTime));

        List<Thread> threads = new ArrayList<Thread>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> thread11.m1()));
        }
        threads.forEach((o) -> o.start());
        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println("代码执行时间为:" + (endTime - startTime));

//        startTime = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(() -> thread11.m2());
//            thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        endTime = System.currentTimeMillis();
//        System.out.println("代码执行时间为:" + (endTime - startTime));

        List<Thread> threads1 = new ArrayList<Thread>();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            threads1.add(new Thread(() -> thread11.m2()));
        }
        threads1.forEach((o) -> o.start());
        threads1.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        endTime = System.currentTimeMillis();
        System.out.println("代码执行时间为:" + (endTime - startTime));

//        startTime = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(() -> thread11.m3());
//            thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        endTime = System.currentTimeMillis();
//        System.out.println("代码执行时间为:" + (endTime - startTime));

        List<Thread> threads2 = new ArrayList<Thread>();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            threads2.add(new Thread(() -> thread11.m3()));
        }
        threads2.forEach((o) -> o.start());
        threads2.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        endTime = System.currentTimeMillis();
        System.out.println("代码执行时间为:" + (endTime - startTime));
        System.out.println(thread11.count);
        System.out.println(thread11.count1);
        System.out.println(thread11.count2);
    }
}
