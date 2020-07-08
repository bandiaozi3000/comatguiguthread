package shangguigu;

import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列LinkedBlockingQueue：
 *    put():向队列添加
 *    take():从队列获取,若队列为空,则阻塞等待
 */
public class LinkedBlockingQueue {
    private static BlockingQueue<String> queue = new java.util.concurrent.LinkedBlockingQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("产品" + i);
                    try {
                        queue.put("产品" + i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }).start();
        for(int i=0;i<10;i++){
            new Thread(() -> {
                while(true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "" + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}