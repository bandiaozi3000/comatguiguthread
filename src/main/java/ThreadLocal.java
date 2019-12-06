/**
 * ThreadLocal:空间换时间，每个线程有自己的空间,即线程之间值不共享
 * Synchronized:时间换空间
 */
public class ThreadLocal {
    static volatile java.lang.ThreadLocal<String> threadLocal = new java.lang.ThreadLocal<>();
//    static volatile List<String> threadLocal = new ArrayList<>();
    public void m1(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadLocal.get());
    }

    public void m2(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadLocal.set("张三");
    }

    public static void main(String[] args) {
        ThreadLocal thread22 = new ThreadLocal();
        new Thread(()->thread22.m1()).start();
        new Thread(()->thread22.m2()).start();
    }
}
