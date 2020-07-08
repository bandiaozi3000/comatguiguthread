package concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        System.out.println("start...");
//        LockSupport.unpark(Thread.currentThread());
//        LockSupport.park();
        LockSupport.parkNanos(2000000000);
        System.out.println("end...");

    }
}

