package structs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.PandAction;
import mvc.action.LoginAction;
import mvc.action.RegisterAction;
import mvc.form.LoginForm;
import mvc.form.RegisterForm;
import mvc.mapping.ActionMapping;
import mvc.util.FullBean;
import service.vo.MessageVo;
import structs.from.ActionForm;
import structs.from.FullForm;
import structs.from.Xmlbean;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
     * 这个ActionServlet就相当于控制分发器的作用，他分析前台请求数据，来确定他要请求的为哪一个
     * Action，并且分析，返回到哪一个页面
     * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path = this.getPath(request.getServletPath());
		Map<String, Xmlbean> beans = (Map<String, Xmlbean>)this.getServletContext().getAttribute("structs");
		Xmlbean xmlbean = beans.get(path);
		String actionType = xmlbean.getType();
		String clas = xmlbean.getClas();
		ActionForm form = FullForm.full(clas, request);
		Action action = null;
		String url = "";
		try{
			Class claz = Class.forName(actionType);
			action = (Action)claz.newInstance();
			url = action.execute(request, form, xmlbean.getForward());
		}catch(Exception e){
			e.printStackTrace();
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
		
//		Action action = new PandAction();
//		String url = action.execute(request);
//		
//		//转发器对象只要一个字符串对象进行转发，如果你给的地址错误，他就给转发到错误的页面
//		RequestDispatcher dis = request.getRequestDispatcher(url);
//		dis.forward(request, response);
//		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	private String getPath(String servletpath){
		return servletpath.split("\\.")[0];
	}
	


}
