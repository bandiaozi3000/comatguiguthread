package shangguigu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * scheduleWithFixedDelay和scheduleAtFixedRate区别
 * scheduleWithFixedDelay:在任务执行完毕后延长period时间执行,注意是任务执行完毕后在延长,相当于单位时间是 任务执行时长+period
 * scheduleAtFixedRate:以固定频率执行,若方法执行时间大于period,则方法执行完毕后立马执行下次任务 相当于单位时间是 max(任务时长,period)
 */
public class SchedulePool {
    public static void main(String[] args) {
        /**
         * 比较输出结果:
         * scheduleAtFixedRate:单位时长为3s->max(3,2)
         * pool-1-thread-12020-03-16 10:12:12
           pool-1-thread-12020-03-16 10:12:15
           pool-1-thread-22020-03-16 10:12:18
           scheduleWithFixedDelay:单位时长5s->3+2
           pool-1-thread-12020-03-16 10:13:29
           pool-1-thread-12020-03-16 10:13:34
           pool-1-thread-22020-03-16 10:13:39
           由此可知,结果验证正确
         *
         */
        ExecutorService service = Executors.newScheduledThreadPool(4);
         ((ScheduledExecutorService) service).scheduleWithFixedDelay(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(Thread.currentThread().getName()+ dFormat.format(new Date()));
        },2,2, TimeUnit.SECONDS);
    }
}
