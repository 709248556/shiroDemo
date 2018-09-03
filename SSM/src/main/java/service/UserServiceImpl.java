package service;

import dao.IUserDao;
import model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Created by alvin on 15/9/7.
 */
//@Service("UserService") 注解用于标示此类为业务层组件,在使用时会被注解的类会自动由
    //spring进行注入,无需我们创建实例
@Service
public class UserServiceImpl implements UserService {
    //自动注入iuserdao 用于访问数据库
    @Autowired
    IUserDao Mapper;

    //登录方法的实现,从jsp页面获取username与password
    public boolean login(String username, String password) {
//        System.out.println("输入的账号:" + username + "输入的密码:" + password);
        //对输入账号进行查询,取出数据库中保存对信息
        User user = Mapper.selectByName(username);
        if (user != null) {
//            System.out.println("查询出来的账号:" + user.getUsername() + "密码:" + user.getPassword());
//            System.out.println("---------");
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;

        }
        return false;

    }
    
	@Override
	public List selectByLikeName(String name) {
		System.out.println("已经进入selectByLikeName方法");
		List<User> userlist = (List) Mapper.selectByLikeName(name);
    	System.out.println("userlist:"+userlist);
    	System.out.println("userlist.get(0):"+userlist.get(0));
    	System.out.println("userlist.size():"+userlist.size());
    	User user = userlist.get(0);
    	System.out.println("user.user.getUsername():"+user.getUsername());
		return userlist;
	}
}