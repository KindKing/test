package structs.from;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ActionLis implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
				ServletContext context = sce.getServletContext();
				String path = context.getInitParameter("struts-config");
				String pathroot = context.getRealPath("\\");
				System.out.println(pathroot);
				
				try {
					Map<String, Xmlbean> xmlbeans = Struct_config.resolu(pathroot+path);
					context.setAttribute("structs", xmlbeans);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("系统注册成功");
				
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("系统已经注销");
	}

}
