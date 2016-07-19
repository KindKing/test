package mvc.form;
//这个类承当model的角色，是页面属性跟后台的一对一映射
public class LoginForm extends ActionForm{
	
	private String name;
	private String password;
	
	public LoginForm(){
		
	}
	
	public LoginForm(String name,String password){
		this.name=name;
		this.password=password;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean equals(){
		return false;
	}
	
	public String toString(){
		return "name="+this.name+"||"+"password="+this.password;
	}
	
	
}
