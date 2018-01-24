package com.bill.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public class CsvUtil {
	private BufferedReader bufferedreader = null;
	private List list = new ArrayList();
	
	public CsvUtil() {
	}

	public CsvUtil(InputStream inputStream) throws IOException {
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputStream,"gbk"));
		String stemp;
		while ((stemp = bufferedreader.readLine()) != null) {
			list.add(stemp);
		}
	}
	
	public List getList() throws IOException {
		return list;
	}

	// 得到csv文件的行�?
	public int getRowNum() {
		return list.size();
	}

	// 得到csv文件的列�?
	public int getColNum() {
		if (!list.toString().equals("[]")) {
			if (list.get(0).toString().contains(",")) { // csv文件中，每列之间的是�?','来分隔的
				return list.get(0).toString().split(",").length;
			} else if (list.get(0).toString().trim().length() != 0) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	// 取得指定行的�?
	public String getRow(int index) {
		if (this.list.size() != 0)
			return (String) list.get(index);
		else
			return null;
	}

	// 取得指定列的�?
	public String getCol(int index) {
		if (this.getColNum() == 0) {
			return null;
		}
		StringBuffer scol = new StringBuffer();
		String temp = null;
		int colnum = this.getColNum();
		if (colnum > 1) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				temp = it.next().toString();
				scol = scol.append(temp.split(",")[index] + ",");
			}
		} else {
			for (Iterator it = list.iterator(); it.hasNext();) {
				temp = it.next().toString();
				scol = scol.append(temp + ",");
			}
		}
		String str = new String(scol.toString());
		str = str.substring(0, str.length() - 1);
		return str;
	}

	// 取得指定行，指定列的�?
	public String getString(int row, int col) {
		String temp = null;
		int colnum = this.getColNum();
		if (colnum > 1) {
			temp = list.get(row).toString().split(",")[col];
		} else if (colnum == 1) {
			temp = list.get(row).toString();
		} else {
			temp = null;
		}
		return temp;
	}

	public void CsvClose() throws IOException {
		this.bufferedreader.close();
	}

//	public List readCvs(String filename) throws IOException {
//		CsvUtil cu = new CsvUtil(filename);
//		List list = cu.getList();
//
//		return list;
//	}

	public void createCsv(List list, String path) throws IOException {
		File file = new File(path);
		if (!file.exists())
			file.createNewFile();
		else
			file.delete();
		StringBuilder sb = new StringBuilder("");
		FileOutputStream writerStream = new FileOutputStream(file, true);
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(writerStream, "GBK"));
		for (Iterator itt = list.iterator(); itt.hasNext();) {
			String fileStr = StringUtils.strip(itt.next().toString(),"[]");
			String[] split = fileStr.split(", ");
			for (int i = 0; i < split.length; i++) {
				sb.append(split[i]+",");
			}
			sb.replace(sb.length()-1, sb.length(), "");
			sb.append("\r\n");
		}
		output.write(sb.toString());
		output.flush();
		output.close();
	}
}
