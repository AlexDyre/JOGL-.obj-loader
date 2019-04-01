package objects;

import util.ColorRGB;
import util.ObjMtl;
import util.Vector3;

public class TriangleFace {
	public Vector3 v1, v2, v3, normal;
	public int[] vertexPos;
	public ColorRGB color;
	public ObjMtl material;
	public boolean useMtl;
	private ObjObject obj;
	
	public TriangleFace() {
		this.vertexPos = new int[3];
		this.obj = null;
		this.useMtl = false;
		this.material = null;
	}
	
	public TriangleFace(int[] vertexPosition, ObjObject obj) {
		this.vertexPos = vertexPosition;
		this.obj = obj;
		this.useMtl = false;
		this.material = null;
	}
	
	@Override
	public String toString() {
		return "v1: " + v1 + " v2: " + v2 + " v3: " + v3 + "\n";
	}
	
	
}
