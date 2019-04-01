package util;

/**
 * 
 * @author Jordan Carter - 1317225
 *
 */
public class Vector3 {
	
	public double x, y, z;
	
	/**
	 * Initialises a new Vector3 with x = x, y = y, z = z
	 * @param x double x pos
	 * @param y double y pos
	 * @param z double z pos
	 */
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Initialises a new Vector3 with x = 0.0, y = 0.0, z = 0.0
	 */
	public Vector3() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	public Vector3(Vector3 vector3) {
		this.x = vector3.x;
		this.y = vector3.y;
		this.z = vector3.z;
	}

	@Override
	public String toString() {
		return "x: " + x + " y: " + y + " z: " + z;
	}
	
	public void multiply(double x) {
		this.x *= x;
		this.y *= x;
		this.z *= x;
	}
	
	public double length() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Normalises the vector
	 */
	public void normalise() {
		double length = this.length();
		x /= length;
		y /= length;
		z /= length;
	}
	
	/**
	 * Returns a new normalised vector 3 of this vector
	 * @return Vector3
	 */
	public Vector3 normalised() {
		Vector3 vector3 = new Vector3(this);
		vector3.normalise();
		return vector3;
	}
	
	public void move(Vector3 target, double distance) {
		
	}
}
