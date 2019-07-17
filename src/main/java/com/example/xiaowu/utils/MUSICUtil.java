package com.example.xiaowu.utils;

import Ice.Communicator;
import Ice.InitializationData;
import Ice.ObjectPrx;
import Ice.Util;
import cma.cimiss.DataAPIAccessPrx;
import cma.cimiss.DataAPIAccessPrxHelper;
import cma.cimiss.client.DataQueryClient;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

import static cma.cimiss.client.DataQueryClient.getLocalIP;

public class MUSICUtil {
    public static String userName="BEZZ_BFZZ_zzqxt001";
    public static String  pwd = "66833038" ;



    public static DataAPIAccessPrx prx = null;
    public static Communicator ic = null;
    public static String SELF_IP;
    public static String serverIp = "";
    public static String http_server = "";
    public static String http_port = "8008";
    /**
     * 获取数据
     * @return
     */
    public static JSONObject getData(String interfaceId, HashMap<String, String> params, String dataFormat) {
        JSONObject jsonObject = new JSONObject();
        /* 1. 定义client对象 */
        DataQueryClient client = new DataQueryClient();
        //返回内容
        StringBuffer retStr = new StringBuffer();
        try {

            //初始化接口服务连接资源                  //这一块出问题
            client.initResources();


            //调用接口
            int rst = client.callAPI_to_serializedStr(userName, pwd, interfaceId, params, dataFormat, retStr) ;
            //输出结果
            if(rst == 0) { //正常返回
                jsonObject = JSONObject.parseObject(retStr.toString());
            } else { //异常返回
                System.out.println( "[error] StaElemSearchAPI_CLIB_callAPI_to_serializedStr_JSON." ) ;
                System.out.printf( "\treturn code: %d. \n", rst ) ;
                System.out.println( "国家气象局内网请求异常" ) ;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println( "国家气象局内网请求异常2" ) ;
        }finally {
            //释放接口服务连接资源
            System.out.println( "释放资源" ) ;
            client.destroyResources() ;
        }


        return jsonObject;
    }


    public static void main(String[] args) {
        try {
            System.out.println("111111111111111");
            InitializationData initData = new InitializationData();
            System.out.println("iiiiiiiiiiiiii "+initData);
            System.out.println("222222222222222");
            initData.properties = Util.createProperties();
            System.out.println("333333333333333");
            System.out.println("oooooooooooooo "+initData.properties);
            initData.properties.load("client.config");
            System.out.println("444444444444444");
            ic = Util.initialize(initData);
            System.out.println("icicicic "+initData.properties.getProperty("http.port"));
            System.out.println("5555555555555555");
            SELF_IP = getLocalIP();
            if (ic == null) {
                System.out.println("ICE get Communicator by properties is null!!!!!!");
                throw new Exception("ICE get Communicator by properties is null!!!!!!");
            }

            ObjectPrx base = ic.stringToProxy(initData.properties.getProperty("DataApi.Proxy"));
            prx = DataAPIAccessPrxHelper.checkedCast(base);
            if (prx == null) {
                throw new Exception("Invalid proxy");
            }

            try {
                http_port = initData.properties.getProperty("http.port");
                if (http_port == null || http_port.equalsIgnoreCase("")) {
                    http_port = "80";
                }

                serverIp = initData.properties.getProperty("DataApi.Proxy").split(" ")[2];
                http_server = serverIp + ":" + http_port;
            } catch (Exception var3) {
                http_server = "";
            }

            initData = null;
        } catch (Exception var4) {
            System.out.println("异常");
        }
    }
}
