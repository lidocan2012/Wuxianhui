package com.wifihi.util;

public class StringOpera {
    
	/**
	 * 返回source中字符x之前的子字符串
	 * @param source
	 * @param x
	 * @return
	 */
	public static String getPreSubstring(String source, char x){
	    String presubstr = new String();
	    for(int i=0;i<source.length();i++){
	    	if(source.charAt(i) == x){
	    		presubstr = source.substring(0, i);
	    		break;
	    	}
	    }
	    return presubstr;
	}
	/**
	 * 返回source中字符x之后的子字符串
	 * @param source
	 * @param x
	 * @return
	 */
	public static String getAfterSubstring(String source, char x){
	    String aftersubstr = new String();
	    for(int i=0;i<source.length();i++){
	    	if(source.charAt(i) == x){
	    		aftersubstr = source.substring(i+1, source.length());
	    		break;
	    	}
	    }
	    return aftersubstr;
	}
	
	public static void main(String args[]) {
		try{
			StringOpera so = new StringOpera();
			String test = new String("1090x2m40");
			System.out.println("presub="+so.getPreSubstring(test, 'x'));
			System.out.println("aftersub="+so.getAfterSubstring(test, 'x'));
		}
		catch(Exception e){
			
		}
	}
}
