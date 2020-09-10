package OOMError;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 个人理解javaHeapSpace和overHeadLimitExceeded错误原因:
 *    两个方法只所以造成的结果不同，javaHeapSpace方法不断生成字符串,同时触发GC,GC处理速度大于对象生成速度,所以GC能成功执行且可以看出并没有触发Error
 *    overHeadLimitExceeded方法因为是不断给list添加元素,GC虽不断触发但是对象并没有回收,即每次处理结果作用不大,所以造成不断消耗资源GC,但资源分配达到
 *    98%机会触发该Error
 */
public class OOMErrorDemo {

    static class OOMtest{
        static {
            System.out.println("**********");
        }
    }

    public static void stackOverFlowError() {
        stackOverFlow();
    }

    private static void stackOverFlow() {
        stackOverFlow();
    }

    private static void javaHeapSpace() {
        Random random = new Random();
        String str = "atguigu";
        while (true) {
            str = str + random.nextInt(111111111) + random.nextInt(22222222);
            str.intern();
        }
//        Byte[] bytes = new Byte[20*1024*1024];
    }

    private static void overHeadLimitExceeded() {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static void directBufferMemory() {
        //该方法将对象分配在直接内存中,不由JVM管控
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2*1024*1024);
    }

    public static void metaSpace(){
        int i=0;
        try{
            while(true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMtest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,objects);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable e){
            System.out.println("***********多少次后发生了异常"+i);
            e.printStackTrace();
        }
    }

    private static void unableToCreateNewNativeThread() {
//        for(int i=0;;i++){
//            System.out.println(i++);
//            new Thread(()->{
//                try {
//                    Thread.sleep(Integer.MAX_VALUE);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
    }

    public static void main(String[] args) {
        //栈内存溢出,方法递归,会不断申请栈,超出栈最大值
//        stackOverFlowError();
        //堆内存溢出
//        javaHeapSpace();
        //该错误是JVM超过98%资源在进行垃圾回收
//        overHeadLimitExceeded();
        //直接内存溢出,即分配内存不由JVM分配,而分配在元空间
        directBufferMemory();
        //创建线程超过进程允许分配的最大线程数,暂时别尝试执行
//        unableToCreateNewNativeThread();
        //此原因是元空间内存不够用,该方法会不停创造内部类信息,类加载信息存放在元空间
        metaSpace();
    }
}
