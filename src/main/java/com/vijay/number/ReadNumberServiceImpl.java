package com.vijay.number;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class ReadNumberServiceImpl implements ReadNumberService {
	
	/**
	 * Actual text values of the given numberValue from 0 to 19
	 */
	private final String[] below20_array = new String[20];
	
	/**Number filter mapping for English wording
	 * 
	 */
	private final LinkedHashMap<Integer,String> numberFilterMap = new LinkedHashMap<Integer, String>();
	
	/**Number filter mapping for Roman wording
	 * 
	 */	
    private final LinkedHashMap<Integer, String> numberLimits = new LinkedHashMap<Integer, String>();

	/**Error message
	 * 
	 */	
	private String unsupportedMsg = "This input number is not supported. Please try between 1 and 3999";
	
	/**
	 * Constructor which initialises numberFilterMap and below20_array array.
	 */	
	public ReadNumberServiceImpl() {		
				
		numberFilterMap.put( new Integer(1000), "thousand" );
		numberFilterMap.put( new Integer(100), "hundred" );		
		
		below20_array[0] = "zero";
		below20_array[1] = "one";
		below20_array[2] = "two";
		below20_array[3] = "three";
		below20_array[4] = "four";
		below20_array[5] = "five";
		below20_array[6] = "six";
		below20_array[7] = "seven";
		below20_array[8] = "eight ";
		below20_array[9] = "nine";
		below20_array[10] = "ten";
		below20_array[11] = "eleven";
		below20_array[12] = "twelve";
		below20_array[13] = "thirteen";
		below20_array[14] = "fourteen";
		below20_array[15] = "fifteen";
		below20_array[16] = "sixteen";
		below20_array[17] = "seventeen";
		below20_array[18] = "eighteen";
		below20_array[19] = "nineteen";		
		
	    numberLimits.put(1, "I");
	    numberLimits.put(4, "IV");
	    numberLimits.put(5, "V");
	    numberLimits.put(9, "IX");
	    numberLimits.put(10, "X");
	    numberLimits.put(40, "XL");
	    numberLimits.put(50, "L");
	    numberLimits.put(90, "XC");
	    numberLimits.put(100, "C");
	    numberLimits.put(400, "CD");
	    numberLimits.put(500, "D");
	    numberLimits.put(900, "CM");
	    numberLimits.put(1000, "M");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String numberToEnglish(int numberValue) throws UnsupportedOperationException {
		
		if (numberValue > 0 && numberValue < 4000) {
			
			String returnEnglishWord = "";			
			Set entrySet = numberFilterMap.entrySet();				
			Iterator it = entrySet.iterator();		
			
			while(it.hasNext()) {
			
				Map.Entry m=(Map.Entry) it.next();			 
				int numberfilter = (Integer)m.getKey();
				String numberfilter_text =(String)m.getValue();
             
				String readText = " ";             
				int get100s_remainder = numberValue % numberfilter;
             
				int get100s = 0;
                          
				if(numberfilter>100) {           
					get100s = (numberValue - get100s_remainder)/numberfilter;
					numberValue -= get100s*numberfilter;
				}
				else {
					get100s = numberValue;
					numberfilter_text = "";
				}
             
				if( has100s(get100s) && has10s(get100s) )
					readText += read100s(get100s) + " hundred and " + read10s(get100s) +" "+ numberfilter_text;
             
				else if( has100s(get100s) && !has10s(get100s) )
					readText += read100s(get100s) + " hundred "+ numberfilter_text;
             
				else if( !has100s(get100s) && has10s(get100s) )
					readText += read10s(get100s)  +" "+ numberfilter_text;
             
             
				returnEnglishWord += readText;            
            		
			}
			
			returnEnglishWord = returnEnglishWord.trim();
			
			returnEnglishWord = returnEnglishWord.replaceAll("\\s+", " ");	
			
			if(returnEnglishWord.length()==0) returnEnglishWord = "zero";
		
			returnEnglishWord = returnEnglishWord.toLowerCase();
			
		    StringBuilder sb = new StringBuilder(returnEnglishWord); // one StringBuilder object  
		    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));  
		    returnEnglishWord = sb.toString(); // one String object  
			
			return returnEnglishWord;
			
	    } else {
	        throw new UnsupportedOperationException(unsupportedMsg);
	    }
	}

	@Override
	public String numberToRoman(int numberValue) throws UnsupportedOperationException {
	    if (numberValue > 0 && numberValue < 4000) {



	        String romanNumeral = "";

	        while (numberValue > 0) {
	            int highestFound = 0;
	            for (Map.Entry<Integer, String> current : numberLimits.entrySet()){
	                if (current.getKey() <= numberValue) {
	                    highestFound = current.getKey();
	                }
	            }
	            romanNumeral += numberLimits.get(highestFound);
	            numberValue -= highestFound;
	        }

	        return romanNumeral;

	    } else {
	        throw new UnsupportedOperationException(unsupportedMsg);
	    }

	}
	
	/**
	 * Implementation of the read100s method
	 */
	private String read100s(int numberValue) {
		
		int get_10s = numberValue % 100;
				
		String text100s = "";
		
		if( (get_10s < numberValue || get_10s == 0) ) text100s = below20( (numberValue - get_10s)/100 );
		
		return text100s;
	}
	
	/**Private method checks whether given numberValue has 100s in it. 
	 * @param numberValue
	 * @return boolean
	 * @throws Exception
	 */
	private boolean has100s(int numberValue) {
		int get_10s = numberValue % 100;
		
		boolean has100s = false;
		
		if( (get_10s < numberValue || get_10s == 0) ) has100s = ( (numberValue - get_10s)/100 > 0 && (numberValue - get_10s)/100 <= 999);
		
		return has100s;
	}
	
	/**Private methos to determine fiven numberValue has 10s in it. 
	 * @param numberValue
	 * @return
	 * @throws Exception
	 */
	private boolean has10s(int numberValue) {
		int get_10s = numberValue % 100;
		
		boolean has10s = get_10s>0;
		
		return has10s;
	}
	
	/**
	 * Implementation of the read10s method
	 * which calls either below20() or above19to99() by passing numberValue to them
	 */
	private String read10s(int numberValue) {
		
		String text10s = "";
		
		int get_10s = numberValue % 100;
		
		if(get_10s < 20) text10s = below20(get_10s);
		else text10s = above19to99(get_10s);
		
		return text10s;
	}
	
	/**
	 * private method to read numbers from 20 to 99
	 * Which also calls below20 to read numbers from 1 to 19
	 * @param numberValue
	 * @return String
	 */
	private String above19to99(int numberValue) {
		
		String[] text10s_array = {"", "", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
		
		String text10s = "";
		String text10s_1 = "";
		String text10s_2 = "";
		
		int check_below20 = numberValue % 10;
		
		int actual10s = (numberValue - check_below20)/10;
		
		if(actual10s>0)
		text10s_1 = text10s_array[actual10s];	
		
		if(check_below20>0)
		text10s_2 = below20(check_below20);
		
		if(text10s_1.length()>0 && text10s_2.length()>0 )
		text10s = text10s_1 +" "+ text10s_2;
		else if(text10s_1.length()>0 && text10s_2.length()==0)
		text10s = text10s_1;
		else if(text10s_1.length()==0 && text10s_2.length()>0)
		text10s = text10s_2;
		
		return text10s;
	}
	
	/**
	 * private method reads numbers from 0 to 19
	 * @param numberValue
	 * @return String
	 */
	
	private String below20(int numberValue) {		
		return below20_array[numberValue];
	}

}
