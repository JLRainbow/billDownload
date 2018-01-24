package com.bill.alipay;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.joda.time.DateTime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.bill.util.ConfigUtil;
import com.bill.util.CsvUtil;
import com.bill.util.FileUtil;
/**
 * 支付宝下载对账单
 * @author jial
 *
 */
public class AlipayBillDownload {
     //初始化实例请求对象 
    public static AlipayClient alipayClient = new DefaultAlipayClient(ConfigUtil.ALIPAY_DOWNLOAD_BILL_URL, 
    		ConfigUtil.ALIPAY_APP_ID, ConfigUtil.ALIPAY_APP_PRIVATE_KEY, "json", "GBK",  ConfigUtil.ALIPAY_PUBLIC_KEY,"RSA"); 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	billDownload ();//下载对账单接口 
    }
    public static void  billDownload (){ 
        
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        JSONObject json = new JSONObject();
        json.put("bill_type", "signcustomer");
        //昨天的数据 new DateTime().minusDays(1).toString("yyyy-MM-dd")
        json.put("bill_date", "2017-12-30");
        request.setBizContent(json.toString());
                
		AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
			System.out.println(response.getBillDownloadUrl());

		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
         if(response.isSuccess()){ 
			// 将接口返回的对账单下载地址传入urlStr
			String urlStr = response.getBillDownloadUrl();
			
			// 开始下载
			try {
				//读取配置路径
				Properties props =new Properties();
				InputStream is = AlipayBillDownload.class.getClassLoader().getResourceAsStream("config.properties");
				props.load(is);
				String filePath = props.getProperty("filePath");
				// 指定希望保存的文件路径
				String newZip = filePath + new Date().getTime() + ".zip";
				FileUtil.downloadNet(urlStr, newZip);
				// 解压到指定目录
				FileUtil.unZip(newZip, filePath);
				 // 遍历文件 获取需要的明细csv
	            File[] fs = new File(filePath).listFiles();
//	            for (File file : fs) {
//	                if (file.getAbsolutePath().contains("汇总")) {
//	                	//读取csv文件
//	                	
//	                	InputStream inputStream = new FileInputStream(file.getAbsolutePath());
//	                	CsvUtil test = new CsvUtil(inputStream);
//	                	int rowNum = test.getRowNum();// 获取行数
//	                	System.out.println("==========行数：" + rowNum);
//	                	inputStream.close();
//	                }
//	            }
	            // 删除文件
//	            for (File file : fs) {
//	                file.delete();
//	            }
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			  System.out.println("调用成功");
		}else {
             System.out.println("调用失败");
        }
         
         System.out.println(JSON.toJSONString(response));
       } 
}
