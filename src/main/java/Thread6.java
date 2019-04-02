/**
 * 注意:此时同步方法上加了static关键字，所以此方法对是该类加锁，而不是对类的某个对象加锁
 *      所以for循环内即使该类生成多个实例,所以直接结果还是当某个线程方法执行完后另一个线
 *      程才会接着执行
 */
public class Thread6 {
    public  static synchronized  void m1(){
        for(int i = 0 ;i<10; i++){
            System.out.println(Thread.currentThread().getName() + i);
        }
    }

    public static void main(String[] args) {
        for(int i =0;i<3;i++){
            Thread6 thread6 = new Thread6();
            new Thread(()->thread6.m1(),"t"+i).start();
        }
    }
}
