package shapes;

import com.jogamp.opengl.GL2;

import util.Vertex2d;

/**
 * 
 * @author Jordan Carter - 1317225
 *
 */
public class Rectangle {
	
	public float[] color;
	// position of the bottom left corner
	public Vertex2d pos;
	public float height;
	public float width;
	
	/**
	 * Constructor supplying all options for a rectangle
	 * 
	 * @param color float[4] for RGBA color, 0.0 - 1.0
	 * @param pos float[2] x,y
	 * @param h float height of the rectangle
	 * @param w float width of the rectangle
	 */
	public Rectangle(float[] color, Vertex2d pos, float h, float w) {
		this.color = color;
		this.pos = pos;
		this.height = h;
		this.width = w;
	}
	
	/**
	 * Draws the rectangle
	 * @param gl
	 */
	public void draw(GL2 gl) {
		gl.glColor4fv(color, 0);
		
		gl.glBegin(GL2.GL_QUADS);
			// BL
			gl.glVertex2d(pos.x, pos.y);
			// BR
			gl.glVertex2d(pos.x + width, pos.y);
			// TR
			gl.glVertex2d(pos.x + width, pos.y + height);
			// TL
			gl.glVertex2d(pos.x, pos.y + height);
		gl.glEnd();
	}
}
