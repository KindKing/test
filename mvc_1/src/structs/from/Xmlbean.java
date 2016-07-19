package structs.from;

import java.util.HashMap;
import java.util.Map;

public class Xmlbean {
	
	private String name;
	
	private String type;
	
	private String path;
	
	private String clas;
	
	private Map<String, String> forward = new HashMap<String,String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public Map<String, String> getForward() {
		return forward;
	}

	public void setForward(Map<String, String> forward) {
		this.forward = forward;
	}
	
	
}
