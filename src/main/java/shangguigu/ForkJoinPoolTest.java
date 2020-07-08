package shangguigu;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool:拆分-合并.将一项比较大的任务拆分成多个小任务并行执行,效率高
 *    RecursiveAction：无返回值
 *    RecursiveTask：有返回值
 */
public class ForkJoinPoolTest {
    static int max = 500;
    static int[] nums = new int[1000];

    static {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            nums[i] = random.nextInt(1000);
        }
        System.out.println(Arrays.stream(nums).sum());
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AddTask addTask = new AddTask(0, nums.length);
        forkJoinPool.execute(addTask);  //相当于执行addTask的fork()
        System.out.println(addTask.join());
        System.in.read();
    }

    //    static class AddTask extends RecursiveAction{
    static class AddTask extends RecursiveTask<Integer> {
        private int start;
        private int end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum=0;
            if (end - start < max) {
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println(Thread.currentThread().getName() + "start:" + start + "  " + "end:" + end);
                return sum;
            } else {
                int middle = (start + end) / 2;
                AddTask addTask1 = new AddTask(start, middle);
                AddTask addTask2 = new AddTask(middle, end);
                addTask1.fork();
                addTask2.fork();
                return addTask1.join() + addTask2.join();
            }
        }
    }
}
