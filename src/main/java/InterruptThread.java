/**
 * @ClassName:InterruptThread
 * @Description:线程终端
 * @Author:lm.sun
 * @Date:2019/12/5 15:07
 */
public class InterruptThread implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            if(Thread.currentThread().isInterrupted()==true&&i==5){
                break;
            }
            System.out.println("thread is executing........");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptThread());
        thread.start();
        /**
         * interrupt()并不是真的中断线程,只是将该线程的中断标志置为true，若真的希望将线程中断,根据Thread.currentThread().isInterrupted()的
         * 返回值来判断
         */
        thread.interrupt();
        thread.join();
        System.out.println("main thead end ......");

    }
}
