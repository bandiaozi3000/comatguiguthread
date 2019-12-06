public class Thread4 implements Runnable {
    private int count = 10;

    @Override
    public synchronized void run() {
        for(int i=0;i<10;i++) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count: " + count);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread4 thread = new Thread4();
            new Thread(thread).start();
        }


    }
}
