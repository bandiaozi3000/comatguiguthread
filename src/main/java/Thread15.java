/**
 * 关于线程之间通信:
 *    wait:之后的程序都不执行，释放当前锁.等到该线程被唤醒时,接着刚才的执行.
 */
public class Thread15 {
    public  int count=0;

    public static void main(String[] args) {
        Thread15 thread15 = new Thread15();
        Object lock = new Object();
        new Thread(()->{
            synchronized (lock){
                System.out.println("t1 start....");
                if(thread15.count!=5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
                System.out.println("t1 end....");
            }
        },"t1").start();

        new Thread(()->{
            synchronized (lock) {
                System.out.println("t2 start....");
                for (int i = 0; i < 10; i++) {
                    thread15.count++;
                    System.out.println( thread15.count);
                    if(thread15.count==5){
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("t2 end....");
            }
        },"t2").start();
    }
}
