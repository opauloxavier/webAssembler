package classesAction;

import com.opensymphony.xwork2.ActionSupport;

public class WelcomeAction extends ActionSupport {

public String start() {
	
	System.out.println("OK");
	return "SUCCESS";
}
	
}
