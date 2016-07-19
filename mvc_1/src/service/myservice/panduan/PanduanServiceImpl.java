package service.myservice.panduan;

import java.util.HashMap;
import java.util.Map;

import service.vo.MessageVo;

public class PanduanServiceImpl implements PanduanService {

	@Override
	public Map<String, MessageVo> getVo(String user) {
		
		Map<String, MessageVo> map  = new HashMap<String,MessageVo>();
		boolean b = this.isuser(user);
		MessageVo vo = new MessageVo();
		if(b==true){
			//request.setAttribute("mess", "success");
			vo.setName("zjf");
			vo.setAge("22");
			vo.setMemo("success");
			map.put("/view/myjsp.jsp", vo);
		}else{
			//request.setAttribute("mess", "fail");
			vo.setName("zjf");
			vo.setAge("22");
			vo.setMemo("fail");
			map.put("/view/fail.jsp", vo);
		}
		return map;
	}
	
	private boolean isuser(String name){
		boolean b = false;
		if(name.equals("zjf")){
			b=true;
		}
		return b;
	}


}
