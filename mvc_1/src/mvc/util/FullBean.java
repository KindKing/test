package mvc.util;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import mvc.form.ActionForm;

public class FullBean {
	
	public FullBean(){
		
	}
	
	public static ActionForm full(HttpServletRequest request){
		ActionForm o = null;
		try {
			Class clazz= Class.forName(request.getParameter("sign"));//通过类的权限名得到类的class
			o =  (ActionForm) clazz.newInstance();//通过类的class实例化当前类
			Field[] fields = clazz.getDeclaredFields();//通过类的class得到类的属性
			for(Field f:fields){//循环属性
				f.setAccessible(true);
				f.set(o, request.getParameter(f.getName()));//按属性的值赋值给o
				f.setAccessible(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
}
