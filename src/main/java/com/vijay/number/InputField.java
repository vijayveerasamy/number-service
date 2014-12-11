package com.vijay.number;


public class InputField {
	
    /*@Min(value=1, message="Minimum 1")
    @Max(value=3999, message="Maximum 3999")
    @Digits(integer=4, fraction=0, message="Enter number between 1 and 3999")
    @NotEmpty*/
	private int numberValue;

	public int getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(int numberValue) {
		this.numberValue = numberValue;
	}
}
