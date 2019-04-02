/**
 * 一个加锁方法在执行过程中调用另一个加锁方法是允许的,即synchronized获得的锁是可重用的
 */
public class Thread7 {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() +"m1");
        m2();
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() +"m2");
    }

    public static void main(String[] args) {
        Thread7 thread7 = new Thread7();
        new Thread(()->thread7.m1()).start();
    }
}
