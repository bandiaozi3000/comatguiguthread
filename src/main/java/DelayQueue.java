import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列:到指定时间执行.多用于执行定时任务
 *     步骤:队列任务需要实现Delayed接口
 */
public class DelayQueue {
    private static BlockingQueue<MyTask> queue = new java.util.concurrent.DelayQueue<>();

    static class MyTask implements Delayed {

        long runningTime;
        String name;

        public MyTask(long rt, String name) {
            this.runningTime = rt;
            this.name = name;
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name=" + name +
                    '}';
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay((TimeUnit.MILLISECONDS)) < o.getDelay((TimeUnit.MILLISECONDS))) {
                return -1;
            } else if (this.getDelay((TimeUnit.MILLISECONDS)) > o.getDelay((TimeUnit.MILLISECONDS))) {
                return 1;
            } else {
                return 0;
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Long now = System.currentTimeMillis();
            MyTask task1 = new MyTask(now + 1000, "1000");
            MyTask task2 = new MyTask(now + 2000, "2000");
            MyTask task3 = new MyTask(now + 500, "500");
            MyTask task4 = new MyTask(now + 100, "100");
            MyTask task5 = new MyTask(now + 20000, "20000");
            queue.put(task1);
            queue.put(task2);
            queue.put(task3);
            queue.put(task4);
            queue.put(task5);
            for (int i = 0; i < 5; i++) {
                System.out.println(queue.take());
            }

        }
    }
}
