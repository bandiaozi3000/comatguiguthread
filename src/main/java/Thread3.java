public class Thread3 implements Runnable {
    private  int count = 10;
   public synchronized void run(){
       count--;
       System.out.println(Thread.currentThread().getName()+"count: "+count);
   }

    public static void main(String[] args) {
       Thread3 thread = new Thread3();
       for(int i=0;i<5;i++){
           new Thread(thread).start();
        }

    }
}
