package com.lwh147.rtms.backstage.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @description: 日期时间工具类
 * @author: lwh
 * @create: 2021/5/3 20:23
 * @version: v1.0
 **/
public class DateTimeUtil {
    private static final int ZERO = 0;

    private DateTimeUtil() {

    }

    /**
     * 获得指定日期添加指定天数后的0点(昨天0点,前天0点,明天0点,后天0点,十天后零点)
     *
     * @param date   指定日期
     * @param amount 加上几天
     * @return Date
     **/
    public static Date getPlusDayInitial(Date date, int amount) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);
        c.set(Calendar.HOUR_OF_DAY, ZERO);
        c.set(Calendar.MINUTE, ZERO);
        c.set(Calendar.SECOND, ZERO);
        c.set(Calendar.MILLISECOND, ZERO);
        return c.getTime();
    }
}
