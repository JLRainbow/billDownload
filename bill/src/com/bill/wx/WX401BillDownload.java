package com.bill.wx;

import java.io.IOException;

import com.bill.util.ConfigUtil;

public class WX401BillDownload extends WXBillDownload{
	
  public static void main(String[]args) throws Exception {

  	WX401BillDownload wX401BillDownload = new WX401BillDownload();
  	wX401BillDownload.billDownload();
  }

	private WX401BillDownload() {
	}
	
	public void billDownload() throws IOException{
		super.billDownload(ConfigUtil.WX_APP_ID_401, ConfigUtil.WX_MCH_ID_401, ConfigUtil.WX_API_KEY_401);
	}
	
}
