package shangguigu;

/**
 * 同步方法的加锁不影响非同步方法的执行
 */
public class Thread5 {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "m1 start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m1 end");

    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() + "m2 start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m2 end");

    }

    public static void main(String[] args) {
        Thread5 thread5 = new Thread5();
        /**
         * 以下写法是Java8 lambda表达式,等同于下述写法
         */
        new Thread(()->thread5.m1(),"t1").start();
        new Thread(()->thread5.m2(),"t2").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                thread5.m1();
//            }
//        }, "t1").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                thread5.m1();
//            }
//        }, "t2").start();
    }
}
