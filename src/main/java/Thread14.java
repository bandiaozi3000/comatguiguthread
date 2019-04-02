/**
 * 尽量不要使用字符串常量作为锁对象.
 * 在下面例子中,m1和m2锁定的是同一个对象
 * 这种情况会发生比较诡异的现象,比如你用到了一个类库,在该类库代码中锁定了字符串"hello",因为你读不到源码,你在
 * 自己的代码中也锁定了"hello",这样就有可能发生比较诡异的死锁阻塞
 */
public class Thread14 {
    String s1= "hello";
    String s2= "hello";
    public void m1(){
        synchronized (s1){

        }
    }

    public void m2(){
        synchronized (s2){

        }
    }
}
