package business;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import service.myservice.panduan.PanduanService;
import service.myservice.panduan.PanduanServiceImpl;
import service.vo.MessageVo;
import structs.action.Action;
import structs.from.ActionForm;

public class PandAction implements Action {

	@Override
	public String execute(HttpServletRequest request,ActionForm form,Map<String, String> froward) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String url = "fail";
		if(name.equals("zjf")){
			url = "success";
		}
		return froward.get(url);
	}
	
	
}
