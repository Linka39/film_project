package com.linka39.util;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author  工具类设置静态方法，声明一次在内存初始化就存在
 *不需要声明对象就可以调用
 */
public class DateUtil {

    /**
     * 日期对象转字符串
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date,String format){
        String result="";
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        if(date!=null){
            result=sdf.format(date);
        }
        return result;
    }

    /**
     * 字符串转日期对象
     * @param str
     * @param format
     * @return
     * @throws Exception
     */
    public static Date formatString(String str,String format) throws Exception{
        if(StringUtil.isEmpty(str)){
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static String getCurrentDateStr()throws Exception{
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }
}
