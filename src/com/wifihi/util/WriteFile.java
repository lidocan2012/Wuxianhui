package com.wifihi.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	
	/**
	 * ����Ŀ¼���ļ�������һ��BufferedWriter����Ŀ¼���Ĺ����Ǹ�Ŀ¼����Ŀ¼֮����\\�ָ�
	 * @param dir
	 * @param filename
	 * @return
	 */
	static public BufferedWriter getBufferedWriter(String dir, String filename) {
		try {
			File newdir = new File(dir);
			if (!newdir.exists())
				newdir.mkdirs();
			FileWriter fwn = new FileWriter(dir + filename);// �ַ���
			BufferedWriter bwn = new BufferedWriter(fwn);
			return bwn;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}       
	}

}
