/**
 * 线程死锁:即一个方法调用过程中,需要另一个资源的锁，而另一个资源锁需要本资源的锁,这样就造成双方
 *         僵持不下的局面.
 */
public class Thread8 {
    private Object o1= new Object();
    private Object o2= new Object();
    public void m1(){
       synchronized (o1){
           System.out.println("m1.....");
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           synchronized (o2){
               System.out.println("m2.....");
           }
       }
    }

    public void m2(){
        synchronized (o2){
            System.out.println("m2.....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o1){
                System.out.println("m1.....");
            }
        }
    }

    public static void main(String[] args) {
        Thread8 thread8 = new Thread8();
        new Thread(()->thread8.m1()).start();
        new Thread(()->thread8.m2()).start();
    }
}
