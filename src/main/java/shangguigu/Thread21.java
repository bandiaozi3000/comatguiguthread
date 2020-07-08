package shangguigu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition:可指定唤醒线程,效率较notifyAll高
 * @param <T>
 */
public class Thread21<T> {
    List<T> list = new ArrayList<>();
    Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void set(T t) {
        while (true) {
            try {
                lock.lock();
                if (list.size() == 10) {
                    try {
                        System.out.println("产品已满,请通知消费者消费");
                        producer.await();
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
                consumer.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public T get() {
        while (true) {
            try {
                lock.lock();
                if (list.size() == 0) {
                    try {
                        System.out.println("产品为空,请通知生产者生产");
                        consumer.await();
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
                producer.signalAll();
                System.out.println(Thread.currentThread().getName() + "得到" + t);
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        Thread20<String> thread20 = new Thread20<>();
        new Thread(() -> thread20.get()).start();
        new Thread(() -> thread20.set("苹果")).start();

    }
}

