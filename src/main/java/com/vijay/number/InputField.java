package com.vijay.number;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class InputField {
	
    @Min(value=1, message="Enter number between 1 and 3999")
    @Max(value=3999, message="Enter number between 1 and 3999")
    @NotNull(message="Enter number between 1 and 3999")
	private int numberValue;

	public int getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(int numberValue) {
		this.numberValue = numberValue;
	}
}
