/**
 * 当同步方法执行期间碰到异常时,会放弃当前锁，其他线程执行会接着上一次方法结果继续执行下去
 */
public class Thread9 {
    private int count = 0;
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" start");
        while(true){
            count++;
            System.out.println(Thread.currentThread().getName()+""+count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count==5){
                int i = 10/0;
            }
        }
    }

    public static void main(String[] args) {
        Thread9 thread9 = new Thread9();
        new Thread(()->thread9.m1(),"t1").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->thread9.m1(),"t2").start();
    }
}
