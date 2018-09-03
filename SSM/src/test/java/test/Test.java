package test;

import dao.IUserDao;


import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {
   private static ApplicationContext ac;
   
    static {
        ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
    }

    public static void main(String[] args) {
        IUserDao mapper = (IUserDao) ac.getBean("IUserDao");
        System.out.println("获取alvin");
        User user = mapper.selectByName("alvin");
        System.out.println(user.getId()+":"+"username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());

    }
}