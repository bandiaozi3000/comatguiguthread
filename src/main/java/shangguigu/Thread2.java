package shangguigu;

import java.util.concurrent.locks.ReentrantLock;

public class Thread2 implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    if(i == 5){
                        /**
                         *  this.notifyAll()放在循环内和放在循环外结果不一样.放在循环外正常执行完毕,循环内死锁.
                         *  思索原因:放在循环内,只有当i=5的时候才会唤醒,当10次执行完后,例外一个线程并没有被唤醒,
                         *  而如果放在循环外,每次执行完毕则会执行一次唤醒操作.所以程序会正常执行完毕.
                         */
                        this.notifyAll();
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

    public static void main(String[] args)  {
        /**
         *     Thread thread1 = new Thread(new Thread2());
         *     Thread thread2 = new Thread(new Thread2());
         *     注意:
         *        上述这种方法和下面这种方法输出结果不一样.上面两个线程参数New了两个不同的对象,所以执行结果还是双方抢占
         *        而下面的这种方法,两个线程的参数对象是同一个,因为对run方法加了锁,所以当一个线程执行时,另一个线程会等待
         *      synchronized:方法是对当前对象加锁,而不是对代码块或者方法加锁!!!
         */
        Thread2 thread3 = new Thread2();
        Thread thread1 = new Thread(thread3);
        Thread thread2 = new Thread(thread3);
        thread1.start();
        thread2.start();

    }
}
