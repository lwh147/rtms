import cn.hutool.core.lang.Snowflake;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @description: 数据生成类
 * @author: lwh
 * @create: 2021/5/9 20:22
 * @version: v1.0
 **/
public class Test {
    private static final Snowflake snowflake = new Snowflake(1, 1);

    public static void main(String[] args) throws ParseException {
        getId(1000);
    }

    public static void getTime(int num) throws ParseException {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 生成时间格式
        // 指定开始时间与结束时间
        String start = "2021-04-15 00:00:00";
        String end = "2021-05-09 23:59:59";
        // 把时间格式的字符串改为时间戳
        long l1 = s.parse(start).getTime();
        long l2 = s.parse(end).getTime();
        for (int i = 0; i < num; i++) {
            long time = (long) ((l2 - l1) * Math.random() + l1);// 得到两个日期之间日期的时间戳
            System.out.println(s.format(time));// 把时间戳改为时间格式
        }
    }

    public static void getTemp(int num) {
        for (int i = 0; i < num; i++) {
            float temp = (float) (1.1f * Math.random() + 36.0f);
            System.out.println(temp);
        }
        for (int i = 0; i < num / 100 * 10; i++) {
            float temp = (float) (3.0f * Math.random() + 36.0f);
            System.out.println(temp);
        }
        for (int i = 0; i < num / 100 * 3; i++) {
            float temp = (float) (4.0f * Math.random() + 36.0f);
            System.out.println(temp);
        }
    }

    public static void getId(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println(snowflake.nextId());
        }
    }
}
