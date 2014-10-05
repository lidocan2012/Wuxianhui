package com.wifihi.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	
	/**
	 * 根据目录和文件名返回一个BufferedWriter对象，目录名的规则是父目录和子目录之间用\\分隔
	 * @param dir
	 * @param filename
	 * @return
	 */
	static public BufferedWriter getBufferedWriter(String dir, String filename) {
		try {
			File newdir = new File(dir);
			if (!newdir.exists())
				newdir.mkdirs();
			FileWriter fwn = new FileWriter(dir + filename);// 字符流
			BufferedWriter bwn = new BufferedWriter(fwn);
			return bwn;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}       
	}

}
