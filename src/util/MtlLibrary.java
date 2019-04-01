package util;

import java.util.ArrayList;

public class MtlLibrary {

	public String name;
	public ArrayList<ObjMtl> materials;
	
	public MtlLibrary(String name) {
		this.name = name;
		this.materials = new ArrayList<ObjMtl>();
	}
	
	@Override
	public String toString() {
		String mtls = "";
		for (ObjMtl mtl : materials) {
			mtls += mtl.mtlName + ", ";
		}
		return "Materials: " + mtls;
	}
}
