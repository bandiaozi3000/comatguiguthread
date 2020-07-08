package shangguigu;

import java.util.ArrayList;
import java.util.List;

/**
 * synchronized优化:
 *   同步代码块中的语句块越少越好
 *   例如：
 *      方法m1比方法m2效率低
 */
public class Thread12 {
    private int count = 0;

    public synchronized void m1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            count++;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread12 thread12 = new Thread12();
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> thread12.m1()).start();
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("代码执行时间为:" + (endTime - startTime));
//
//         startTime = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> thread12.m2()).start();
//        }
//        endTime = System.currentTimeMillis();
//        System.out.println("代码执行时间为:" + (endTime - startTime));
//        System.out.println(thread12.count);
        List<Thread> threads = new ArrayList<Thread>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> thread12.m1()));
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
        System.out.println(thread12.count);

        List<Thread> threads1 = new ArrayList<Thread>();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            threads1.add(new Thread(() -> thread12.m2()));
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
        System.out.println(thread12.count);
    }
}
