package shangguigu;

import java.lang.ThreadLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:SimpleDateFormatTest
 * @Description:日期
 * @Author:lm.sun
 * @Date:2019/12/18 10:03
 */
public class SimpleDateFormatTest {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static java.lang.ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
//                    synchronized (simpleDateFormat){
//                        try {
//                            System.out.println(simpleDateFormat.parse("2017-12-13 15:17:27"));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }

                    System.out.println(simpleDateFormat.format(new Date()));
                    try {
                        System.out.println(threadLocal.get().parse("2017-01-02 13:12:12"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }finally {
                        threadLocal.remove();
                    }
                }

//                }
            });
            thread.start();
        }
    }
}
