package shangguigu;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * List:非同步列表,所以可能出现售出同一张票的情况
 * Vector:同步列表,方法同步,所以不会出现上述情况.但方法与方法之间不是原子性,例如list.size()和list.remove()方法之间可能被其他线程打断
 *        此时可能发生数组下表越界的情况
 * Queue:队列，先取后判断.若此时列表为空,取值为空.
 * 个人理解:关键在于取值的操作Queue即使列表为空也不会报错,而List取值若列表为空则会报错.
 */
public class TicketSeller {

//    static List<Integer> list = new ArrayList<>();
//    static Vector<Integer> list = new Vector<>();
    static Queue<Integer> list = new ConcurrentLinkedQueue<>();
    static{
        for(int i=0;i<1000;i++){
            list.add(i);
        }
    }

    public static void main(String[] args) {
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                while(list.size()>0){
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("销售了"+list.remove(0));
//                }
//            }).start();
//        }

        for(int i=0;i<10;i++){
            new Thread(()->{
                while(true){
                  Integer integer = list.poll();
                  if(integer==null){
                      break;
                  }else{
                      System.out.println("销售了"+integer);
                  }
                }
            }).start();
        }
    }
}
