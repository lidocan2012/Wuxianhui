package com.wifihi.util;

import java.util.*;

public class listOperators {

	static public int getInsertIndexinDescentList(double v, LinkedList list) {
		if (list.size() == 0)
			return 0;
		int index = 0;
		boolean biger = false;
		for (int i = 0; i < list.size(); i++) {
			double temp = ((Double) list.get(i)).doubleValue();
			if (v >= temp) {
				index = i;
				biger = true;
			}
		}
		if (!biger)
			index = list.size();
		return index;
	}

	/**
	 * ��������,������ ͨ��һ������Ҫ��������ݷָ�ɶ����������֣�����һ���ֵ��������ݶ�������һ���ֵ�
	 * �������ݶ�ҪС��Ȼ���ٰ��˷����������������ݷֱ���п�����������������̿��Եݹ� ���У��Դ˴ﵽ�������ݱ���������С�
	 * 
	 * @param list
	 *            ��Ҫ���������
	 * @param index
	 *            �洢Ԫ�ص�˳��
	 * @param left
	 *            ��ʼԪ��
	 * @param right
	 *            ����Ԫ��
	 */
	static public void quickSorting(LinkedList list, int[] index, int left,
			int right) {
		double[] arr = new double[list.size()];
		for(int i=0;i<list.size();i++)
			arr[i] = ((Double)list.get(i)).doubleValue();
		quickSorting(arr, index, left, right);
	}

	/**
	 * ��������,������ ͨ��һ������Ҫ��������ݷָ�ɶ����������֣�����һ���ֵ��������ݶ�������һ���ֵ�
	 * �������ݶ�ҪС��Ȼ���ٰ��˷����������������ݷֱ���п�����������������̿��Եݹ� ���У��Դ˴ﵽ�������ݱ���������С�
	 * 
	 * @param arr
	 *            ��Ҫ�����һά���飬Ԫ������Ϊdouble
	 * @param index
	 *            �洢Ԫ�ص�˳��
	 * @param left
	 *            ��ʼԪ��
	 * @param right
	 *            ����Ԫ��
	 */
	static public void quickSorting(double[] arr, int[] index, int left,
			int right) {
		double[] newarr = new double[arr.length];
		for(int i=0;i<newarr.length;i++)
			newarr[i] = arr[i];
		sorting(newarr, index, left, right);
	}

	static private void sorting(double[] arr, int[] index, int left,
			int right) {
		if (left < right) {
			int[] middleindex = partition(arr, index, left, right);
			quickSorting(arr, index, left, middleindex[0]);
			quickSorting(arr, index, middleindex[1], right);
		}
		
	}
	/**
	 * @param pData
	 * @param left
	 * @param right
	 * @return 
	 */
	static private int[] partition(double[] pData, int[] index, int left, int right) {
		int[] part = new int[2];
		double middle, strTemp;
		int indextemp;
		int i = left;
		int j = right;
		middle = pData[(left + right) / 2];
		do {
			while ((pData[i] < middle) && (i < right))
				i++;
			while ((pData[j] > middle) && (j > left))
				j--;
			if (i <= j) {
				strTemp = pData[i];
				pData[i] = pData[j];
				pData[j] = strTemp;
				indextemp = index[i];
				index[i] = index[j];
				index[j] = indextemp;
				i++;
				j--;
			}
//			for (int k = 0; k < pData.length; k++) {
//				System.out.print(pData[k] + " ");
//			}
//			System.out.println();
		} while (i < j);// �������ɨ����±꽻�����һ������
		part[0] = j;
		part[1] = i;
        return part;
	}

	/**
	 * ��������,������ ͨ��һ������Ҫ��������ݷָ�ɶ����������֣�����һ���ֵ��������ݶ�������һ���ֵ�
	 * �������ݶ�ҪС��Ȼ���ٰ��˷����������������ݷֱ���п�����������������̿��Եݹ� ���У��Դ˴ﵽ�������ݱ���������С�
	 * @param pData
	 * @param left
	 * @param right
	 * @return 
	 */
	public static double[] Sort(double[] pData, int left, int right) {
		double middle, strTemp;
		int i = left;
		int j = right;
		middle = pData[(left + right) / 2];
		do {
			while ((pData[i] < middle) && (i < right))
				i++;
			while ((pData[j] > middle) && (j > left))
				j--;
			if (i <= j) {
				strTemp = pData[i];
				pData[i] = pData[j];
				pData[j] = strTemp;
				i++;
				j--;
			}
			for (int k = 0; k < pData.length; k++) {
				System.out.print(pData[k] + " ");
			}
			System.out.println();
		} while (i < j);// �������ɨ����±꽻�����һ������
		if (left < j)
			Sort(pData, left, j); // �ݹ����
		if (right > i)
			Sort(pData, i, right); // �ݹ����
		return pData;
	}


	static public void main(String[] args) {
		 LinkedList list = new LinkedList();
		 list.addLast(new Double(49));
		 list.addLast(new Double(38));
		 list.addLast(new Double(65));
		 list.addLast(new Double(5));
		 list.addLast(new Double(76));
		 list.addLast(new Double(69));
		 list.addLast(new Double(14));
		 list.addLast(new Double(0.67));
		 list.addLast(new Double(1.67));
		 list.addLast(new Double(14.3));
		double[] arr = { 100, 38, 65, 5, 0.11, 69, 5, 0.566, 0.888, 1.345, 0.234 };

		// listOperators lo = new listOperators();

		int[] index = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			index[i] = i;
		}
		//listOperators.Sort(arr, 0, arr.length - 1);
		for (int i = 0; i < arr.length; i++) {
			// System.out.print(((Double) list.get(index[i])).doubleValue() + "
			// ");
			System.out.print(arr[i] + "  ");
		}
		listOperators.quickSorting(list, index, 0, list.size() - 1);
		System.out.println();
		for (int i = 0; i < index.length; i++) {
			System.out.print(index[i] + "  ");
		}
		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			 System.out.print(((Double) list.get(index[i])).doubleValue() + " ");
			//System.out.print(arr[i] + "  ");
		}
		double x = 3.0;
		double y = 5.0;
		// System.out.println(x/y + " " + x%y);
	}
}
