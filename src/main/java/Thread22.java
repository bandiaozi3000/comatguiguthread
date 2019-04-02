import java.util.ArrayList;
import java.util.List;

/**
 * ThreadLocal:空间换时间，每个线程有自己的空间,即线程之间值不共享
 * Synchronized:时间换空间
 */
public class Thread22 {
//    static volatile ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static volatile List<String> threadLocal = new ArrayList<>();
    public void m1(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadLocal.get(0));
    }

    public void m2(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadLocal.add("张三");
    }

    public static void main(String[] args) {
        Thread22 thread22 = new Thread22();
        new Thread(()->thread22.m1()).start();
        new Thread(()->thread22.m2()).start();
    }
}
