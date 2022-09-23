import ds.mapper.UserMapper;
import ds.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class UserMapperImpl implements UserMapper {
    private SqlSession sqlSession;
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    @Override
    public List<User> getUserList(Map map) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getUserList(map);
    }

    @Override
    public int addUser(User u) {
        return 0;
    }

    @Override
    public int updateUser(Map map) {
        return 0;
    }

    @Override
    public int deleteUser(int id) {
        return 0;
    }
}