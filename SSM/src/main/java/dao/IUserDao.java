package dao;

import java.util.List;

import model.User;

/**
 * Created by alvin on 15/9/7.
 * ����Ϊ�ӿ�ģʽ�µ�����
 */

public interface IUserDao {
    //�����Խӿ���ʽ���������ݿ��������,����ֻ��
    // ��Mybatisӳ���ļ��ж������ӳ��Ϳ���ֱ��ʹ��
    public User selectById(int id);
    public User selectByName(String username);
    public List<User> selectByLikeName(String name);
}