package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Struct_config {
	
	public static void main(String[] args) {
		try {
			new Struct_config().resolu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void resolu() throws Exception{
		
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new File("WebContent/WEB-INF/struts-config.xml"));
		Element root = document.getRootElement();
		Element actionform = root.getChild("formbeans");
		List<Element> forms = actionform.getChildren();
		for(Element e:forms){
			String name = e.getAttributeValue("name");
			String clas = e.getAttributeValue("class");
			System.out.println("formbeans:name="+name+"||class="+clas);
		}
		
		
		Element action = root.getChild("action-mapping");
		List<Element> actions = action.getChildren();
		for(Element e:actions){
			String name = e.getAttributeValue("name");
			String type = e.getAttributeValue("type");
			String path = e.getAttributeValue("path");
			List<Element> forwards = e.getChildren();
			for(Element el:forwards){
				String fname = el.getAttributeValue("name");
				String value = el.getAttributeValue("value");
				System.out.println("forward:fname="+fname+"||value="+value);
				
			}
		}
		
		
	}
	
	
}
