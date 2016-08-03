package lab.sharp.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ClassName:DateUtils <br/>
 * Reason: 日期时间工具类. <br/>
 * Date: 2014-9-15 11:45:26<br/>
 * 后续如有更多功能以追加的形式补录
 * @author 邢凌霄
 * @version 1.0
 * @since JDK 1.5
 * @see
 */
public class DateUtils {
	private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	 	public final static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    private static final SimpleDateFormat dateFormatSlash = new SimpleDateFormat("yyyy/MM/dd");
	    private static final SimpleDateFormat timeFormat = new SimpleDateFormat( "HH:mm:ss");
	    
	    private static final Calendar calendar = Calendar.getInstance();
	    
	    /**
	     * 获得当前日期时间
	     * 
	     * 日期时间格式yyyy-MM-dd HH:mm:ss
	     * 
	     * @return
	     */
	    public static String currentDatetime() {
	        return datetimeFormat.format(now());
	    }
	    /**
	     * 格式化日期时间
	     * <p>
	     * 日期时间格式yyyy-MM-dd HH:mm:ss
	     * 
	     * 将DateTime类型转换成String类型
	     * @return
	     */
	    public static String formatDatetime(Date date) {
	        return datetimeFormat.format(date);
	    }
	    
	    /**
	     * 格式化日期时间
	     * <p>
	     * 日期时间格式yyyy-MM-dd HH:mm:ss
	     * 将String类型转换成DateTime类型
	     * @return
	     * @throws ParseException 
	     */
	    public static Date formatDatetime(String dateTime) throws ParseException {
	        return datetimeFormat.parse(dateTime);
	    }
	    
	    /**
	     * 格式化日期时间
	     * 
	     * @param date
	     * @param pattern
	     *  格式化模式，详见{@link SimpleDateFormat}构造器
	     *  <code>SimpleDateFormat(String pattern)</code>
	     * @return
	     */
	    public static String formatDatetime(Date date, String pattern) {
	        SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat.clone();
	        customFormat.applyPattern(pattern);
	        return customFormat.format(date);
	    }
	    /**
	     * 获得当前日期
	     * <p>
	     * 日期格式yyyy-MM-dd
	     * 
	     * @return
	     */
	    public static String currentDate() {
	        return dateFormat.format(now());
	    }

	    /**
	     * 格式化日期
	     * <p>
	     * 日期格式yyyy-MM-dd
	     * 将Date转换成String
	     * @return
	     */
	    public static String formatDate(Date date) {
	        return dateFormat.format(date);
	    }
	    
	    /**
	     * 格式化日期
	     * <p>
	     * 日期格式yyyy-MM-dd
	     * 将String转换成Date
	     * @return
	     * @throws ParseException 
	     */
	    public static Date formatStringToDate(String date) throws ParseException {
	        return dateFormat.parse(date);
	    }
	    
	    /**
	     * 格式化日期
	     * <p>
	     * 转换前String类型格式为yyyy-MM-dd
	     * 转换后Date类型格式为yyyy/MM/dd
	     * 将String转换成Date
	     * @return
	     * @throws ParseException 
	     */
	    public static Date formatDate(String date) throws ParseException {
	        return (Date)dateFormatSlash.parseObject(date);
	    }
	    /**
	     * 获得当前时间
	     * <p>
	     * 时间格式HH:mm:ss
	     * 
	     * @return
	     */
	    public static String currentTime() {
	        return timeFormat.format(now());
	    }
	    
	    /**
	     * 格式化时间
	     * <p>
	     * 时间格式HH:mm:ss
	     * 
	     * @return
	     */
	    public static String formatTime(Date date) {
	        return timeFormat.format(date);
	    }
	    
	    /**
	     * 获得当前时间的<code>java.util.Date</code>对象
	     * 
	     * @return
	     */
	    public static Date now() {
	        return new Date();
	    }

	    public static Calendar calendar() {
	        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
	        cal.setFirstDayOfWeek(Calendar.MONDAY);
	        return cal;
	    }

	    /**
	     * 获得当前时间的毫秒数
	     * <p>
	     * 详见{@link System#currentTimeMillis()}
	     * 
	     * @return
	     */
	    public static long millis() {
	        return System.currentTimeMillis();
	    }

	    /**
	     * 
	     * 获得当前Chinese月份
	     * 
	     * @return
	     */
	    public static int month() {
	        return calendar().get(Calendar.MONTH) + 1;
	    }

	    /**
	     * 获得月份中的第几天
	     * 
	     * @return
	     */
	    public static int dayOfMonth() {
	        return calendar().get(Calendar.DAY_OF_MONTH);
	    }

	    /**
	     * 今天是星期的第几天
	     * 
	     * @return
	     */
	    public static int dayOfWeek() {
	        return calendar().get(Calendar.DAY_OF_WEEK);
	    }

	    /**
	     * 今天是年中的第几天
	     * 
	     * @return
	     */
	    public static int dayOfYear() {
	        return calendar().get(Calendar.DAY_OF_YEAR);
	    }

	    /**
	     *判断原日期是否在目标日期之前
	     * 
	     * @param src
	     * @param dst
	     * @return
	     */
	    public static boolean isBefore(Date src, Date dst) {
	        return src.before(dst);
	    }

	    /**
	     *判断原日期是否在目标日期之后
	     * 
	     * @param src
	     * @param dst
	     * @return
	     */
	    public static boolean isAfter(Date src, Date dst) {
	        return src.after(dst);
	    }

	    /**
	     *判断两日期是否相同
	     * 
	     * @param date1
	     * @param date2
	     * @return
	     */
	    public static boolean isEqual(Date date1, Date date2) {
	        return date1.compareTo(date2) == 0;
	    }

	    /**
	     * 判断某个日期是否在某个日期范围
	     * 
	     * @param beginDate
	     *            日期范围开始
	     * @param endDate
	     *            日期范围结束
	     * @param src
	     *            需要判断的日期
	     * @return
	     */
	    public static boolean between(Date beginDate, Date endDate, Date src) {
	        return beginDate.before(src) && endDate.after(src);
	    }
	    /**
	     * 获得当前月的第一天
	     * <p>
	     * HH:mm:ss SS为零
	     * 日期格式yyyy-MM-dd HH:mm:ss SS
	     * 
	     * @return
	     */
	    public static Date firstDayOfMonthDateTime(Date dtDate) {
	    	if(dtDate==null){
	    		return null;
	    	}
	        Calendar cal = calendar();
	        cal.setTime(dtDate);
	        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
	        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
	        cal.set(Calendar.MINUTE, 0);// m置零
	        cal.set(Calendar.SECOND, 0);// s置零
	        cal.set(Calendar.MILLISECOND, 0);// S置零
	        return cal.getTime();
	    }
	    
	    /**
	     * 获得当前月的第一天
	     * <p>
	     * 日期格式yyyy-MM-dd
	     * 
	     * @return  String 
	     * @throws ParseException 
	     */
	    public static String firstDayOfMonthDateString(Date dtDate) throws ParseException {
	    	if(dtDate==null){
	    		return null;
	    	}
	        Calendar cal = calendar();
	        cal.setTime(dtDate);
	        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
	        return dateFormat.format(cal.getTime());
	    }
	    /**
	     * 获得当前月的第一天
	     * <p>
	     * 日期格式yyyy-MM-dd
	     * 
	     * @return  Date
	     * @throws ParseException 
	     * @throws ParseException 
	     */
	    public static Date firstDayOfMonthDate(Date dtDate) throws ParseException{
	    	if(dtDate==null){
	    		return null;
	    	}
	    	Calendar cal = calendar();
	    	cal.setTime(dtDate);
	    	cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
	    	return dateFormat.parse(dateFormat.format(cal.getTime()));
	    }
	    /**
	     * 获得当前月的最后一天
	     * <p>
	     * HH:mm:ss为0，毫秒为999
	     * 日期格式yyyy-MM-dd HH:mm:ss SS
	     * @return
	     */
	    public static Date lastDayOfMonthDateTime(Date dtDate) {
	    	if(dtDate==null){
	    		return null;
	    	}
	    	Calendar cal = calendar();
	    	cal.setTime(dtDate);
	    	cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
	    	cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
	    	cal.setTime(before(cal.getTime(),1));
	        return cal.getTime();
	    }
	    /**
	     * 获得当前月的最后一天
	     * <p>
	     * 日期格式yyyy-MM-dd 
	     * @return  String
	     * @throws ParseException 
	     */
	    public static String lastDayOfMonthDateString(Date dtDate) throws ParseException {
	    	if(dtDate==null){
	    		return null;
	    	}
	    	Calendar cal = calendar();
	    	cal.setTime(dtDate);
	    	cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
	    	cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
	    	cal.setTime(before(cal.getTime(),1));
	    	return dateFormat.format(cal.getTime());
	    }
	    /**
	     * 获得当前月的最后一天
	     * <p>
	     * 日期格式yyyy-MM-dd 
	     * @return Date
	     * @throws ParseException 
	     */
	    public static Date lastDayOfMonthDate(Date dtDate) throws ParseException {
	    	if(dtDate==null){
	    		return null;
	    	}
	    	Calendar cal = calendar();
	    	cal.setTime(dtDate);
	    	cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
	    	cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
	    	cal.set(Calendar.MINUTE, 0);// m置零
	    	cal.set(Calendar.SECOND, 0);// s置零
	    	cal.set(Calendar.MILLISECOND, 0);// S置零
	    	cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
	    	cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
	    	cal.setTime(before(cal.getTime(),1));
	    	return dateFormat.parse(dateFormat.format(cal.getTime()));
	    }
	    
	    /**
	     * 获得上一个月的第一天
	     * <p>
	     * 日期格式yyyy-MM-dd
	     * 
	     * @return
	     * @throws ParseException 
	     */
	    public static Date firstDayOfPriorMonth() throws ParseException {
	        Calendar cal = calendar();
	        cal.add(Calendar.MONTH, -1);
	        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
	        return dateFormat.parse(datetimeFormat.format(cal.getTime()));
	    }

	    private static Date weekDay(int week) {
	        Calendar cal = calendar();
	        cal.set(Calendar.DAY_OF_WEEK, week);
	        return cal.getTime();
	    }

	    /**
	     * 获得周五日期
	     * <p>
	     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	     * 
	     * @return
	     */
	    public static Date friday() {
	        return weekDay(Calendar.FRIDAY);
	    }

	    /**
	     * 获得周六日期
	     * <p>
	     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	     * 
	     * @return
	     */
	    public static Date saturday() {
	        return weekDay(Calendar.SATURDAY);
	    }

	    /**
	     * 获得周日日期
	     * <p>
	     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	     * 
	     * @return
	     */
	    public static Date sunday() {
	        return weekDay(Calendar.SUNDAY);
	    }

	    /**
	     * 将字符串日期时间转换成java.util.Date类型
	     * <p>
	     * 日期时间格式yyyy-MM-dd HH:mm:ss
	     * 
	     * @param datetime
	     * @return
	     */
	    public static Date parseDatetime(String datetime) throws ParseException {
	        return datetimeFormat.parse(datetime);
	    }

	    /**
	     * 将字符串日期转换成java.util.Date类型
	     *<p>
	     * 日期时间格式yyyy-MM-dd
	     * 
	     * @param date
	     * @return
	     * @throws ParseException
	     */
	    public static Date parseDate(String date) throws ParseException {
	        return dateFormat.parse(date);
	    }

	    /**
	     * 将字符串日期转换成java.util.Date类型
	     *<p>
	     * 时间格式 HH:mm:ss
	     * 
	     * @param time
	     * @return
	     * @throws ParseException
	     */
	    public static Date parseTime(String time) throws ParseException {
	        return timeFormat.parse(time);
	    }

	    /**
	     * 根据自定义pattern将字符串日期转换成java.util.Date类型
	     * 
	     * @param datetime
	     * @param pattern
	     * @return
	     * @throws ParseException
	     */
	    public static Date parseDatetime(String datetime, String pattern)throws java.text.ParseException {
	        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
	        format.applyPattern(pattern);
	        return format.parse(datetime);
	    }



	   
		/**
		 * 使用反射处理日期时，标准时间格式被转成String，需要用这个格式处理一下
		 * @param dateStr
		 * @return
		 */
	    public static Date timeZoneStrToDate(String dateStr){
			try{
				if(dateStr==null) return null;
				SimpleDateFormat  sdf = new SimpleDateFormat ("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
				Date date = sdf.parse(dateStr);
				
				return date;
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
	    

	    /**
	     * 根据输入的数字返回是中文的星期几 1:星期日 7：星期一
	     * @param dayOfWeek
	     * @return
	     */ 
		public static String getChineseWeekName(int dayOfWeek){
	    	switch(dayOfWeek){  
	    		case   1:   
	  	  			return   "星期日"; 
				case   2:   
					return   "星期一";   
		  	  	case   3:   
		  	  		return   "星期二";   
		  	  	case   4:   
		  	  		return   "星期三";   
		  	  	case   5:   
		  	  		return   "星期四";   
		  	  	case   6:   
		  	  		return   "星期五";   
		  	  	case   7:   
		  	  		return   "星期六";
	    	}   
		  	return   "";   
	    }
		
		
	    /**
	     * 计算传入日期的后一天
	     * <p>
	     * 日期时间格式yyyy-MM-dd
	     * @param String 
	     * @return
	     * @throws ParseException 
	     */
	    public static String afterDate(String dateString) throws ParseException {
	        
	        calendar.setTime(formatStringToDate(dateString));
	        int dayDate = calendar.get(Calendar.DATE);
	        calendar.set(Calendar.DATE, dayDate+1);
	        
	        return formatDate(calendar.getTime());
	    }
	    
	    /**
	     * 计算传入日期的前一天
	     * <p>
	     * 日期时间格式yyyy-MM-dd
	     * @param String 
	     * @return
	     * @throws ParseException 
	     */
	    public static String beforeDate(String dateString) throws ParseException {
	        
	        calendar.setTime(formatStringToDate(dateString));
	        int dayDate = calendar.get(Calendar.DATE);
	        calendar.set(Calendar.DATE, dayDate-1);
	        
	        return formatDate(calendar.getTime());
	    }
	    
	    /** 获得该日期指定天数之前的日期
	     * @param dtDate
	     * @param lDays
	     * @return 返回日期
	     */
	    public static Date before(Date dtDate, long lDays)
	    {
	        long lCurrentDate = 0;
	        lCurrentDate = dtDate.getTime() - lDays * 24 * 60 * 60 * 1000;
	        Date dtBefore = new Date(lCurrentDate);
	        return dtBefore;
	    }
	    /** 获得该日期指定天数之后的日期
	     * @param dtDate
	     * @param lDays
	     * @return 返回日期
	     */
	    public static Date after(Date dtDate, long lDays)
	    {
	        long lCurrentDate = 0;
	        lCurrentDate = dtDate.getTime() + lDays * 24 * 60 * 60 * 1000;
	        Date dtAfter = new Date(lCurrentDate);
	        return dtAfter;
	    }
	    /**
	     * 将String转Timestamp
	     * */
	     public static Timestamp formatStringToTimestamp(String datetimeString){
	     	Timestamp timestamp = null;
	     	datetimeFormat.setLenient(false);
	     	  try {
	     		  timestamp = new Timestamp(datetimeFormat.parse(datetimeString).getTime());
	     	  } catch (Exception e) {
	     	   e.printStackTrace();
	     	  }
	     	return timestamp;
	     }
	     /**
	      * 将Date转Timestamp
	      * */
	      public static Timestamp formatDateToTimestamp(Date date){
	      	Timestamp timestamp = null;
	      	  try {
	      		timestamp = new Timestamp(date.getTime());
	      	  } catch (Exception e) {
	      	   e.printStackTrace();
	      	  }
	      	return timestamp;
	      }
	     /**
	      * 将Timestamp转Date
	      * 日期时间格式yyyy-MM-dd HH:mm:ss
	      * */
	     public static Date formatTimestampToDateTime (Timestamp ts){
	     	Date date = null;
	     	String strTemp = ts.toString();
	     	 try {
	 			date = datetimeFormat.parse(strTemp);
	 		} catch (ParseException e) {
	 			e.printStackTrace();
	 		}   
	     	return date;
	     }
	     /**
	      * 将Timestamp转Date
	      * 日期时间格式yyyy-MM-dd
	      * */
	     public static Date formatTimestampToDate (Timestamp ts){
	    	 Date date = null;
	    	 String strTemp = ts.toString();
	    	 try {
	    		 date = dateFormat.parse(strTemp);
	    	 } catch (ParseException e) {
	    		 e.printStackTrace();
	    	 }   
	    	 return date;
	     }
	 	/**
	 	 * 方法名称：getNextMonthForFix
	 	 * 方法描述：传进去开始日期和月份参数，得出开始日期加上月份参数之后的日期
	 	 * 创建人： 邢凌霄
	 	 * 创建时间：2016-6-30 下午04:00:46
	 	 * @param dtDate
	 	 * @param nMonth
	 	 * @return
	 	 * @since JDK 1.5
	 	 */
	 	static public Date getNextMonthForFix (Date dtDate,int nMonth )
	 	{
	  	    Calendar c = Calendar.getInstance();
	  	    c.setTime(dtDate);
	  	    c.add(Calendar.MONTH, nMonth);
	  	    dtDate = c.getTime();
	  	   return dtDate;
	 	}
		/*
		 * 得到一个日期的月份，用1-12表示
		 */
		public static String getRealMonthString ( java.sql.Timestamp ts )
		{
			if (null == ts)
				return "" ;
			java.util.Calendar calendar = Calendar.getInstance ( ) ;
			calendar.setTime ( ts ) ;
			return String.valueOf ( calendar.get ( Calendar.MONTH ) + 1 ) ;
		}
		/*
		 * 得到一个日期的年份
		 */
		public static String getYearString ( java.sql.Timestamp ts )
		{
			if (null == ts)
				return "" ;
			java.util.Calendar calendar = Calendar.getInstance ( ) ;
			calendar.setTime ( ts ) ;
			return String.valueOf ( calendar.get ( Calendar.YEAR ) ) ;
		}
		/*
		 * 得到一个日期的日子
		 */
		public static String getDayString ( java.sql.Timestamp ts )
		{
			if (null == ts)
				return "" ;
			java.util.Calendar calendar = Calendar.getInstance ( ) ;
			calendar.setTime ( ts ) ;
			return String.valueOf ( calendar.get ( Calendar.DAY_OF_MONTH ) ) ;
		}
		static public java.sql.Timestamp getDateTime ( String sDt )
		{
			try
			{
				return java.sql.Timestamp.valueOf ( sDt ) ; //sDt
															// format:yyyy-mm-dd
															// hh:mm:ss.fffffffff
			} catch (IllegalArgumentException iae)
			{
				sDt = sDt + " 00:00:00" ;
				try
				{
					return java.sql.Timestamp.valueOf ( sDt ) ;
				} catch (Exception e)
				{
					return null ;
				}
			}
		}
	     public static void main(String[] args) {
	     	String str = "2016-2-29";
	     	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	     	try {
	     	    Date myDate = formatter.parse(str);
	     	    Calendar c = Calendar.getInstance();
	     	    c.setTime(myDate);
	     	    c.add(Calendar.MONTH, 12);
	     	    myDate = c.getTime();
	     	    System.out.println(DateUtils.formatDateToTimestamp(myDate));
	     	} catch (ParseException e1) {
	     	    e1.printStackTrace();
	     	}
	 	}
}
