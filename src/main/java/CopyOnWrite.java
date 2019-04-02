import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * List各列表性能对比
 * ArrayList:非同步,可能出现各种问题
 * 1).size不符合
 * 2).ArrayIndexOutOfBoundsException
 * 原因:add()操作分为两步:1.检查array容量 2.step2塞值并将size++ (其中size个线程共享,因为是非原子性的,方法执行过程中被打断可能出现异常)
 * 同步:调用Collections.synchronized()方法
 * Vector:同步,性能比较低
 * CopyOnWriteArrayList：同步,读效率高,写效率低.
 * 原理:写时复制,即读的时候不需要加锁(因为写的时候改的列表是一个Copy出来的新列表,而旧的列表并没有改变.)
 * 弊端:1)内存占用问题
 * 2)数据一致性问题.CopyOnWrite容器只能保证数据的最终一致性,不能保证数据的实时一致性.所以如果你希望写入的的数据,马上能读到,请不要使用CopyOnWrite容器
 */
public class CopyOnWrite {

    // static List<Integer> list = new ArrayList<>();
    /**
     * 将非同步list转换为同步list:
     *    Collections.synchronizedList()
     */
     static List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    // static List<Integer> list = new Vector<>();
    // static List<Integer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    list.add(finalI + j);
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        Long endTime = System.currentTimeMillis();
        System.out.println("花费时间为:" + (endTime - startTime));
    }
}
