package mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.action.LoginAction;
import mvc.action.RegisterAction;
import mvc.form.Action;
import mvc.form.ActionForm;
import mvc.form.LoginForm;
import mvc.form.RegisterForm;
import mvc.mapping.ActionMapping;
import mvc.util.FullBean;

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
		PrintWriter out = response.getWriter();
//		System.out.println("sign="+request.getParameter("sign"));
		ActionForm form =  (ActionForm)FullBean.full(request);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//System.out.println(form.toString());
		Action action = null;//action总的接口，这里应用的接口的好处，通过一个定义，来实现解耦
		Map<String, String> map = ActionMapping.getMap();
		String actionname = map.get(request.getParameter("sign"));
		try {
			Class clazz = Class.forName(actionname);
			Object o = clazz.newInstance();
			action = (Action) o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//下面的一句话完全诠释了接口的好处，这个action不必知道到底是哪一个action，
		//我只需要运行期间（请求发来），知道是哪一个action就行了。这样我就可以用这一句代替了
		//我可能出现的下面N局代码
		//loginAction.execute(form)
		//registerAction.execute(form)
		//从而实现了解耦。
		//假如我后面维护，这个地方我需要把execute方法换成update方法，如果我不解耦，我可能会需要修改N多的代码
		//而现在，我只需要更改这一句代码就Ok了。
		//不但写的时候省事。修改代码或者维护更加省事。
		//这就是接口解耦
		String message = action.execute(form);
		out.print(message);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
