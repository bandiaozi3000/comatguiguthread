package shangguigu;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 从多次运行结果可以看出,原子类在多线程情况下,无论执行多少次,最后结果都是正确的.
 * 而普通的Int类型在多线程多次执行情况下,结果会出现误差.
 * 原因:a++不是一个原子操作,在执行过程中会被其他线程执行影响.
 */
public class AtomicIntegerTest {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static int a = 0;

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
//        int temvalue = 0;
//        AtomicInteger i = new AtomicInteger(0);
//        System.out.println(i.getAndSet(3)+".........");
//        temvalue = i.getAndSet(3);
//        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:0;  i:3
//        temvalue = i.getAndIncrement();
//        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:3;  i:4
//        temvalue = i.getAndAdd(5);
//        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:4;  i:9
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<1000;i++){
                    atomicInteger.incrementAndGet();
                    a++;
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<1000;i++){
                    atomicInteger.incrementAndGet();
                    a++;
                }
            }
        });
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println("current int :" +a);
        System.out.println("current atomicInt :" +atomicInteger.get());

    }

}