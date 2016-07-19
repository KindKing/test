package structs.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import structs.from.ActionForm;

public interface Action {
	
	public String execute(HttpServletRequest request,ActionForm from,Map<String, String> forward);
}
