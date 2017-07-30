package com.gojek.parking.enums;

/**
 * To maintain various Colours available for Car
 * 
 * @author Vikas Garg
 */
public enum Colour {

	WHITE ("White"),
	BLACK ("Black"),
	RED ("Red"),
	BLUE ("Blue");
	
	private String colour = null;
	
	Colour(String colourName) {
		this.colour = colourName;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	/**
	 * To get Colour enum by its name
	 * 
	 * @param colourName
	 * @return
	 */
	public static Colour getColourByName(String colourName) {
		Colour matchedColour = null;
		
		for (Colour localColour : Colour.values()) {
			if (localColour.getColour().equalsIgnoreCase(colourName)) {
				matchedColour = localColour;
				break;
			}
		}
		return matchedColour;
	}
}