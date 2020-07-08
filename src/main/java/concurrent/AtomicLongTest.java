package concurrent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 多次验证结果可得,AtomicLong类具有原子性,在多线程情况下结果无误.而普通的int类型则不存在原子性,即使加了volatile
 */
public class AtomicLongTest {

    private static AtomicLong atomicLong = new AtomicLong();

    private volatile static int  a = 0;

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> arrayList  =new ArrayList<>();
        ArrayList<Integer> arrayList2  =new ArrayList<>();
        for(int i=0;i<1000;i++){
            arrayList.add(0);
            arrayList2.add(0);
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<1000;i++){
                    if(arrayList.get(i).intValue()==0){
                        atomicLong.incrementAndGet();
                        a++;
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<1000;i++){
                    if(arrayList.get(i).intValue()==0){
                        atomicLong.incrementAndGet();
                        a++;
                    }
                }
            }
        });
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println("数组0的个数为:"+atomicLong.get());
        System.out.println("数组0的个数为:"+a);
    }
}
