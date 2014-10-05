package com.wifihi.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadFile {

	/**
	 * 将文件filename中的数据读入到一维数组arr中
	 * 
	 * @param filename
	 * @param arr
	 */
	public static void ReadFiletoArray(String filename, Object[] arr, int datatype) {
		try {
			FileReader frs = new FileReader(filename);
			BufferedReader in = new BufferedReader(frs);
			String xx = in.readLine();
			if (xx != null) {
				//System.out.println(xx);
				int beginindex = 0;
				int k = 0;
				int indexofelement = 0;
				while (beginindex < xx.length()) {
					char x = xx.charAt(k);
					if (x == ' ') {
						String sub = xx.substring(beginindex, k);
						beginindex = k + 1;
						if(datatype == 1)
						    arr[indexofelement] = new Integer(sub);
						else if(datatype == 2)
							arr[indexofelement] = new Float(sub);
						else if(datatype == 3)
							arr[indexofelement] = new Double(sub);
						else{
							System.out.println("不支持数据类型");
						}
						System.out.print(arr[indexofelement] + " ");
						indexofelement++;
					}
					k++;
				}
				System.out.println();
				xx = in.readLine();
			}
			frs.close();
			in.close();
		} catch (IOException e) {

		}
	}

	/**
	 * 将文件filename中的数据读入到二维数组arr中
	 * @param filename
	 * @param arr
	 */
	public static void ReadFiletoArray(String filename, Object[][] arr, int datatype) {
		try {
			FileReader frs = new FileReader(filename);
			BufferedReader in = new BufferedReader(frs);
			String xx = in.readLine();
			int indexofline = 0;
			while (xx != null) {
				//System.out.println(xx);
				int beginindex = 0;
				int k = 0;
				int indexofelement = 0;
				while (beginindex < xx.length()) {
					char x = xx.charAt(k);
					if (x == ' ') {
						String sub = xx.substring(beginindex, k);
						beginindex = k + 1;
						if(datatype == 1)
							arr[indexofline][indexofelement] = new Integer(sub);
						else if(datatype == 2)
							arr[indexofline][indexofelement] = new Float(sub);
						else if(datatype == 3)
							arr[indexofline][indexofelement] = new Double(sub);
						else{
							System.out.println("不支持数据类型");
						}
						System.out.print(arr[indexofline][indexofelement] + " ");
						indexofelement++;
					}
					k++;
				}
				System.out.println();
				xx = in.readLine();
				indexofline++;
			}
			frs.close();
			in.close();
		} catch (IOException e) {

		}
	}

	/**
	 * 将文件filename中的数据读入到一维链表数组arr中
	 * 
	 * @param filename
	 * @param arr
	 */
	public static void ReadFiletoListArray(String filename, LinkedList[] listarr, int datatype) {
		try {
			FileReader frs = new FileReader(filename);
			BufferedReader in = new BufferedReader(frs);
			String xx = in.readLine();
			while (xx != null) {
				//System.out.println(xx);
				int beginindex = 0;
				int k = 0;
				int indexofelement = 0;
				while (beginindex < xx.length()) {
					char x = xx.charAt(k);
					if (x == ' ') {
						String sub = xx.substring(beginindex, k);
						beginindex = k + 1;
						if(datatype == 1)
						    listarr[indexofelement].addLast(new Integer(sub));
						else if(datatype == 2)
							listarr[indexofelement].addLast(new Float(sub));
						else if(datatype == 3)
							listarr[indexofelement].addLast(new Double(sub));
						else
							System.out.println("不支持数据类型");
					}
					k++;
				}
				System.out.println();				
				xx = in.readLine();
				indexofelement++;
			}
			System.out.println();
			for(int i=0;i<listarr.length;i++){
				for(int j=0;j<listarr[i].size();j++){
					System.out.print((String)listarr[i].get(j)+" ");
				}
				System.out.println();
			}
			frs.close();
			in.close();
		} catch (IOException e) {

		}
	}

    /**
     * 将文件中内容输出到一个链表中，此链表中的元素也是链表，文件中的每行对应链表中的一个链表
     * @param filename 文件名
     * @param list 需要输出的链表
     * @param datatype 数据类型
     */	
	public static void ReadFiletoList(String filename, LinkedList list, int datatype) {
		try {
			FileReader frs = new FileReader(filename);
			BufferedReader in = new BufferedReader(frs);
			String xx = in.readLine();
			while (xx != null) {
				LinkedList templist = new LinkedList();
				//System.out.println(xx);
				int beginindex = 0;
				int k = 0;
				int indexofelement = 0;
				while (beginindex < xx.length()) {
					char x = xx.charAt(k);
					if (x == ' ') {
						String sub = xx.substring(beginindex, k);
						beginindex = k + 1;
						if(datatype == 1)
							templist.addLast(new Integer(sub));
						else if(datatype == 2)
							templist.addLast(new Float(sub));
						else if(datatype == 3)
							templist.addLast(new Double(sub));
						else
							System.out.println("不支持数据类型");
					}
					k++;
				}
				list.addLast(templist);
				//System.out.println();				
				xx = in.readLine();
				indexofelement++;
			}
			System.out.println();
			for(int i=0;i<list.size();i++){
				LinkedList ll = (LinkedList)list.get(i);
				for(int j=0;j<ll.size();j++){
					System.out.print((Double)ll.get(j)+" ");
				}
				System.out.println();
			}
			frs.close();
			in.close();
		} catch (IOException e) {

		}
	}

}
