package com.gojek.parking.enums;

/**
 * 
 * @author Vikas Garg
 *
 */
public enum Color {

	WHITE ("White"),
	BLACK ("Black"),
	RED("Red"),
	BLUE ("Blue");
	
	private String color = null;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	Color(String colorName) {
		this.color = colorName;
	}
}