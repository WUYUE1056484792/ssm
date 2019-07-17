package com.example.xiaowu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
    /**
     * 获取Music世界时间
     * @param yearNum 提前天数
     * @return
     * @author 李云鹏
     * @data 2018-4-26
     */
    public static String getMusicDate(int yearNum) {
        Calendar c = Calendar.getInstance();
        if(yearNum>0){
            c.add(Calendar.DATE, -yearNum);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if(hour>=8){
            hour=hour-8;
        }else{
            hour=hour+16;
            if(date>1){
                date=date-1;
            }else{
                month=month-1;
                //获取月天数
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(sdf.parse(year+""+month));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                date=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
        }
        String str=year+"";
        if(month<10){
            str=str+"0"+month;
        }else{
            str=str+month;
        }
        if(date<10){
            str=str+"0"+date;
        }else{
            str=str+date;
        }
        if(hour<10){
            str=str+"0"+hour;
        }else{
            str=str+hour;
        }
        return str+"0000";
    }

    public static void main(String[] args) {
        String result = getMusicDate(0);
        System.out.println("zzzzzzzzzzz "+result);
    }
}
