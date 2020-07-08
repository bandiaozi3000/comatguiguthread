package concurrent;

import javax.xml.bind.SchemaOutputResolver;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapTest extends Thread{
            static Hashtable<Integer, Integer> map = new Hashtable<>();
            static AtomicInteger at = new AtomicInteger(0);
            static volatile int count = 1;
            public void run() {
            while(count < 100000) {
                        map.put(at.get(),at.get());
                        at.incrementAndGet();
                System.out.println(at.get());
                    }
             }
    public static void main(String[] args) {
               HashMapTest t0 = new HashMapTest();
               HashMapTest t1 = new HashMapTest();
               HashMapTest t2 = new HashMapTest();
               HashMapTest t3 = new HashMapTest();
               HashMapTest t4 = new HashMapTest();
               t0.start();
               t1.start();
               t2.start();
               t3.start();
               t4.start();
//               for(int i=0;i<100000;i++){
//                   System.out.println(map.get(i));
//               }
           }

}
