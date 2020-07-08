package shangguigu;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 比较几种Map
 * HashMap:非同步
 * HashTable:同步
 * ConcurrentHashMap:同步,分段锁,效率比HashTable高
 * ConcurrentSkipListMap：同步,分段锁,且排序,效率较ConcurrentHashMap低
 */
public class ConcurrentMap {
    // private static Map<String, String> map = new HashMap<>();
    // private static Map<String,String> map = new Hashtable<>();
    // private static Map<String,String> map = new ConcurrentHashMap<>();
    private static Map<String, String> map = new ConcurrentSkipListMap<>();

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put(String.valueOf(finalI + j), String.valueOf(finalI + j));
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("花费时间为:" + (endTime - startTime));


    }

}
