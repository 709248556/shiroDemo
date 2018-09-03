package service;

import java.util.List;

public interface UserService {
	public boolean login(String username,String password);
	public List selectByLikeName(String name);
}
