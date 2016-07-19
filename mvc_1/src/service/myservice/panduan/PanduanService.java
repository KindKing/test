package service.myservice.panduan;

import java.util.Map;

import service.vo.MessageVo;

public interface PanduanService {
	
	public Map<String, MessageVo> getVo(String user);
}
