import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * get():
 *    等待任务执行完成.若任务为执行完,阻塞线程.
 */

public class Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            Thread.sleep(3000);
            return 10;
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        java.util.concurrent.Future<Integer> f = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 5;
        });
        Thread.sleep(10000);
        System.out.println("aasdasdadasda");
        System.out.println(f.get());  //阻塞线程,获取执行结果
        System.out.println(f.isDone());
        System.out.println(f.get());
        System.out.println(f.isDone());
        executorService.shutdown();


    }
}
