package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import shapes.Rectangle;
import util.Vertex2d;

/**
 * 
 * @author Jordan Carter - 1317225
 *
 */
public class Button extends Rectangle {

	private String label;
	private GL2 gl;
	private GLUT glut;
	public Vertex2d pos;
	private float stringWidth = 0.0f;
	private float offSetX;
	private float offSetY;
	public boolean state;
	public String stateString = "";
	public float[] defaultColor;
	public float[] onColor;
	public float[] offColor;
	
	/**
	 * 
	 * @param color
	 * @param shadowColor
	 * @param pos
	 * @param h
	 * @param w
	 * @param gl
	 * @param label
	 */
	public Button(float[] color, float[] shadowColor, Vertex2d pos, float h, float w, GL2 gl, String label) {
		super(color, pos, h, w);
		this.label = label;
		this.gl = gl;
		this.defaultColor = color;
		this.onColor = color;
		this.offColor = shadowColor;
		this.pos = pos;
		this.state = false;
		glut = new GLUT();

		stringWidth = glut.glutBitmapLength(GLUT.BITMAP_8_BY_13, label);
		offSetX = ((width - (stringWidth / 1000.0f)) / 2.0f) / 2.0f;
		offSetY = (height / 2) - ((13.0f / 2.0f) / 1000.0f);
	}

	/**
	 * Draw's the buttons given label
	 */
	public void drawLabel() {
		// set the color of the label based on state
		if (state == false) {
			// black 
			gl.glColor3f(0.0f, 0.0f, 0.0f);
		} else {
			// white
			gl.glColor3f(1.0f, 1.0f, 1.0f);
		}
		
		gl.glRasterPos2d(pos.x + offSetX, pos.y + offSetY);
		glut.glutBitmapString(GLUT.BITMAP_8_BY_13, label);
	}

	/**
	 * Run when the button is pressed.
	 * Flips the state of the button
	 */
	public void press() {
		state = (state == true) ? false : true;
		stateString = (state == true) ? "on" : "off";
		super.color = (state == true) ? offColor : onColor ;
	}

	@Override
	public void draw(GL2 gl) {
		super.draw(gl);
		drawLabel();
	}
}
