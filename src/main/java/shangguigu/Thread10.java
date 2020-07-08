package shangguigu;

/**
 * volatile关键字:被修饰的变量不同线程之间可见.
 * 疑问:while循环体内有方法时,volatile关键字即使没有,其他线程也可看见被修改变量值?????
 * volatile和synchronized的区别:volatile只保证可见性,不保证原子性
 */
public class Thread10 {
    volatile boolean running = true;

    void m1(Integer timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println(running);
        if ("t1".equals(Thread.currentThread().getName())) {
            running = false;
        }
    }

    public static void main(String[] args) {
        Thread10 thread10 = new Thread10();
        Thread10 thread101 = new Thread10();
        new Thread(() -> thread10.m1(1000), "t1").start();
        new Thread(() -> thread101.m1(5000), "t2").start();
    }
}
