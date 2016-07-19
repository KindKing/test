package mvc.mapping;

import java.util.HashMap;
import java.util.Map;

public class ActionMapping {
	
	public ActionMapping(){
		
	}
	
	public static Map<String,String> getMap(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("mvc.form.LoginForm", "mvc.action.LoginAction");
		map.put("mvc.form.RegisterForm", "mvc.action.RegisterAction");
		return map;
	}
}
