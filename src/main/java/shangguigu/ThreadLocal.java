package shangguigu;

import java.util.ArrayList;
import java.util.List;

/**
 * ThreadLocal:空间换时间，每个线程有自己的空间,即线程之间值不共享
 * Synchronized:时间换空间
 */
public class ThreadLocal {
    static volatile java.lang.ThreadLocal<List> threadLocal = new java.lang.ThreadLocal<>();
//    static volatile List<String> threadLocal = new ArrayList<>();
    public void m1(List list){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadLocal.set(list);
        System.out.println(threadLocal.get());
        threadLocal.get().add("ccc");
    }

    public void m2(List list) throws InterruptedException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadLocal.set(list);
        Thread.sleep(3000);
        System.out.println(threadLocal.get());
    }

    public static void main(String[] args) {
        ThreadLocal thread22 = new ThreadLocal();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        new Thread(()->thread22.m1(arrayList)).start();
        new Thread(()-> {
            try {
                thread22.m2(arrayList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
