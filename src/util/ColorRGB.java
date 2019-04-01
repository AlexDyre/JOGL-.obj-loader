package util;

public class ColorRGB {
	public double red, blue, green;
	
	// TODO: All colors values should be limited from 0.0 to 1.0 inclusive
	
	public ColorRGB() {
		this.red = 1.0;
		this.green = 1.0;
		this.blue = 1.0;
	}
	
	public ColorRGB(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	@Override
	public String toString() {
		return "R: " + red + " G: " + green + " B: " + blue;
	}
}
