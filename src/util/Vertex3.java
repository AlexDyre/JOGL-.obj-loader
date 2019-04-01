package util;

/**
 * 
 * @author Jordan Carter - 1317225
 *
 */
public class Vertex3 {
	
	public double x, y, z;
	
	/**
	 * Initialises a new Vertex3 with x = x, y = y, z = z
	 * @param x double x pos
	 * @param y double y pos
	 * @param z double z pos
	 */
	public Vertex3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Initialises a new Vertex3 with x = 0.0, y = 0.0, z = 0.0
	 */
	public Vertex3() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	@Override
	public String toString() {
		return "x: " + x + " y: " + y + " z: " + z;
	}
}
