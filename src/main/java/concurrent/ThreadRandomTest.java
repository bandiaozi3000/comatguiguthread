package concurrent;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadRandomTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Random random = new Random();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Long start = System.currentTimeMillis();
        for(int i = 0;i<1000;i++){
            service.execute(()->{    //execute:执行结果没有返回值  submit:执行结果可以有返回值也可以没有
                list.add(random.nextInt(100));
//                list2.add(threadLocalRandom.nextInt(100));
            });
        }
        System.out.println(list.size()+"..........");
        Thread.sleep(1000);
        System.out.println(list.size()+"..........");
        Long end = System.currentTimeMillis();
        System.out.println(end-start);
        Thread.sleep(5000000);
        service.shutdown();
//        List<Integer> list2 = new ArrayList<>();
//        for(Integer num:list){
//           if(!list2.contains(num)) {
//               list2.add(num) ;
//           }
//        }
//        System.out.println(list2.size());
        Set set = new HashSet(list);
        Set set2 = new HashSet(list2);
        System.out.println(set.size());
        System.out.println(set2.size());
    }
}
