package structs.from;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Struct_config {
	
	public static void main(String[] args) {
		try {
			System.out.println(resolu(""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Map<String, Xmlbean> resolu(String xmlpath) throws Exception{
		
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new File(xmlpath));
		Element root = document.getRootElement();
//		Element actionform = root.getChild("formbeans");
//		List<Element> forms = actionform.getChildren();
//		for(Element e:forms){
//			String name = e.getAttributeValue("name");
//			String clas = e.getAttributeValue("class");
//			System.out.println("formbeans:name="+name+"||class="+clas);
//		}
//		
		Map<String, Xmlbean> cachebean = new HashMap<String,Xmlbean>();
		Element action = root.getChild("action-mapping");
		List<Element> actions = action.getChildren();
		for(Element e:actions){
			Xmlbean actionx = new Xmlbean();
			String name = e.getAttributeValue("name");
			actionx.setName(name);
			Element bean = root.getChild("formbeans");
			List<Element> beans = bean.getChildren();
			for(Element ex:beans){
				if(name.equals(ex.getAttributeValue("name"))){
					String clas = ex.getAttributeValue("class");
					actionx.setClas(clas);
					break;
				}
			}
			String type = e.getAttributeValue("type");
			actionx.setType(type);
			String path = e.getAttributeValue("path");
			actionx.setPath(path);
			List<Element> forwards = e.getChildren();
			Map<String, String> map = new HashMap<String, String>();
			for(Element el:forwards){
				String fname = el.getAttributeValue("name");
				String value = el.getAttributeValue("value");
				map.put(fname, value);
			}
			actionx.setForward(map);
			cachebean.put(path, actionx);
		}
		
		return cachebean;
	}
	
	
}
