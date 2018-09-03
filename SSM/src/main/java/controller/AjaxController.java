package controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.UserService;

@Controller
@RequestMapping("/AjaxController")
@Scope("prototype")
public class AjaxController {
	 //自动注入业务层的userService类
    @Autowired
    	UserService userService;
	@RequestMapping("/ajax")
		public void test(User user,HttpServletResponse resp) throws IOException {
			System.out.println("已经成功进入AjaxController");
			String username = user.getUsername();
			List<User> UserList=userService.selectByLikeName(username);
	        //把list实体封装到json对象中
	        JSONArray jArray=JSONArray.fromObject(UserList);  
	        //向客户端输出json对象  
	        System.out.println("jArray.toString():"+jArray.toString());
	        resp.getWriter().write(jArray.toString());  
		}	
}
