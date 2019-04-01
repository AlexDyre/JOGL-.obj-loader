package objects;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;

import renderer.Renderer;
import util.Vertex2d;

public class WireframeButton extends Button implements MouseListener {
	
	private Renderer renderer;

	public WireframeButton(float[] color, float[] shadowColor, Vertex2d pos, float h, float w, GL2 gl, String label, Renderer renderer, GLCanvas canvas) {
		super(color, shadowColor, pos, h, w, gl, label);
		this.renderer = renderer;
		canvas.addMouseListener(this);
	}
	
	@Override
	public void press() {
		super.press();
		renderer.toggleWireframe();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1 ) {
			float x = e.getX();
			float y = Renderer.windowHeight - e.getY();
			
			float glX = 2.0f * (x / Renderer.windowWidth) - 1.0f;
			float glY = 2.0f * (y / Renderer.windowHeight) - 1.0f;
			
			// check if the coordinates are within the bounds for this button
			if ((glX >= pos.x && glX <= pos.x + width) && (glY >= pos.y && glY <= pos.y + height)) {
				press();
			} 
		}
		 
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
