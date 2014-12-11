package com.vijay.number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	private final ReadNumberService readNumberService;
	
	@Autowired
	public HomeController(ReadNumberService readNumberService) {
		this.readNumberService = readNumberService;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		InputField inputField = new InputField();
    
        model.addAttribute("inputField", inputField);
		
		return "home";
	}
	
	@RequestMapping(value = "/", params = "english", method = RequestMethod.POST)
	public String readEnglish(Model model, @ModelAttribute InputField inputField, BindingResult result) {
		
	    if (result.hasErrors()) {
	    	logger.info("Binding error: "+result.toString());
			inputField.setNumberValue(0);
			model.addAttribute("errorMsg", "Invalid input, please enter number 1 and 3999.");
	        return "home";
	    }
		
		logger.info("Input value = "+inputField.getNumberValue());
		model.addAttribute("numberValue", inputField.getNumberValue());
		
		try {
			String returnValue = readNumberService.numberToEnglish(inputField.getNumberValue());
			model.addAttribute("numberValue", inputField.getNumberValue());	
			model.addAttribute("returnLabel", "English word");
			model.addAttribute("returnValue", returnValue);
		}
		catch(UnsupportedOperationException ex) {
			inputField.setNumberValue(0);
			model.addAttribute("errorMsg", ex.getMessage());
			logger.info(ex.getMessage());
		}
		
		model.addAttribute("inputField", inputField);
		
		return "home";
	}
	
	@RequestMapping(value = "/", params = "roman", method = RequestMethod.POST)
	public String readRoman(Model model, @ModelAttribute InputField inputField, BindingResult result) {
		
	    if (result.hasErrors()) {
	    	logger.info("Binding error: "+result.toString());
			inputField.setNumberValue(0);
			model.addAttribute("errorMsg", "Invalid input, please enter number 1 and 3999.");
	        return "home";
	    }
		
		logger.info("Input value = "+inputField.getNumberValue());
		model.addAttribute("numberValue", inputField.getNumberValue());
		
		try {
			String returnValue = readNumberService.numberToRoman(inputField.getNumberValue());
			model.addAttribute("numberValue", inputField.getNumberValue());	
			model.addAttribute("returnLabel", "Roman numeral");
			model.addAttribute("returnValue", returnValue);
		}
		catch(UnsupportedOperationException ex) {
			inputField.setNumberValue(0);
			model.addAttribute("errorMsg", ex.getMessage());
			logger.info(ex.getMessage());
		}
		
		model.addAttribute("inputField", inputField);
		
		return "home";
	}
	
}
