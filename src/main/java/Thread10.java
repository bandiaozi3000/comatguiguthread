/**
 * volatile关键字:被修饰的变量不同线程之间可见.
 * 疑问:while循环体内有方法时,volatile关键字即使没有,其他线程也可看见被修改变量值?????
 * volatile和synchronized的区别:volatile只保证可见性,不保证原子性
 */
public class Thread10 {
     boolean running = true;
     void m1(){
         System.out.println("m1 start");
        while(running){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("m1 going");
        }
         System.out.println("m1 end");
    }

    public static void main(String[] args) {
        Thread10 thread10 = new Thread10();
        new Thread(()->thread10.m1(),"t1").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread10.running=false;
    }
}
