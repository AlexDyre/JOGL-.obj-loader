package renderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import objects.Biplane;
import objects.ObjObject;
import objects.SR71;

// TODO: need to implement this, I assume this controls the speed of the animation
//import utils.SimulationTime;

public class Renderer implements GLEventListener, KeyListener {
	
	private static GLCanvas canvas;
	
	public static int windowHeight = 1024;
	public static int windowWidth = 1024;

	//track-ball camera
	//private static TrackballCamera camera;
	private static CameraController camera;
	
	private GLUT glut;
	
	private ObjObject cube, sr71Scene, colorCube;
	private SR71 plane;
	private Biplane biplane;
	//private WireframeButton wireframeButton;
	
	public boolean wireFrame;
	
	// test of animation
	public float moveAmount = 0.0f;
	
	public Renderer(GLCanvas canvas) {
		super();
		System.out.println("Render init");
		Renderer.canvas = canvas;
		this.wireFrame = false;
	}
	

	@Override
	public void display(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();
		//GLU glu = new GLU();
		glut = new GLUT();
		
		
		
		// select and clear the model-view matrix
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		//gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glEnable(GL2.GL_LINE_SMOOTH);
		gl.glEnable(GL2.GL_SMOOTH);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		if (wireFrame) {
			gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		} else {
			gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
		}
		
		// animation
		plane.animate();
		biplane.animate();
		
		
		// enable z-testing
		gl.glDepthMask(true);
		
		
		
		//<your drawing and other code>
		// Camera
		camera.draw(gl);
		// Lights
		lights(gl);
		
		//gl.glPushMatrix();
		
		//gl.glTranslated(0, 0, -10);
		//glut.glutSolidTeapot(1);
		//gl.glPopMatrix();
		
		//cube.draw(gl);
		
		//plane.draw(gl);
		
		//colorCube.draw(gl);
		
		sr71Scene.draw(gl);
		plane.draw(gl);
		biplane.draw(gl);
		// Should object specific draw methods handle pushing and pop-ing from the matrix stack
		/*
		 * gl.glPushMatrix();
		 * 
		 * gl.glTranslatef(0.0f, 0.0f, -1.5f + moveAmount); plane.draw(gl); moveAmount
		 * += 0.002f; moveAmount = (moveAmount > 3.2f) ? 0.0f : moveAmount;
		 * gl.glPopMatrix();
		 */
		
		// we want to render all opaque objects
		
		// now we want to disable z-testing and render transparent objects
		gl.glDepthMask(false);
		
		// Re-enable depth testing for the next frame
		// might remove this if enabling at the start of each frame is better
		
		//plane.draw(gl);
		
		
		
		
		gl.glDepthMask(true);
		
		// draw the UI over the top of the rest of the elements
		// disable depth testing and masking
		//gl.glDepthMask(false);
		//gl.glDisable(GL2.GL_DEPTH_TEST);
		
		// now draw the UI
		//wireframeButton.draw(gl);
		gl.glFlush();

	}
	
	public void toggleWireframe() {
		System.out.println("Wireframe toggled");
		wireFrame = (wireFrame) ? false : true;
	}


	

	@Override
	public void init(GLAutoDrawable drawable) {
		System.out.println("Starting renderer setup");
		GL2 gl = drawable.getGL().getGL2();
		System.err.println("INIT GL IS: " + gl.getClass().getName());
		// Enable V-Sync
		gl.setSwapInterval(1);

		// Setup the drawing area and shading
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glShadeModel(GL2.GL_SMOOTH);
		
		// Enable depth testing
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
		
		
	    //<other init code here>
		
		// intialise the camera
		//Renderer.camera = new TrackballCamera(canvas);
		Renderer.camera = new CameraController(canvas);
		System.out.println("Camera set up");
		
		
		//use the lights
		this.lights(gl);
		
		//cube = new ObjObject("cube.obj", gl);
		plane = new SR71("SR-71.obj", gl);
		sr71Scene = new ObjObject("SR-71Scene.obj" ,gl);
		//wireframeButton = new WireframeButton(new float[] {1.0f, 1.0f, 1.0f, 1.0f}, new float[] {0.33f, 0.33f, 0.33f, 1.0f}, new Vertex2d(-0.95, 0.875), 0.1f, 0.3f, gl, "Wireframe On/Off", this, canvas);
		colorCube = new ObjObject("ColorCube.obj", gl);
		biplane = new Biplane("biplane.obj", gl);

	}

	private void lights(GL2 gl) {
		// lighting stuff
		float ambient[] = { 0, 0, 0, 1 };
		float diffuse[] = {1f, 1f, 1f, 1 };
		float specular[] = { 1, 1, 1, 1 };
		
		float[] ambientLight = { 0.1f, 0.1f, 0.1f,0f };  // weak RED ambient 
		gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_AMBIENT, ambientLight, 0); 
		
		float position0[] = { 5, 5, 5, 0 };
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position0, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specular, 0);
		
		float position1[] = { -10, -10, -10, 0 };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position1, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambient, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, specular, 0);
		
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_LIGHT1);
	
		//lets use use standard color functions
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		//normalise the surface normals for lighting calculations
		gl.glEnable(GL2.GL_NORMALIZE);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		
		height = (height == 0) ? 1 : height;
		windowHeight = height;
		windowWidth = width;
		
		// avoid a divide by zero error when calculating aspect ratio!
		height = (height <= 0) ? 1 : height;
		final float h = (float) width / (float) height;
		// specify the affine transformation of x and y from normalized device
		// coordinates to window coordinates
		gl.glViewport(0, 0, width, height);

		// switch to projection mode
		gl.glMatrixMode(GL2.GL_PROJECTION);
		// Reset the current matrix to the "identity"
		gl.glLoadIdentity();
		// gluPerspective(FOV, aspect ratio, near, far)
		glu.gluPerspective(45.0f, h, 1.0, 40.0);
		// finished modifying projection matrix switch back to model_view matrix
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		// Reset the current matrix to the "identity"
		gl.glLoadIdentity();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch (key) {
		case KeyEvent.VK_UP:
			System.out.println("Up key pressed");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("Down key pressed");
			break;
		case KeyEvent.VK_1:
			System.out.println("1 key pressed");
			break;
		case KeyEvent.VK_SPACE:
			toggleWireframe();
		default:
			break;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void dispose(GLAutoDrawable drawable) {}
}
