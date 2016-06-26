package com.yinrong.service.impl;

import com.yinrong.service.MnsSendService;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *  @author SongZhiQiang
 */
public class MnsSendServiceImpl  implements MnsSendService {

/*
    @Override
    public void executeSendEmail(Map<String, String> map) {
        String mnslistSubject = map.get("mnslistSubject");
        String mnslistContent = map.get("mnslistContent");
        String smtpHost = getSysParam("OPEN_EMAIL_SERVER","00","00");
        String userName = getSysParam("OPEN_EMAIL_SERVER","01","00");
        String password = getSysParam("OPEN_EMAIL_SERVER","02","00");
        List<String> toList = (List<String>)JsonUtil.buildNonDefaultBinder().getJsonToList(map.get("to"), String.class);
        EmailUtil themail = new EmailUtil();
        themail.createMimeMessage();
        themail.setNeedAuth(true);
        themail.setSmtpHost(smtpHost);
        themail.setSubject(mnslistSubject);
        themail.setBody(mnslistContent);
        themail.setFrom(userName);
        themail.setUserName(userName);
        themail.setPassword(password);
        try {
            for (int i = 0; i < toList.size(); i++) {
                String to = String.valueOf(toList.get(i));
                themail.setTo(to);
                themail.sendMail();
            }
        } catch (Exception e) {
            logger.error(SYS_CODE+":executeSendEmail" ,e);
        }
        executeSendEmail
    }
*/




    public static void main(String[] args) {
        MnsSendServiceImpl mnsSendService=new MnsSendServiceImpl();
        Map map=new HashMap();
        map.put("mnslistSubject","测试主题");
        map.put("mnslistContent","测试内容");
        List<String> toList=new ArrayList<String>();

        toList.add("yz576965161@126.com");
        toList.add("ye576965161@126.com");
        map.put("to", toList);
        mnsSendService.executeSendEmail(map);
    }



    public void executeSendEmail(Map<String, String> map) {
        try {
            /*    String api_key =  getSysParam("OPEN_EMAIL_SERVER","03","00");
                String api_user =  getSysParam("OPEN_EMAIL_SERVER","04","00");
                String api_url =  getSysParam("OPEN_EMAIL_SERVER","05","00");
                String from =  getSysParam("OPEN_EMAIL_SERVER","06","00");
                String fromname =  getSysParam("OPEN_EMAIL_SERVER","07","00");*/

            String api_key="L3bB2B601MrL4epl";
            String api_user="hexunpay";
            String  api_url="http://sendcloud.sohu.com/webapi/mail.send.json";
            String from="hexunpay@pay.hexuntong.hexun.com";
            String fromname="和e付";
           /*     if (StringUtils.isBlank(api_key)||StringUtils.isBlank(api_user)||
                        StringUtils.isBlank(api_url)||StringUtils.isBlank(from)||
                        StringUtils.isBlank(fromname)){
                    logger.info("短信渠道配置信息不完整，请检查OPEN_EMAIL_SERVER配置配置");
                    return;
                }*/
            String mnslistSubject = map.get("mnslistSubject");
            String mnslistContent = map.get("mnslistContent");

            Map<String,String> params=new HashMap<String, String>();
            params.put("api_key",api_key);
            params.put("api_user",api_user);
            params.put("from",from);
            params.put("fromname",fromname);
            params.put("subject",mnslistSubject);
            params.put("html",mnslistContent);
            params.put("resp_email_id","true");
            //   List<String> toList = (List<String>)JsonUtil.buildNonDefaultBinder().getJsonToList(map.get("to"), String.class);
            //   logger.info("开始发送邮件"+map.toString());
            params.put("to","ye576965161@126.com");
            String result= Httppo.doPost(api_url,params,"utf-8",5000,5000);
        } catch (Exception e) {
            //     logger.error("邮件发送失败:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
