package com.sence.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/10/20.
 */
public class DateUtils {
    public static Date getCurrentTime() {
        Date curDate = new Date(System.currentTimeMillis());
        return curDate;
    }

    private static final long INTERVAL_IN_MILLISECONDS = 30 * 1000;

    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getCurrentWeek() {
        String week = "星期";
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY:
                week += "日";
                break;
            case Calendar.MONDAY:
                week += "一";
                break;
            case Calendar.TUESDAY:
                week += "二";
                break;
            case Calendar.WEDNESDAY:
                week += "三";
                break;
            case Calendar.THURSDAY:
                week += "四";
                break;
            case Calendar.FRIDAY:
                week += "五";
                break;
            case Calendar.SATURDAY:
                week += "六";
                break;
        }
        return week;
    }

    public static String getWeekByTime(long time) {
        String strWeek = "";
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(time);
        int day = calendar1.get(Calendar.DAY_OF_WEEK);
        int week = calendar1.get(Calendar.WEEK_OF_YEAR);
        int curWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int curDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if (curWeek == week) {
            strWeek = "周";
            if (curDay == day) {
                return "今日";
            } else if (curDay == Calendar.SUNDAY) {
                strWeek = "下周";
            }
        } else if (week == curWeek + 1) {
            strWeek = "下周";
            if (day == Calendar.SUNDAY) {
                strWeek = "周";
            }
        } else {
            return new SimpleDateFormat("M月d日", Locale.CHINA).format(new Date());
        }
        switch (day) {
            case Calendar.SUNDAY:
                strWeek += "日";
                break;
            case Calendar.MONDAY:
                strWeek += "一";
                break;
            case Calendar.TUESDAY:
                strWeek += "二";
                break;
            case Calendar.WEDNESDAY:
                strWeek += "三";
                break;
            case Calendar.THURSDAY:
                strWeek += "四";
                break;
            case Calendar.FRIDAY:
                strWeek += "五";
                break;
            case Calendar.SATURDAY:
                strWeek += "六";
                break;
        }
        return strWeek;
    }

    public static String toTimeBySecond(long timeLength) {
        int minute = (int) timeLength / 60;
        int hour = 0;
        if (minute >= 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        int second = (int) timeLength % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public static int toTimeBySecond(int timeLength) {
        int minute = timeLength / 60;
        return minute;
    }

    public static long getTime(String strTime) {
        long time = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(strTime);
            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time / 1000;
    }

    public static String getStringByTimeTamp(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Long time = new Long(l);
        String d = format.format(time);
        return d;
    }

    public static String getStringByTimeTamp3(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Long time = new Long(l);
        String d = format.format(time);
        return d;
    }

    public static String getStringByTimeTamp1(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Long time = new Long(l);
        String d = format.format(time);
        return d;
    }

    public static String getStringByTimeTamp2(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = new Long(l);
        String d = format.format(time);
        return d;
    }

    public static String dateToString() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tablename = dateFormat.format(now);
        return tablename;
    }

    public static String dealFloat(float f) {
        if (f == 0) {
            return "0.00";
        }
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String format = decimalFormat.format(f);
        return format;
    }

    /**
     * 时间格式化器
     *
     * @param time
     * @return
     */
    public static String change(String time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(time + "000"));

        int month = (c.get(Calendar.MONTH));
        String year = String.valueOf(c.get(Calendar.YEAR));
        String day = String.valueOf(c.get(Calendar.DATE));
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(c.get(Calendar.MINUTE));

        return (month + 1) + "月" + day + "日" + hour + "时";
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param time
     * @return
     */
    public static String convertTimeToFormat(long time) {
//        long curTime = System.currentTimeMillis() / (long) 1000;
//        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return "1分钟前";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return "30天前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return "30天前";
        } else {
            return "1分钟前";
        }
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param time
     * @return
     */
    public static String convertTimeToFormatDate(long time) {
//        long curTime = System.currentTimeMillis() / (long) 1000;
//        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return "1分钟前";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "1分钟前";
        }
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param time
     * @return
     */
    public static String chatToFormatDate(long time) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        int curHour = Integer.parseInt(formatDateHourNoMinute(curTime));
        long timeStamp = curTime - time;
        long hour = timeStamp / 3600;//xiaoshi

        if (hour > curHour && hour < (curHour + 24)) {//代表昨天
            return "昨天 " + formatDateHour(time);
        } else if (hour > (curHour + 24)) {//前天以前 显示日期
            return formatDateDetail(time * 1000);
        } else {//代表今天
            return formatDateHour(time);
        }

    }


    public static String convertTimeNew(long diff_time, long now_time, long add_time) {
        String zero_time = formatDate(now_time);
        long zero_temp = dateTotemp(zero_time) / 1000;
        if (add_time < zero_temp) {
            if ((zero_temp - add_time) >= 3600 * 24) {
                if ((zero_temp - add_time) >= 3600 * 24 * 365) {
                    return formatDate(add_time);
                }
                return formatDateMoth(add_time);
            } else {
                return "昨天" + formatDateHour(add_time);
            }
        } else {
            if (diff_time < 60 && diff_time >= 0) {
                return "1分钟前";
            } else if (diff_time >= 60 && diff_time < 3600) {
                return diff_time / 60 + "分钟前";
            } else if (diff_time >= 3600 && diff_time < 3600 * 2) {
                return diff_time / 3600 + "小时前";
            } else if (diff_time >= 3600 * 2 && diff_time < 3600 * 24) {
                return formatDateHour(add_time);
            }
        }
        return "1分钟前";
    }


    /**
     * 得到日期   yyyy-MM-dd
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static String formatDate(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * 得到日期   yyyy-MM-dd
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static String formatDateMoth(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    public static String formatDateDetail(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Long time = new Long(l);
        String d = format.format(time);
        return d;
    }

    /**
     * 得到日期   yyyy-MM-dd
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static String formatDateHour(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    public static String formatDateMinute(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    public static String formatDateYear(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    public static String formatDateMonth(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    public static String formatDateHourNoMinute(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    public static String formatDateDay(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }


    public static long dateTotemp(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static long dateToTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


}
