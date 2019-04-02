package objects;

import java.util.ArrayList;
import com.jogamp.opengl.GL2;

import util.ColorRGB;
import util.MtlLibrary;
import util.ObjLoader;
import util.Vector3;

public class ObjObject {
	private String filename;
	public ArrayList<Vector3> vertices, normals;
	public ArrayList<TriangleFace> tris;
	public MtlLibrary mtlLibrary;
	public Vector3 pos;
	
	private int triDisplayList, base;
	
	/**
	 * Default constructor, initialises an empty object
	 */
	public ObjObject() {
		this.filename = "";
		this.vertices = new ArrayList<Vector3>();
		this.tris = new ArrayList<TriangleFace>();
		this.pos = new Vector3();
	}
	
	/**
	 * Constructor for an ObjObject from a specified file
	 * @param path path to .obj file for importing
	 * @param importer obj importer
	 */
	public ObjObject(String path, GL2 gl) {
		this.filename = path;
		this.vertices = new ArrayList<Vector3>();
		this.normals = new ArrayList<Vector3>();
;		this.tris = new ArrayList<TriangleFace>();
		this.pos = new Vector3();
		
		ObjLoader.importModel(path, this);
		this.base = gl.glGenLists(1);
		compileTriList(gl, base);
	}
	
	/**
	 * Compiles the 
	 * @param gl
	 * @param index
	 */
	private void compileTriList(GL2 gl, int index) {
		triDisplayList = index;
		
		gl.glNewList(triDisplayList, GL2.GL_COMPILE);
			
		gl.glColor4d(0.2, 0.2, 0.2, 1.0);
			if (filename.equalsIgnoreCase("biplane.obj")) {
				gl.glScaled(0.01, 0.01, 0.01);
			}
			
			ColorRGB color;
			for (TriangleFace face : tris) {
				if (face.useMtl && face.material.transparency >= 1.0) {
					color = face.material.diffuse;
					gl.glColor4d(color.red, color.green, color.blue, face.material.transparency);
					
					gl.glBegin(GL2.GL_POLYGON);
						//need to set normal for the face first
						gl.glNormal3d(face.normal.x, face.normal.y, face.normal.y);
						gl.glVertex3d(face.v1.x, face.v1.y, face.v1.z);
						gl.glVertex3d(face.v2.x, face.v2.y, face.v2.z);
						gl.glVertex3d(face.v3.x, face.v3.y, face.v3.z);
					gl.glEnd();
				}
				
			}
			
			for (TriangleFace face : tris) {
				if (face.useMtl && face.material.transparency < 1.0) {
					color = face.material.diffuse;
					gl.glColor4d(color.red, color.green, color.blue, face.material.transparency);
					
					gl.glBegin(GL2.GL_POLYGON);
						//need to set normal for the face first
						gl.glNormal3d(face.normal.x, face.normal.y, face.normal.y);
						gl.glVertex3d(face.v1.x, face.v1.y, face.v1.z);
						gl.glVertex3d(face.v2.x, face.v2.y, face.v2.z);
						gl.glVertex3d(face.v3.x, face.v3.y, face.v3.z);
					gl.glEnd();
				}
				
			}
			gl.glScaled(1, 1, 1);
		gl.glEndList();
	}
	
	@Override
	public String toString() {
		return "File: " + filename + " Vertices: " + vertices.size() + " Triangles: " + tris.size() + " Normals: " + normals.size() + " Materials: " + mtlLibrary + " Position: " + pos;
	}
	
	public void draw(GL2 gl) {
		gl.glPushMatrix();
			//gl.glRotated(arg0, arg1, arg2, arg3);
			//gl.glScaled(arg0, arg1, arg2);
			gl.glTranslated(pos.x, pos.y, pos.z);
			gl.glCallList(triDisplayList);
		gl.glPopMatrix();
	}
	
}
