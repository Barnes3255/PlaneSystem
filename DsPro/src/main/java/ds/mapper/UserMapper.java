package ds.mapper;

import ds.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> getUserList(Map map);
    int addUser(User u);
    int updateUser(Map map);
    int deleteUser(int id);
}
