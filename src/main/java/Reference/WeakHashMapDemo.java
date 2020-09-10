package Reference;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void hashMap() {
        HashMap<Integer,String> hashMap= new HashMap<>();
        Integer integer = new Integer(1);
        hashMap.put(integer,"hashMap");
        System.out.println(hashMap);
        integer = null;
        System.out.println(hashMap);
        System.gc();
        System.out.println(hashMap);
    }

    public static void weakHashMap() {
        WeakHashMap<Integer,String> hashMap= new WeakHashMap();
        Integer integer = new Integer(1);
        hashMap.put(integer,"hashMap");
        System.out.println(hashMap);
        integer = null;
        System.out.println(hashMap);
        System.gc();
        System.out.println(hashMap);
    }

    public static void main(String[] args) {
        hashMap();
        System.out.println("***********************");
        weakHashMap();
    }
}
