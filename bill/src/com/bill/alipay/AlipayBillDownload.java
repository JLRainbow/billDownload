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
 * ֧�������ض��˵�
 * @author jial
 *
 */
public class AlipayBillDownload {
     //��ʼ��ʵ��������� 
    public static AlipayClient alipayClient = new DefaultAlipayClient(ConfigUtil.ALIPAY_DOWNLOAD_BILL_URL, 
    		ConfigUtil.ALIPAY_APP_ID, ConfigUtil.ALIPAY_APP_PRIVATE_KEY, "json", "GBK",  ConfigUtil.ALIPAY_PUBLIC_KEY,"RSA"); 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	billDownload ();//���ض��˵��ӿ� 
    }
    public static void  billDownload (){ 
        
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        JSONObject json = new JSONObject();
        json.put("bill_type", "signcustomer");
        //��������� new DateTime().minusDays(1).toString("yyyy-MM-dd")
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
			// ���ӿڷ��صĶ��˵����ص�ַ����urlStr
			String urlStr = response.getBillDownloadUrl();
			
			// ��ʼ����
			try {
				//��ȡ����·��
				Properties props =new Properties();
				InputStream is = AlipayBillDownload.class.getClassLoader().getResourceAsStream("config.properties");
				props.load(is);
				String filePath = props.getProperty("filePath");
				// ָ��ϣ��������ļ�·��
				String newZip = filePath + new Date().getTime() + ".zip";
				FileUtil.downloadNet(urlStr, newZip);
				// ��ѹ��ָ��Ŀ¼
				FileUtil.unZip(newZip, filePath);
				 // �����ļ� ��ȡ��Ҫ����ϸcsv
	            File[] fs = new File(filePath).listFiles();
//	            for (File file : fs) {
//	                if (file.getAbsolutePath().contains("����")) {
//	                	//��ȡcsv�ļ�
//	                	
//	                	InputStream inputStream = new FileInputStream(file.getAbsolutePath());
//	                	CsvUtil test = new CsvUtil(inputStream);
//	                	int rowNum = test.getRowNum();// ��ȡ����
//	                	System.out.println("==========������" + rowNum);
//	                	inputStream.close();
//	                }
//	            }
	            // ɾ���ļ�
//	            for (File file : fs) {
//	                file.delete();
//	            }
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			  System.out.println("���óɹ�");
		}else {
             System.out.println("����ʧ��");
        }
         
         System.out.println(JSON.toJSONString(response));
       } 
}
