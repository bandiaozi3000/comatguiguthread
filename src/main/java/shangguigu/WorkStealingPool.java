package shangguigu;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WorkStealingPool：精灵线程,所以用System.in.read()可以看到输出
 *    功能:先做完工作的会接手其他工作
 *  newWorkStealingPool(4)：4代表的不是创建4个线程,而是最多支持几个线程同时执行
 *
 */
public class WorkStealingPool {
    public static void main(String[] args) throws IOException {
//        ExecutorService service = Executors.newWorkStealingPool(4);
        ExecutorService service = Executors.newFixedThreadPool(4);
//        ExecutorService service = Executors.newCachedThreadPool();
        Mytask mytask1 = new Mytask(1000);
        Mytask mytask2 = new Mytask(2000);
        Mytask mytask3 = new Mytask(500);
        Mytask mytask4 = new Mytask(2000);
        Mytask mytask5 = new Mytask(3000);
        service.execute(mytask1);
        service.execute(mytask2);
        service.execute(mytask3);
        service.execute(mytask4);
        service.execute(mytask5);
//        System.in.read();
    }
    static class Mytask implements Runnable{
        private int time;

        public Mytask(int time){
            this.time=time;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
