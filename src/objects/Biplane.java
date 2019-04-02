package objects;

import com.jogamp.opengl.GL2;

public class Biplane extends ObjObject {

	public Biplane(String path, GL2 gl) {
		super(path, gl);
		pos.z = -1.5;
		pos.x += 0.225;
		pos.y += 0.1;
	}

	public void animate() {
		pos.z = (pos.z > 1.6) ? -1.5 : (pos.z += 0.001);
	}
}
