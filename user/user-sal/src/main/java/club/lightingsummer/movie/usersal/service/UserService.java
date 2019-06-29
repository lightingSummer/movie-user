package club.lightingsummer.movie.usersal.service;

import club.lightingsummer.movie.userapi.po.User;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/29 0029
 * @description：
 */
public interface UserService {

    User getUserInfoById(int id);

    User getUserInfoByName(String userName);

    boolean addUser(User user);
}
