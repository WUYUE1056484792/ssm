package com.example.xiaowu.utils;


import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestUtil {
    /**
     * 调用对方接口方法
     * @param path 对方或第三方提供的路径
     * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static String interfaceUtil(String path, String data,String clientId,String token) {
        String str = "";
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;

            /**设置URLConnection的参数和普通的请求属性****start***/

            conn.setRequestProperty("token", token);
            conn.setRequestProperty("clientId", clientId);
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            //设置主机连接时间超时时间3000毫秒
            conn.setConnectTimeout(2000);
            //设置读取远程返回数据的时间3000毫秒
            conn.setReadTimeout(2000);


            /**设置URLConnection的参数和普通的请求属性****end***/

            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod("GET");//GET和POST必须全大写
            /**GET方法请求*****start*/
            /**
             * 如果只是发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可；
             * 如果发送POST方式的请求，需要获取URLConnection实例对应的输出流来发送请求参数。
             * */
            conn.connect();

            /**GET方法请求*****end*/

            /***POST方法请求****start*/

            /*out = new PrintWriter(conn.getOutputStream());//获取URLConnection对象对应的输出流

            out.print(data);//发送请求参数即数据

            out.flush();//缓冲数据
            */
            /***POST方法请求****end*/

            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while ((str = br.readLine()) != null) {
                str = new String(str.getBytes(), "UTF-8");//解决中文乱码问题
                System.out.println("wwwww"+str);
                return str;
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");

        } catch (Exception e) {
            System.out.println("异常了 "+e.toString());
            return "fail";
            //e.printStackTrace();
        }

        return "aaaaaaaaaaaa";
    }


    /**
     *
     * 极光专用
     */
    public static String JGUtil(String path,String appKey,String masterSecret) {
        String str = "";
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;

            /**设置URLConnection的参数和普通的请求属性****start***/

            String base64String=appKey+":"+masterSecret;
            System.out.println("拼接的 "+base64String);


            String str2=base64String;
            String encode = new BASE64Encoder().encode(str2.getBytes());
            System.out.println("编码过后："+encode);



            String auth = "Basic "+encode;
            System.out.println("最终的 "+auth);

            conn.setRequestProperty("Authorization", auth);
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            /**设置URLConnection的参数和普通的请求属性****end***/

            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod("GET");//GET和POST必须全大写
            /**GET方法请求*****start*/
            /**
             * 如果只是发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可；
             * 如果发送POST方式的请求，需要获取URLConnection实例对应的输出流来发送请求参数。
             * */
            conn.connect();

            /**GET方法请求*****end*/

            /***POST方法请求****start*/

            /*out = new PrintWriter(conn.getOutputStream());//获取URLConnection对象对应的输出流

            out.print(data);//发送请求参数即数据

            out.flush();//缓冲数据
            */
            /***POST方法请求****end*/

            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while ((str = br.readLine()) != null) {
                str = new String(str.getBytes(), "UTF-8");//解决中文乱码问题
                System.out.println("wwwww"+str);
                return str;
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "aaaaaaaaaaaa";
    }



    public static void main(String[] args) {
        /*List<ContactDto> contactDtoList = new ArrayList<>();
        String result = HttpRequestUtil.interfaceUtil("http://sso.sw.jpsycn.com/api/tSUser/userTree?departId=410100000000", "","ydswtapp","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJjbGllbnRJZFwiOlwieWRzd3RhcHBcIixcImxvZ2luVGltZVwiOjE1NTYwMDY1NDU4MzksXCJ1c0lkXCI6MjM5OSxcInVzZXJSb2xlXCI6XCJBRE1JTlwiLFwidXVJZFwiOlwiNDAyODgxZTUzYzdmMGRkZTAxM2M3ZjI5ZWQ4ZTAwMTZcIn0iLCJpc3MiOiJhdXRoMCIsImV4cCI6MTg3MTM2NjU0NSwiaWF0IjoxNTU2MDA2NTQ1fQ.Cy3-eDD4OEYhJlldvtJsymALRVGwP466TmBrSQJQGUo");//get请求
        //String result = HttpRequestUtil.interfaceUtil("http://172.16.2.207:6606/daibancontroller.do?getDbxxNum", "","ydswtapp","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJjbGllbnRJZFwiOlwieWRzd3RhcHBcIixcImxvZ2luVGltZVwiOjE1NTYwMDY1NDU4MzksXCJ1c0lkXCI6MjM5OSxcInVzZXJSb2xlXCI6XCJBRE1JTlwiLFwidXVJZFwiOlwiNDAyODgxZTUzYzdmMGRkZTAxM2M3ZjI5ZWQ4ZTAwMTZcIn0iLCJpc3MiOiJhdXRoMCIsImV4cCI6MTg3MTM2NjU0NSwiaWF0IjoxNTU2MDA2NTQ1fQ.Cy3-eDD4OEYhJlldvtJsymALRVGwP466TmBrSQJQGUo");//get请求
        System.out.println("result  "+result);
        //将json字符串转化为json对象
        JSONObject res= JSON.parseObject(result);
        //System.out.println("ssssssssssss "+res);
        JSONArray data = (JSONArray) res.get("data");
        System.out.println("data  "+data);*/




        /*String result = interfaceUtil("http://zzqx.zhengzhou.gov.cn/QXJ_C_MESSAGE_PARSE_CITY.getWeather.do?city=%E9%83%91%E5%B7%9E","city=%E9%83%91%E5%B7%9E","ydswtapp","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJjbGllbnRJZFwiOlwieWRzd3RhcHBcIixcImxvZ2luVGltZVwiOjE1NjAxMjk2NTQwODUsXCJ1c0lkXCI6NzUxNCxcInVzZXJSb2xlXCI6XCJBRE1JTlwiLFwidXVJZFwiOlwiYTBhMjYzMjk5ZGZiNGIwMGI2ZDk4YmVjNmZiYWYwOGVcIn0iLCJpc3MiOiJhdXRoMCIsImV4cCI6MTg3NTQ4OTY1NCwiaWF0IjoxNTYwMTI5NjU0fQ.cVy0DX2B_YXSPf39aba2xHcZRZDT7dHKGGeuD1nR0WY");//post请求
        System.out.println("result  "+result);*/

        String result = HttpRequestUtil.interfaceUtil("http://zzqx.zhengzhou.gov.cn/WECHAT_WEATHER.getRadarImg.do","","","");
        System.out.println("result  "+result);
    }


}
