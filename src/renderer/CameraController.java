package renderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import util.Vector3;

public class CameraController implements MouseListener, KeyListener {
	
	private Vector3 rotation, translatePosition;
	private Vector3 pos;
	private Vector3 target;
	private Vector3 forward, side;
	private GLU glu;
	
	public double FOV, windowWidth, windowHeight, distanceToOrigin;
	
	public CameraController(GLCanvas canvas) {
		canvas.addMouseListener(this);
		canvas.addKeyListener(this);
		this.glu = new GLU();
		this.FOV = 45;
		this.windowHeight = 1;
		this.windowWidth = 1;
		this.distanceToOrigin = 5;
		this.rotation = new Vector3();
		this.translatePosition = new Vector3();
         
        this.pos = new Vector3();
        this.pos.y = 1.0;
        this.pos.z = 2.0;
        this.pos.x = 0.2;
        this.rotation.x = 180;
        this.rotation.y = -30;
        this.target = new Vector3();
        this.forward = new Vector3(0, 1, 0);
        this.side = new Vector3(1, 0, 0);
	}
	
	public void draw(GL2 gl) {
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(FOV, windowWidth / windowHeight, 0.1, distanceToOrigin * 3);
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        // apply translation
        //move();
        pos.y += translatePosition.y;
        translatePosition.y = 0.0;
        // apply rotation
        rotate();
        // calculate the new look position
        calculateTarget();

        glu.gluLookAt(
                pos.x, pos.y, pos.z,
                target.x, target.y, target.z,
                forward.x, forward.y, forward.z);
	}
	
	private void move(double rotOffset, double distance) {
		double r = distance * Math.cos(Math.toRadians(rotation.y));
		
		pos.x += r * Math.sin(Math.toRadians(rotation.x + rotOffset));
		//pos.y += r * Math.sin(Math.toRadians(rotation.y + rotOffset));
		pos.z += r * Math.cos(Math.toRadians(rotation.x + rotOffset));
	}
	
	private void calculateTarget() {
		double r = distanceToOrigin * Math.cos(Math.toRadians(rotation.y));
		
		target.x = pos.x + (r * Math.sin(Math.toRadians(rotation.x)));
		target.y = pos.y + (r * Math.sin(Math.toRadians(rotation.y)));
		target.z = pos.z + (r * Math.cos(Math.toRadians(rotation.x)));
	}
	
	private void rotate() {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch (key) {
		case KeyEvent.VK_W:
			System.out.println("W key pressed");
			move(0, 0.1);
			break;
		case KeyEvent.VK_S:
			System.out.println("S key pressed");
			move(0, -0.1);
			break;
		case KeyEvent.VK_A:
			System.out.println("A key pressed");
			move(90, 0.1);
			break;
		case KeyEvent.VK_D:
			System.out.println("D key pressed");
			move(90, -0.1);
			break;
		case KeyEvent.VK_Q:
			System.out.println("Q key pressed");
			translatePosition.y -= 0.1;
			break;
		case KeyEvent.VK_E:
			System.out.println("E key pressed");
			translatePosition.y += 0.1;
			break;
		case KeyEvent.VK_UP:
			System.out.println("Up key pressed");
			rotation.y += 1;
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("Down key pressed");
			rotation.y -= 1;
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("Left key pressed");
			rotation.x += 1;
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("Right key pressed");
			rotation.x -= 1;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
