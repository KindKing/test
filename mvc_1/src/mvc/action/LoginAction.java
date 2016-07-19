package mvc.action;

import mvc.form.Action;
import mvc.form.ActionForm;
import mvc.form.LoginForm;
//这个类。在mvc框架中其实就是真正请求的Action，如果是一个登录的请求，会通过ActionServlet这个总的
//控制器把请求转到这个里面
public class LoginAction implements Action{

	@Override
	public String execute(ActionForm action) {
		
		LoginForm form = (LoginForm) action;
		return form.toString();
	}

}
