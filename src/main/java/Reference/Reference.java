package Reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Reference {

    /**
     * 强引用
     */
    public static void  reference(){
        Object o1=new Object();
        Object o2 = o1;
        o1=null;
        System.gc();
        try{
            Byte[] bytes = new Byte[10*1024*1024];
        }finally {
            System.out.println(o1);
            System.out.println(o2);
        }
    }

    /**
     * 软引用,且内存够用
     */
    public static void  softReferenceMemoryEnough(){
        Object o1=new Object();
        SoftReference<Object> o2 = new SoftReference<>(o1);
        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(o2.get());

    }

    /**
     * 软引用,且内存不够用
     */
    public static void  softReferenceMemoryNotEnough(){
        Object o1=new Object();
        SoftReference<Object> o2 = new SoftReference<>(o1);
        o1=null;
        System.gc();
        try{
            Byte[] bytes = new Byte[10*1024*1024];
        }finally {
            System.out.println(o1);
            System.out.println(o2.get());
        }
    }

    /**
     * 弱引用
     */
    public static void  weekReference(){
        Object o1=new Object();
        WeakReference<Object> o2 = new WeakReference<>(o1);
        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(o2.get());
    }

    public static void main(String[] args) {
        //强引用对象,无论内存是否足够都不会被回收
//        reference();
        //比较输出结果可以看出,对于软引用对象,若内存不够,引用对象将被回收.
//        softReferenceMemoryEnough();
//        softReferenceMemoryNotEnough();
        //弱引用对象,无论内存是否只要进行垃圾回收一定会被被回收
        weekReference();
    }
}
