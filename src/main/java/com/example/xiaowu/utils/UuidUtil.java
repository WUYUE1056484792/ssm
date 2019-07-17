package com.example.xiaowu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UuidUtil {

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    public static String get19NO(){
        String  orderNo ="";
        String trandNo = String.valueOf((Math.random()*9 + 1) * 1000000);
        String sdf1 = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = trandNo.toString().substring(0, 4);
        orderNo = orderNo + sdf1 ;
        return orderNo;

    }
    public static String get15NO(){
        String  orderNo ="";
        String trandNo = String.valueOf((Math.random()*9 + 1) * 1000000);
        String sdf1 = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = trandNo.toString().substring(0, 1);
        orderNo = orderNo + sdf1 ;
        return orderNo;

    }

    /**
     * 生成指定长度的数字+字母字符串，指定前缀和后缀
     * @param length
     * @return
     */
    public static String getStringRandom(int length,String prefix,String suffix) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return prefix+val+suffix;
    }

    public static void main(String[] args) {
        System.out.println(get32UUID());
    }


}
