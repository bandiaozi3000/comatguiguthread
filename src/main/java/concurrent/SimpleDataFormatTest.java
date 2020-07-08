package concurrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class SimpleDataFormatTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateStr = "2020-04-02 09:28:14";
        for(int i=0;i<100;i++){
            Date date = new Date();
            executorService.execute(()->{
                System.out.println(simpleDateFormat.format(date));
                try {
                    System.out.println(simpleDateFormat.parse(dateStr));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(localDateTime.format(dateTimeFormatter));
                System.out.println(dateTimeFormatter.parse(dateStr));
            });
        }
    }
}
