package com.wifihi.util;

/**
 * 取小数点后几位，不四舍五入
 * @author sush
 *
 */
public class getBitsafterDot {

	/**
	 * 保留decimal小数点后numofbit位返回
	 * @param decimal
	 * @param numofbit
	 * @return
	 */
    static public double getBitsafter(double decimal, int numofbit){
    	String str = ((Double)decimal).toString();
		int num = 0;
		boolean after = false;
    	for(int i=0;i<str.length();i++){
    		if(str.charAt(i) == '.'){
    			num++;
    			after = true;
    		}
    		else{
    			if(after)
    				num++;
    		}
    		if(num == numofbit + 1){   			
    			return new Double(str.substring(0, i+1)).doubleValue();
    		}
    	}
    	return decimal;
    }
    
	/**
	 * 保留decimal小数点后numofbit位返回
	 * @param decimal
	 * @param numofbit
	 * @return
	 */
    static public float getBitsafter(float decimal, int numofbit){
    	String str = ((Float)decimal).toString();
		int num = 0;
		boolean after = false;
    	for(int i=0;i<str.length();i++){
    		if(str.charAt(i) == '.'){
    			num++;
    			after = true;
    		}
    		else{
    			if(after)
    				num++;
    		}
    		if(num == numofbit + 1){   			
    			return new Float(str.substring(0, i+1)).floatValue();
    		}
    	}
    	return decimal;
    }    
}

