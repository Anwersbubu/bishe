package com.yj.bishe.demo.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2021-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000116688215";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOU1QwFtjx6KWxPWDo9SrGJrY01/su8RWdH03Sr6aExThZOTODYORTCNxITvyE7HMS3b0HrXBwFHgNW25C765En0uCYQe2j5IWkHbeoAy/QwbqWsnCyeEfWwBtmyTv/ifuX2V5KVtOSYKLxN7ZivduhiWwW8r9r8Tu+S+MFO/puT5bgkvKSQfhaKV0V5c3pS4zU9KWjwVJ3EAFdnOeppWM7LtA8ZkWT3VhB6+9QB6w6FNVjREfcKpk7fOf/OZwF6vyHiSvrho68gh+QFbjf3MRDqeVse2nYhJX1zi1JdTefZlb52vxNSL/yl57d9zR2RWYVCutAoQbFUA5NeeA0U1JAgMBAAECggEAURGh+Kpq9JBCIQ0HMGrF7IyMaNlFmF12pEL6fzHSNfpouFnev8znAW0gnIdxSv2sJmjrjX37d7wRDfqweawwpaHezusaC6EH45ZyPykHWcKCI+eG2U9IziV1vvkKNzGb6SmpkTQ/b/5rP9Xp4hxICEqdGTGQFKSUFlMWBELAzW3/PoZghJ3sHS+hV4gnL6g0gcHUSdziye2Nlo+QODbGGjZKGSUcC2Z2G0T2PB0sbVJv+tv9Z5gqCwGtg8BC/5GCd2uW/YQqWNZ1GvVJT7w78mX+DRt3hCUo5wxCRMUk4XD6STTWI+4oDwF+Zycf3NUeZmPhAWQNFPaiKpmWXAdbIQKBgQDGEB0qls9vJAJWshEXV2C0cA2I4QBlRlvTbtUurlDUnuzswbI25CxdEpOGrziIHyeMiRaQDyKqFOU/H/qsJaKMVAHeHEFhsjVA9ocAkuRFtb+x9fRyk/LKhILS3jHAAZX83h2Fo4Dni8WtI1o6AKAoWSkhk73rrqGmLZ8NQe1rvQKBgQC39VUy5qbBvdqgEfdJmuYLEK6+tGIdHviy5LDUv1mehkCfvqjqkM/RWCEr1OiZ4pYZQtr7b7LzKkbxRwIx8/bKKeKEUMXp6rOos6Nq/ahqMcA4FPFHrD+On6DBuf2FKVedMIkEGiG1ov8876zmuRqQ3MjsPrtCPfxBXbSp2KSafQKBgF5C0P6x42sLgilseYHYTefR5D8ehiesworvlnmu8D0gnbVXwc8p7IsnChrxTUBLQ3E41QoeierUIv5V4+EF0ERkvrbMT7mFLJ8MNNfrEMS0aNTzLzFQhB4pznE42gh9OLqtovM1C2YnOR7bUleEcLSDKnYWcC64X6pM0ANpOuspAoGBAK8515AGuK0vhQKYWm8YRywfFfS9qf+HMupkgDQGmP0xcHk5iXj1u57zTZWM1otOlB832ZNLkK+jg7PsKaEfe+KSBxx2PC9Wyw9VHcKaIEQXY7uH51SYNGpMjzo5Sc3/1V77jmgD5Dw6KNoIiy+y/iUiyZiPIJw2owQ4mBB5NkGZAoGAAtOyllT/SY/CtwMlJqRCG3mvwy4a7CqcrYb620oL4ymkKMwF+3k3LaZJHxSs/uSXlfiThFJUr1+4WVEt2mPzUEBQ9wNYAqmfdWs5Cl6J6/cXCMcLOTGI22ZB1RQMlJSyxWXag0YKyO8qX9tHqk9XSmz5iGkTyhZdpVNnSVZ4pJA=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo2r6FXRibgdWQEgKdpIi3OEWQyJ1Put6Lh80fQuEAsf4EVUvNp3vmf0cmJnmAHgwz5YfDaiCzT1yBw35T0j5HcEdoT8gXqIdYfjGtl5EjXrDfg00AJ5S+lINQ1bFOFsoOvriQL6Xqdf15NrDkxXt3ufZA2oM+XRILAAlZNbm+HPH/E+IuH1YyllQW/dX+RJYSfNDdMF12B55Iel2Dcb5JbGSCulZmr5nUOn7pV4SVecPlW4ouKOeUqgkTLddWCkJA7CmiQsboymzF8IRO5cjc76tCe7ZZTxTCrskPZ3yQ2Pl/9TcWMhPVZwXrL92Z1jVXpXBkkkxrVNx75EnUz5/WwIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://127.0.0.1:8080/notify_url";
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://127.0.0.1:8080/return_url";


	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

