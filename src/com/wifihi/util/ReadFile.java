package com.wifihi.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadFile {

	/**
	 * ���ļ�filename�е����ݶ��뵽һά����arr��
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
							System.out.println("��֧����������");
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
	 * ���ļ�filename�е����ݶ��뵽��ά����arr��
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
							System.out.println("��֧����������");
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
	 * ���ļ�filename�е����ݶ��뵽һά��������arr��
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
							System.out.println("��֧����������");
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
     * ���ļ������������һ�������У��������е�Ԫ��Ҳ�������ļ��е�ÿ�ж�Ӧ�����е�һ������
     * @param filename �ļ���
     * @param list ��Ҫ���������
     * @param datatype ��������
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
							System.out.println("��֧����������");
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
