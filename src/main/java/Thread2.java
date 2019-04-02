public class Thread2 implements  Runnable {

    public synchronized void run(){
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }

    public static void main(String[] args) {
        /**
         *     Thread thread1 = new Thread(new Thread2());
         *     Thread thread2 = new Thread(new Thread2());
         *     注意:
         *        上述这种方法和下面这种方法输出结果不一样.上面两个线程参数New了两个不同的对象,所以执行结果还是双方抢占
         *        而下面的这种方法,两个线程的参数对象是同一个,因为对run方法加了锁,所以当一个线程执行时,另一个线程会等待
         *      synchronized:方法是对当前对象加锁,而不是对代码块或者方法加锁!!!
         */
        Thread2 thread3 = new Thread2();
        Thread thread1 = new Thread(thread3);
        Thread thread2 = new Thread(thread3);
        thread1.start();
        thread2.start();
    }
}
