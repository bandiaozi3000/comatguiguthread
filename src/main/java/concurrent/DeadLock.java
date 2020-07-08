package concurrent;

/**
 * 检查死锁命令
 * 1. jps -l:获取进程id
 * 2.jstack 进程编号
 *
 *第二种
 *   jconsole
 */
public class DeadLock {
    private static Object a = new Object();
    private static Object b = new Object();
    public static void deadLock(){
        new Thread(()->{
            synchronized (a){
                System.out.println(Thread.currentThread().getName()+"对对象a加锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println(Thread.currentThread().getName()+"对对象b加锁");
                }
            }
        }).start();
        new Thread(()->{
            synchronized (b){
                System.out.println(Thread.currentThread().getName()+"对对象b加锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    System.out.println(Thread.currentThread().getName()+"对对象a加锁");
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        DeadLock.deadLock();
    }
}
