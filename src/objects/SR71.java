package objects;

import com.jogamp.opengl.GL2;

public class SR71 extends ObjObject {

	public SR71(String path, GL2 gl) {
		super(path, gl);
		pos.z = -1.5;
	}

	public void animate() {
		pos.z = (pos.z > 1.6) ? -1.5 : (pos.z += 0.002);
	}
}
