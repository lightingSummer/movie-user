package club.lightingsummer.movie.usersal.service.impl;

import club.lightingsummer.movie.userapi.po.User;
import club.lightingsummer.movie.userdal.dao.UserMapper;
import club.lightingsummer.movie.usersal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/29 0029
 * @description：
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfoById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserInfoByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insertSelective(user) == 1;
    }
}
