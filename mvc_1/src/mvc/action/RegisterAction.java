package mvc.action;

import mvc.form.Action;
import mvc.form.ActionForm;
import mvc.form.RegisterForm;

public class RegisterAction implements Action{

	@Override
	public String execute(ActionForm action) {
		
		RegisterForm from = (RegisterForm) action;
		return from.toString();
	}

	
}
