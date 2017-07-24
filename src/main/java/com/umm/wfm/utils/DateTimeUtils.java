package com.umm.wfm.utils;

import com.umm.wfm.security.log.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

/**
 * @author jiangjunhou
 */
public class DateTimeUtils {

    /**
     * 工具类不可实例化
     */
    private DateTimeUtils() {
    }

    /**
     * 标准日期格式
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    public final static String TIME_PATTERN = "HH:mm:ss";

    /**
     * 标准日期时分秒毫秒格式
     */
    public final static String DATETIME_MILL_SECOND = "yyyy-MM-dd HH:mm:ss.SSS";

    public final static String DATETIME_M = "yyyy-MM-dd HH:mm";

    /**
     * 标准时间格式
     */
    public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 美式/英式时间格式
     */
    public final static String DATETIME_PATTERN_EN = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * Number of milliseconds in a standard second.
     */
    public static final long MILLIS_PER_SECOND = 1000;

    /**
     * Number of milliseconds in a standard minute.
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    /**
     * Number of milliseconds in a standard hour.
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    /**
     * Number of milliseconds in a standard day.
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    /**
     * 标准年月格式
     */
    public final static String MONTH_PATTERN = "yyyy-MM";

    private final static String[] WEEK_NAMES = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"};

    private static final Logger LOG = LogUtil.getLogger(DateTimeUtils.class);

    /**
     * 一天的毫秒数
     */
    public static final int TIME_MILLIS_OF_DAY = 86400000;

    /**
     * UTC时区+8区时间和GMT时区相差毫秒数
     */
    public static final int TIME_MILLS_UTC_TO_GMT = 28800000;

    /**
     * 根据开始时间、结束时间得到两个时间段内所有的日期
     *
     * @param start        开始日期
     * @param end          结束日期
     * @param calendarType 类型
     * @return 两个日期之间的日期
     */
    public static List<Date> getDateArrays(Date start, Date end, int calendarType) {
        List<Date> returnDates = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Date tmpDate = calendar.getTime();
        long endTime = end.getTime();
        while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
            returnDates.add(calendar.getTime());
            calendar.add(calendarType, 1);
            tmpDate = calendar.getTime();
        }
        return returnDates;
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return DateUtils.parseDate(dateStr, pattern);
    }

    public static Date parseDate(String dateStr) throws ParseException {
        return parse(dateStr, DATE_PATTERN);
    }

    public static Date parseDateSilence(String str, String parsePattern) {
        try {
            return org.apache.commons.lang.time.DateUtils.parseDate(str, new String[]{parsePattern});
        } catch (ParseException e) {
            LOG.error(str, e);
        }
        return null;
    }

    public static Date parseDateSilence(String str) {
        return parseDateSilence(str, "yyyy-MM-dd");
    }

    public static Date parseDate2(String str) {
        return parseDateSilence(str, "yyyy-MM-dd hh:mm:ss");
    }

    public static Date parseDate3(String str) {
        return parseDateSilence(str, "hh:mm:ss");
    }

    /**
     * 获得昨天的日期
     *
     * @return
     */
    public static Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static int getDayBetween(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        int dayCount = (int) ((end.getTimeInMillis() - start.getTimeInMillis() + 24 * 60 * 60 * 1000) / 24 / 60 / 60 / 1000) - 1;

        return dayCount;
    }

    // List of all date formats that we want to parse.
    // Add your own format here.
    private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {
        private static final long serialVersionUID = 1L;

        {
            add(new SimpleDateFormat("yyyy-MM-dd"));
            add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            add(new SimpleDateFormat("yyyy/M/dd"));
            add(new SimpleDateFormat("yyyy/M/dd HH:mm"));
            add(new SimpleDateFormat("yyyy/M/dd HH:mm:ss"));
            add(new SimpleDateFormat("yyyy/M/dd"));
            add(new SimpleDateFormat("M/dd/yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MM.yyyy"));
            add(new SimpleDateFormat("dd-MM-yyyy"));
        }
    };

    /**
     * Convert String with various formats into java.util.Date
     *
     * @param input Date as a string
     * @return java.util.Date object if input string is parsed successfully else
     * returns null
     */
    public static Date convertToDate(String input) {
        Date date = null;
        if (null == input) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
                // Shhh.. try other formats
            }
            if (date != null) {
                break;
            }
        }

        return date;
    }

    /**
     * 判断字符串是不是日期时间格式 "yyyy-MM-dd hh:mm"
     *
     * @param dateTime
     * @throws ParseException
     */
    public static boolean isDateTime(String dateTime) {
        boolean result = true;
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            dateTimeFormat.parse(dateTime);
        } catch (ParseException e) {
            result = false;
            LOG.error("Validate date time failed.", e);
        }
        return result;
    }

    /**
     * 获取时间间隔数据 天、时、分
     *
     * @param startDate
     * @param endDate
     * @param startTime
     * @param endTime
     * @return 天数、小时、分钟间隔，中间以逗号分隔 如5,4,41 表示间隔 5天4小时41分钟
     */
    public static String getDuration(Date startDate, Date endDate, String startTime, String endTime) {
        String duration = "";
        try {
            long startDateTime = startDate.getTime() + new SimpleDateFormat("HH:mm").parse(startTime).getTime();
            long endDateTime = endDate.getTime() + new SimpleDateFormat("HH:mm").parse(endTime).getTime();
            long durationTime = endDateTime - startDateTime;
            long day = durationTime / (1000 * 60 * 60 * 24);
            long hour = (durationTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (durationTime % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60);
            duration = day + "," + hour + "," + minute;
        } catch (ParseException e) {
            LOG.error("Parse time error! startTime : " + startTime + ",endTime:" + endTime, e);
        }
        return duration;
    }

    /**
     * 获取时间间隔或时间之和
     *
     * @param diffTime
     * @return 时间间隔 小时、分钟
     */
    public static String getHourAndMinute(long diffTime) {
        String resultTime = null;
        long hour = diffTime / (1000 * 60 * 60);
        long minute = diffTime % (1000 * 60 * 60) / (1000 * 60);
        resultTime = hour + ":" + minute;
        return resultTime;
    }

    /**
     * 获取标准日期格式 'yyyy-MM-dd'
     *
     * @param date
     * @return
     */
    public static String getStandartDateStr(Date date) {
        String dateStr = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateStr = dateFormat.format(date);
        return dateStr;
    }

    /**
     * 获取标准日期格式 'yyyy-MM-dd HH:mm:ss'
     *
     * @param date
     * @return
     */
    public static String getStandartDateTimeStr(Date date) {
        String dateStr = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateStr = dateFormat.format(date);
        return dateStr;
    }

    /**
     * 转换时间格式字符串如"2014-11-11 11:11:11"
     *
     * @param dateTimeStr
     * @return
     */
    public static Date parseDateTime(String dateTimeStr) {
        Date dateTime = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            dateTime = dateFormat.parse(dateTimeStr);
        } catch (ParseException e) {
            LOG.error("Parse date time failed,dateTimeStr : " + dateTimeStr, e);
        }
        return dateTime;
    }

    /**
     * 根据时间字符串（支持dateTime和time）获取时间间隔（HH:mm）
     *
     * @param beginTimeStr
     * @param endTimeStr
     * @return
     */
    public static String getDurationFromTimePair(String beginTimeStr, String endTimeStr) {
        String duration = null;
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        long durationTime = 0L;
        try {
            durationTime = dateTimeFormat.parse(endTimeStr).getTime() - dateTimeFormat.parse(beginTimeStr).getTime();
        } catch (ParseException e) {
            try {
                durationTime = timeFormat.parse(endTimeStr).getTime() - timeFormat.parse(beginTimeStr).getTime();
            } catch (ParseException e1) {
                LOG.error("Get Duratio From Time Pair failed,beginTimeStr : " + beginTimeStr + ",endTimeStr"
                        + endTimeStr, e);
            }
            // no thing
        }
        if (durationTime > 0) {
            duration = getHourAndMinute(durationTime);
        }
        return duration;
    }

    public static String formateDate(Date date) {
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateTimeFormat.format(date);
    }

    public static String formateDateToString(Date date) {
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return dateTimeFormat.format(date);
    }

    public static String formateDateToStr(Date date) {
        if (null == date) {
            return null;
        } else {
            DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return dateTimeFormat.format(date);
        }
    }

    public static String longFormat(long l) {
        String str = null;
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        str = s.format(l);
        return str;
    }

    /**
     * 将日期或者时间戳转化为日期对象
     *
     * @param date yyyy-MM-dd or yyyy-MM-dd HH:mm:ss or yyyy-MM-dd HH:mm:ss.SSS
     * @return
     */
    public static Date convertDate(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        if (date.indexOf(":") > 0) {
            return convertDate(date, DATETIME_PATTERN);
        } else if (date.indexOf(".") > 0) {
            return convertDate(date, DATETIME_MILL_SECOND);
        } else {
            return convertDate(date, DATE_PATTERN);
        }
    }

    /**
     * 将日期或者时间字符串转化为日期对象
     *
     * @param date    日期字符串
     * @param pattern 格式字符串</br> yyyy-MM-DD, yyyy/MM/DD, yyyyMMdd</br> yyyy-MM-dd-HH:mm:ss, yyyy-MM-dd HH:mm:ss
     *                格式字符串可选字符："GyMdkHmsSEDFwWahKzZ"
     * @return Date
     * @see DateFormatSymbols#patternChars
     */
    public static Date convertDate(String date, String pattern) {
        try {
            if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(date)) {
                String msg = "the date or pattern is empty.";
                throw new IllegalArgumentException(msg);
            }
            SimpleDateFormat df = new SimpleDateFormat(pattern.trim());
            return df.parse(date.trim());
        } catch (Exception e) {
            LOG.error("Method===DateTimeUtils.convertDate error!", e);
            return null;
        }
    }

    /**
     * 获得日期相差天数
     *
     * @param date1 日期
     * @param date2 日期
     * @return
     */
    public static int diffDate(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / MILLIS_PER_DAY);
    }

    /**
     * 字符串的日期格式的计算
     */
    public static long daysAfterNow(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return LocalDate.now().until(localDate, ChronoUnit.DAYS);
    }

    /**
     * 按指定格式字符串格式时间
     *
     * @param date    日期或者时间
     * @param pattern 格式化字符串 yyyy-MM-dd， yyyy-MM-dd HH:mm:ss, yyyy年MM月dd日 etc.</br>
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern.trim());
        return format.format(date);
    }

    public static String formatDatetime(Date date) {
        return org.apache.commons.lang3.time.DateFormatUtils.format(date, DATETIME_PATTERN);
    }

    public static String formatDate(Date date) {
        return org.apache.commons.lang3.time.DateFormatUtils.format(date, DATE_PATTERN);
    }

    /**
     * Description: 获取开始时间和结束时间之间的时间间隔<br>
     *
     * @param startTime
     * @param endTinme
     * @return <br>
     * @throws ParseException
     * @author fanrui2<br>
     * @taskId <br>
     */
    public static long getTimeSpan(String startTime, String endTinme) throws ParseException {
        long start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime).getTime();
        long end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTinme).getTime();
        if (end >= start) {
            return end - start;
        } else {
            return start - end;
        }
    }

    public static String getYear(String month) {
        int index = getIndexOfMonth(month);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (index < Calendar.getInstance().get(Calendar.MONTH)) {
            year += 1;
        }
        String shortNumber = ("" + year).substring(2, 4);
        return shortNumber;
    }

    public static int getIndexOfMonth(String month) {
        if ("JAN".equals(month)) {
            return 0;
        } else if ("FEB".equals(month)) {
            return 1;
        } else if ("MAR".equals(month)) {
            return 2;
        } else if ("APR".equals(month)) {
            return 3;
        } else if ("MAY".equals(month)) {
            return 4;
        } else if ("JUN".equals(month)) {
            return 5;
        } else if ("JUL".equals(month)) {
            return 6;
        } else if ("AUG".equals(month)) {
            return 7;
        } else if ("SEP".equals(month)) {
            return 8;
        } else if ("OCT".equals(month)) {
            return 9;
        } else if ("NOV".equals(month)) {
            return 10;
        } else if ("DEC".equals(month)) {
            return 11;
        }
        return 0;
    }

    /**
     * Description: 月份转换：英文三字码-->两位数字<br>
     *
     * @param month
     * @return <br>
     * @author lanlugang<br>
     * @date 2015-7-8
     * @taskId PTICKET-1509<br>
     */
    public static String parseMonthStr2Num(String month) {
        month = month.toUpperCase();
        if (month.equals("JAN")) {
            month = "01";
        } else if (month.equals("FEB")) {
            month = "02";
        } else if (month.equals("MAR")) {
            month = "03";
        } else if (month.equals("APR")) {
            month = "04";
        } else if (month.equals("MAY")) {
            month = "05";
        } else if (month.equals("JUN")) {
            month = "06";
        } else if (month.equals("JUL")) {
            month = "07";
        } else if (month.equals("AUG")) {
            month = "08";
        } else if (month.equals("SEP")) {
            month = "09";
        } else if (month.equals("OCT")) {
            month = "10";
        } else if (month.equals("NOV")) {
            month = "11";
        } else if (month.equals("DEC")) {
            month = "12";
        }
        return month;
    }

    public static String convertDateFormNameToNum(String date) {
        StringBuffer dateStr = new StringBuffer();
        String month = parseMonthStr2Num(date.substring(2, 5));
        dateStr.append("20").append(date.substring(5, 7)).append("-")
                .append(month).append("-").append(date.substring(0, 2));
        return dateStr.toString();
    }

    //判断输入日期是周几
    public static int getWeekFromDate(String sDate) {
        Date date = formatDateEn(sDate, "yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (Calendar.MONDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return 1;
        }
        if (Calendar.TUESDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return 2;
        }
        if (Calendar.WEDNESDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return 3;
        }
        if (Calendar.THURSDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return 4;
        }
        if (Calendar.FRIDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return 5;
        }
        if (Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return 6;
        }
        if (Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return 7;
        }
        return 1;
    }

    /**
     * add by sunzuoshun 20120403 此方法描述的是：将文本格式的日期转化为指定的文本日期格式，以适合FD查询
     *
     * @param date 日期, format 日期格式
     * @return String 文本格式日期
     */

    public static String dateConvert(Date date, String format) {
        String ds = "";
        SimpleDateFormat sdf = null;

        sdf = new SimpleDateFormat(format);
        ds = sdf.format(date);

        return ds;
    }

    public static Date formatDateEn(String date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new DateFormatSymbols(Locale.ENGLISH));
        Date returnDate = null;
        try {
            returnDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            LOG.error("日期转" + pattern + "格式失败:" + date);
        }
        return returnDate;
    }

    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    public static Date addMilliseconds(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    public static Date add(Date date, int calendarFiled, int amount) {
        if (null == date) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (amount == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarFiled, amount);
        return c.getTime();
    }

    /**
     * 按指定roundType格式化日期。
     *
     * @param date      日期
     * @param roundType
     * @return Date
     * @see Calendar#MONTH,Calendar#DATE,Calendar#HOUR,Calendar#MINUTE,Calendar#SECOND
     */
    public static Date round(Date date, int roundType) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime());
        switch (roundType) {
            case Calendar.MONTH:
                c.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case Calendar.DATE:
                c.set(Calendar.HOUR_OF_DAY, 0);
                break;
            case Calendar.HOUR:
                c.set(Calendar.MINUTE, 0);
                break;
            case Calendar.MINUTE:
                c.set(Calendar.SECOND, 0);
                break;
            case Calendar.SECOND:
                c.set(Calendar.MILLISECOND, 0);
                break;
            default:
                throw new IllegalArgumentException("invalid round roundType.");
        }
        return c.getTime();
    }

    /**
     * 计算周岁
     *
     * @param dateNow      当前时间
     * @param dateBirthDay 生日时间
     * @return 周岁
     */
    public static int calculateAge(Date dateNow, Date dateBirthDay) {
        Calendar calendarBirth = Calendar.getInstance();
        calendarBirth.setTime(dateBirthDay);
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(dateNow);
        int age = calendarNow.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR);
        if (calendarNow.get(Calendar.MONTH) < calendarBirth.get(Calendar.MONTH)) {
            age--;
        } else if (calendarNow.get(Calendar.MONTH) == calendarBirth.get(Calendar.MONTH)
                && calendarNow.get(Calendar.DAY_OF_MONTH) < calendarBirth.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    public static String str2strdate(String date) {
        return date.substring(8, 10) + convertMonth(date.substring(5, 7)) + date.substring(2, 4);
    }

    public static String convertMonth(String month) {
        switch (Integer.parseInt(month)) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DEC";
        }
        return month;
    }

    /**
     * 转换日期为IBE固有格式 Locale.ENGLISH
     *
     * @param date
     * @return String
     * @author mujunfeng
     * @since 2014-11-27
     */
    public static String convertStrDate(String date) {
        String dateStr = "";
        StringBuffer str = new StringBuffer();
        String month = parseMonthStr2Num(date.substring(2, 5));
        String day = date.substring(0, 2);
        String year = "20" + date.substring(5, 7);
        String hour = date.substring(8, 10);
        String minute = date.substring(10, 12);
        str.append(year).append("-").append(month).append("-").append(day)
                .append(" ").append(hour).append(":").append(minute)
                .append(":00");
        dateStr = str.toString();
        return dateStr;
    }

    /**
     * 转化 yyyy-MM-dd 至 01SEP16 格式
     *
     * @param date yyyy-MM-dd
     * @return 01SEP16
     */
    public static String dateToSeven(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, ISO_LOCAL_DATE);
            String dateString = localDate.format(DateTimeFormatter.ofPattern("ddMMMyy", Locale.ENGLISH));
            return dateString.toUpperCase();
        } catch (Exception ex) {
            throw new RuntimeException("转化日期 yyyy-MM-dd 至 ddMMMyy 错误，输入：" + date, ex);
        }
    }

    public static String sevenToDate(String seven) {
        try {
            StringBuilder formatDate = new StringBuilder();
            formatDate.append(seven.substring(0, 2))
                    .append(StringUtils.capitalize(seven.substring(2, 5).toLowerCase()))
                    .append(seven.substring(5));
            LocalDate localDate = LocalDate.parse(formatDate, DateTimeFormatter.ofPattern("ddMMMyy", Locale.ENGLISH));
            String dateString = localDate.format(ISO_LOCAL_DATE);
            return dateString.toUpperCase();
        } catch (Exception ex) {
            throw new RuntimeException("转化日期 ddMMMyy 至 yyyy-MM-dd 错误，输入：" + seven, ex);
        }
    }

    public static String convert2EnFormatDateStr(String dateStr) {
        try {
            return convert2EnFormatDateStr(dateStr, DATETIME_PATTERN_EN);
        } catch (ParseException e) {
            LOG.error("convert2EnFormatDateStr日期转EEE MMM dd HH:mm:ss zzz yyyy格式失败:" + dateStr, e);
            return null;
        }
    }

    /**
     * Description: 标准日期格式转成英式日期格式<br>
     *
     * @param dateStr
     * @return <br>
     * @throws ParseException
     * @author lanlugang<br>
     * @taskId <br>
     */
    public static String convert2EnFormatDateStr(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String enFormatDateStr = null;
        enFormatDateStr = org.apache.commons.lang3.time.DateFormatUtils
                .format(dateFormat.parse(dateStr), DATETIME_PATTERN_EN, Locale.ENGLISH);
        return enFormatDateStr;
    }

}

