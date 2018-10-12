package com.zxk.log.example;

/**
 * Describe:
 *
 * @author : ZhuXiaokang
 * @mail : xiaokang.zhu@pactera.com
 * @date : 2018/9/27 15:08
 * Attention:
 * Modify:
 */
public class Test {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Test.class);
    private static final org.apache.logging.log4j.Logger l4j2 = org.apache.logging.log4j.LogManager.getLogger(Test.class);
    public static void main(String[] args) {
        org.slf4j.MDC.put("uniqueFlowNo","11111111111111111");
        java.util.Map<String, Object> a = new java.util.HashMap();
        a.put("a","aa");
        a.put("B","15023389244");
//        logger.error("{}", com.alibaba.fastjson.JSONObject.toJSONString(a));
        Test c = new Test();

        User b = new com.zxk.log.example.Test.User();
//        String json = "a:"[{\"delocalizedValues\":{\"uniqueFlowNo\":\"20180927162647243243344528\",\"appVersion\":\"7.1.0\",\"clientIp\":\"192.168.20.78\",\"appId\":\"ABCD-APP\",\"userId\":\"1747492\",\"headerInside\":\"yes\",\"timestamp\":\"1538036807242\",\"token\":\"7eb0bd1ef018e049dbfbab7175ee8be7\"}},{\"userId\":\"1747492\"}]";

//        System.out.println(json);
//
//
//        l4j2.info(new org.apache.logging.log4j.message.ObjectMessage(com.alibaba.fastjson.JSONObject.parseObject(json, java.util.HashMap.class)));

//        l4j2.info(new ObjectMessage(b));
        l4j2.info(new org.apache.logging.log4j.message.ObjectMessage(a));
    }

    static class User {
       public String module = "【Provider】：ugs-provider";
       public String responseTiem = "211ms";
       public java.util.List requestContext = com.alibaba.fastjson.JSONObject.parseObject("[{\"delocalizedValues\":{\"uniqueFlowNo\":\"20180927162647243243344528\",\"appVersion\":\"7.1.0\",\"clientIp\":\"192.168.20.78\",\"appId\":\"ABCD-APP\",\"userId\":\"1747492\",\"headerInside\":\"yes\",\"timestamp\":\"1538036807242\",\"token\":\"7eb0bd1ef018e049dbfbab7175ee8be7\"}},{\"userId\":\"1747492\"}]", java.util.List.class);
       public java.util.HashMap responseContext = com.alibaba.fastjson.JSONObject.parseObject("{\"result\":{\"resultCode\":\"000000\",\"resultMsg\":\"成功\",\"data\":{\"isSuccess\":\"0\",\"continuDay\":2}},\"attachments\":{},\"value\":{\"resultCode\":\"000000\",\"resultMsg\":\"成功\",\"data\":{\"isSuccess\":\"0\",\"continuDay\":2}}}", java.util.HashMap.class);
    }

}
