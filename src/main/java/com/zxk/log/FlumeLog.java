package com.zxk.log;/**
 * Created by zhuxiaokang on 2018/6/27.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Describe: Flume日志测试类
 *
 * @author : ZhuXiaokang
 * @mail : xiaokang.zhu@pactera.com
 * @date : 2018/6/27 10:45
 * Attention:
 * Modify:
 */
public class FlumeLog {

    private static final Logger logger = LoggerFactory.getLogger(FlumeLog.class);

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            logger.info(i+",ABCD-APP,869927026774178,OPPO R9t,223.104.187.103,4g,20180628080003,methodInvoke");
            Thread.sleep(2000);
        }
    }
}
