package ds.service;

import ds.mapper.UserMapper;
import ds.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getUserList(Map map) {
        return userMapper.getUserList(map);
    }

    @Override
    public int addUser(User u) {
        return userMapper.addUser(u);
    }

    @Override
    public int updateUser(Map map) {
        return userMapper.updateUser(map);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }
}
