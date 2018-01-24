package com.bill.wx;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.SortedMap;

import java.util.TreeMap;

import com.bill.alipay.AlipayBillDownload;
import com.bill.util.CommonUtil;
import com.bill.util.ConfigUtil;
import com.bill.util.CsvUtil;
import com.bill.util.WXPayCommonUtil;

/**
 * ΢�����ض��˵�
 * @author jial
 *
 */
public class WXBillDownload {


    protected void billDownload(String appid,String mch_id,String appKey) throws IOException{
        SortedMap<Object,Object> parameters =new TreeMap<Object,Object>();
        //���ñش�����
        parameters.put("appid",appid);
        parameters.put("mch_id",mch_id);
        parameters.put("nonce_str",WXPayCommonUtil.CreateNoncestr());
        parameters.put("bill_date","20171230");//���ض��˵������ڣ���ʽ��20140603�����ڲ���Ϊ���졣
        //bill_type:ALL���ص������ж�����Ϣ,Ĭ��ֵSUCCESS���ص��ճɹ�֧���Ķ�����REFUND�����ص����˿��
        parameters.put("bill_type","ALL");
        parameters.put("sign", WXPayCommonUtil.createSign("utf-8", parameters,appKey));
        //������ת����xml��ʽ��������
        String reuqestXml =WXPayCommonUtil.getRequestXml(parameters);
        String result=CommonUtil.httpsRequest(ConfigUtil.WX_DOWNLOAD_BILL_URL, "POST",reuqestXml);

        if(result.startsWith("<xml>")){//��ѯ����Ϊ����ʱ��������Ϣ��ʾ������Ч
            System.out.println(result);
            System.out.println("���ض��˵�ʧ��");
        }else {  
        	String[] resultArray = result.replace("����", "����%").split("%");
        	List<String> dataList = new ArrayList<String>();
        	for (int i = 0; i < resultArray.length; i++) {
        		dataList.add(resultArray[i]);
			}
        	CsvUtil csvUtil = new CsvUtil();
        	Properties props =new Properties();
			InputStream is = AlipayBillDownload.class.getClassLoader().getResourceAsStream("config.properties");
			props.load(is);
			String filePath = props.getProperty("filePath");
        	csvUtil.createCsv(dataList, filePath+"20171230.csv");
//        	System.out.println(str.replace("%", "%\r\n"));
        	
       }
    }
}


