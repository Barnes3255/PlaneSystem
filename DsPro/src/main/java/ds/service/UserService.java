package ds.service;

import ds.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    List<User> getUserList(Map map);
    int addUser(User u);
    int updateUser(Map map);
    int deleteUser(int id);
}
