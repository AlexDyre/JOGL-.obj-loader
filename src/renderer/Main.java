package renderer;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

import objects.ObjObject;
import util.ObjLoader;

/**
 * Main class for the Assignment 2 program
 * Initialises and sets up the window and environment
 * @author Jordan Carter - 1317225
 *
 */
public class Main {

	public static void main(String[] args) {
		
		
		System.out.println("Creating obj from file");
		
		/*
		 * ObjObject cube = new ObjObject("cube.obj");
		 * 
		 * System.out.println(cube);
		 */
		
		boolean run3D = true;
		
		if (run3D) {
			System.out.println("Initialising Project");
			Frame frame = new Frame(".obj model parser");
			GLCanvas canvas = new GLCanvas();
			Renderer renderer = new Renderer(canvas);
			canvas.addGLEventListener(renderer);
			canvas.addKeyListener(renderer);
			frame.add(canvas);
			frame.setSize(640, 640);
			
			final FPSAnimator animator = new FPSAnimator(canvas, 60);
			
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							animator.stop();
							System.exit(0);
						}
					}).start();
				}
			});
			
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			canvas.requestFocus();
			animator.start();
		}
	}
}
