package concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public class YieldTest implements Runnable{

    YieldTest(){
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for(int i=0;i<5;i++){
            if((i%5==0)){
                System.out.println(Thread.currentThread()+"yield cpu .....");
//                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread()+"is over");
    }

    public static void main(String[] args) throws InterruptedException {
//        new YieldTest();
//        new YieldTest();
//        new YieldTest();
        Thread thread = Thread.currentThread();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        ThreadLocalRandom random = ThreadLocalRandom.current();
/**
 * ThreadLocalRandom.current()作用:
 *   Thread里有两个变量:threadLocalRandomProbe,threadLocalRandomSeed,作用就是根据threadLocalRandomProbe判断当前线程threadLocalRandomSeed是否已经初始化,
 *   若为0则没初始化执行初始化,会将threadLocalRandomSeed赋值.所以如果该方法在线程外初始化,则不会初始化当前线程而是主线程.
 *   另外:ThreadLocalRandom的实例是单例,相当于只是一个工具类,就是为了给当前线程的threadLocalRandomSeed赋初始值.
 */
        for(int i=0;i<10;i++){
            executorService.execute(()->{
                Random random1 = new Random();
                ThreadLocalRandom random = ThreadLocalRandom.current();
                try {
                    Thread.sleep(random1.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread thread1 = Thread.currentThread();
                System.out.println(Thread.currentThread().getName()+"local---"+random.nextInt(10));
//                System.out.println(Thread.currentThread().getName()+"random---"+random1.nextInt(10));
            });

//            new Thread(()->{
//                Random random1 = new Random();
//                try {
//                    Thread.sleep(random1.nextInt(1000));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+"local---"+random.nextInt(10));
//                System.out.println(Thread.currentThread().getName()+"random---"+random1.nextInt(10));
//            }).start();
        }
        executorService.shutdown();
    }
}
