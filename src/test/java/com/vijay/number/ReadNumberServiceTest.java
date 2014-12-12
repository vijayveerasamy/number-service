package com.vijay.number;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:spring/servlet-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ReadNumberServiceTest {
	 
	@Autowired
    protected ReadNumberService readNumberService;

	@Test
    public void readNumberEnglish() {
    	try {
    		String returnValue = null;
    		returnValue = this.readNumberService.numberToEnglish(10);
    		assertEquals("Ten", returnValue);
    		returnValue = this.readNumberService.numberToRoman(10);
    	}
    	catch(UnsupportedOperationException ex){
    		ex.printStackTrace();
    	}     

    }
	
	@SuppressWarnings("unused")
	@Test(expected=UnsupportedOperationException.class)
	public void testUnsupportedOperationException() {
		String returnValue = this.readNumberService.numberToRoman(4000);
	}
    
}
