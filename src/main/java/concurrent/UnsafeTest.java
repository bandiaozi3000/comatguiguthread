package concurrent;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UnsafeTest {
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(5);
//        atomicInteger.incrementAndGet();
//        atomicInteger.incrementAndGet();
//        AtomicLong atomicLong = new AtomicLong(12L);
//        atomicLong.decrementAndGet();
        Thread current = Thread.currentThread();
        current.interrupt();
        System.out.println(current.isInterrupted());

    }
}
