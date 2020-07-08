package shangguigu;

/**
 * 锁定某对象o,如果O的属性发生改变,不影响锁的使用.但是如果o变成另外一个对象,则锁定的对象发生了改变.
 * 所以应该尽量避免锁定对象的引用发生了改变
 * 注意:这从侧面反应了锁加锁的是对象,而不是变量
 *
 */
public class Thread13 {
    Object o = new Object();
    public void m1(){
        synchronized (o){
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        Thread13 thread13 = new Thread13();
        new Thread(()->thread13.m1(),"t1").start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread13.o=new Object();
        new Thread(()->thread13.m1(),"t2").start();
    }
}
