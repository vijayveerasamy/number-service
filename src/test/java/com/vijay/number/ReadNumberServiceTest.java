package com.vijay.number;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
    		returnValue = this.readNumberService.numberToRoman(4000);
    	}
    	catch(UnsupportedOperationException ex){
    		ex.printStackTrace();
    	}     

    }
    
}
