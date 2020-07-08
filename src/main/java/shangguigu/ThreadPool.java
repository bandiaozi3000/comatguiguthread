package shangguigu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i = 0;i<6;i++){
            service.execute(()->{    //execute:执行结果没有返回值  submit:执行结果可以有返回值也可以没有
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);
        service.shutdown();    //关闭线程池,若不调用该方法,服务会一直等待
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
    }
}
