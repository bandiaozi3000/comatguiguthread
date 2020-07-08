package shangguigu;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产-消费者模式
 * 注意:wait一般情况下与while一起,因为if只会判断一次
 *     wait和notify方法只能和synchronized结合使用.若方法没有synchronized关键字,则无法使用该方法
 * @param <T>
 */
public class Thread20<T> {
    List<T> list = new ArrayList<>();

    public synchronized void set(T t) {
        while(true) {
            if(list.size() == 10) {
                try {
                    System.out.println("产品已满,请通知消费者消费");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(t);
            System.out.println("生产者生产" + t);
            this.notifyAll();
        }
    }

    public synchronized T get() {
        while(true) {

            if(list.size() == 0) {
                try {
                    System.out.println("产品为空,请通知生产者生产");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            T t = list.remove(0);
            this.notifyAll();
            System.out.println(Thread.currentThread().getName() + "得到" + t);
        }
    }


    public static void main(String[] args) {
        Thread20<String> thread20 = new Thread20<>();
        new Thread(() -> thread20.get()).start();
        new Thread(() -> thread20.set("苹果")).start();

    }
}
