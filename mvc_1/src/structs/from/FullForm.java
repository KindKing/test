package structs.from;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

public class FullForm {
	
	public FullForm(){
		
	}
	
	public static ActionForm full(String path,HttpServletRequest request){
		ActionForm actionform = null;
		Class clazz;
		try {
			clazz = Class.forName(path);
			actionform = (ActionForm) clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for(Field f:fields){
				f.setAccessible(true);
				f.set(actionform, request.getParameter(f.getName()));
				f.setAccessible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionform;
		
		
	}
}
