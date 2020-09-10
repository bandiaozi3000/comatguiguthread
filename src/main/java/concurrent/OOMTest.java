package concurrent;

/**
 * JVM命令参数:-Xms10M -Xmx10M -XX:+PrintGCDetails
 */
public class OOMTest {
    public static void main(String[] args) {
        Byte[] bytes = new Byte[10*1024*1024];
    }
}
