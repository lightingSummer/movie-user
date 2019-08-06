package club.lightingsummer.movie.userbiz.api;

import club.lightingsummer.movie.userapi.api.UserLoginAPI;
import club.lightingsummer.movie.userapi.bo.CommonResponse;
import club.lightingsummer.movie.userapi.bo.UserModel;
import club.lightingsummer.movie.userapi.enums.ResponseStatus;
import club.lightingsummer.movie.userapi.po.User;
import club.lightingsummer.movie.userbiz.utils.JedisAdapter;
import club.lightingsummer.movie.userbiz.utils.MD5Util;
import club.lightingsummer.movie.usersal.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.UUID;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/29 0029
 * @description：
 */
@Component
@Service(interfaceClass = UserLoginAPI.class, loadbalance = "roundrobin")
public class UserLoginAPIImpl implements UserLoginAPI {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginAPIImpl.class);

    @Autowired
    private UserService userService;
    @Autowired
    private JedisAdapter jedisAdapter;

    @Override
    public CommonResponse login(String userName, String password) {
        CommonResponse<String> commonResponse = CommonResponse.success(String.class);
        if (userName == null || password == null
                || StringUtils.isBlank(userName)
                || StringUtils.isBlank(password)) {
            return CommonResponse.fail(ResponseStatus.PARAM_LACK);
        }
        User user = userService.getUserInfoByName(userName);
        if (user == null) {
            return CommonResponse.fail(ResponseStatus.USER_NOT_EXIT);
        }
        if (!user.getUserPwd().equals(MD5Util.MD5(password + user.getSalt()))) {
            return CommonResponse.fail(ResponseStatus.USER_PASSWORD_WRONG);
        }
        String ticket = UUID.randomUUID().toString().replaceAll("-", "");
        jedisAdapter.set(ticket, user.getUuid() + "");
        jedisAdapter.expire(ticket);
        commonResponse.setData(ticket);
        return commonResponse;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/6/29 0029
     * @description: 用户注册接口
     * @param userModel
     * @return club.lightingsummer.movie.userapi.bo.CommonResponse
     */
    @Override
    @Transactional
    public CommonResponse register(UserModel userModel) {
        CommonResponse<String> commonResponse = CommonResponse.success(String.class);
        if (userModel.getUsername() == null || userModel.getPassword() == null
                || StringUtils.isBlank(userModel.getUsername())
                || StringUtils.isBlank(userModel.getPassword())) {
            return CommonResponse.fail(ResponseStatus.PARAM_LACK);
        }
        User exitUser = userService.getUserInfoByName(userModel.getUsername());
        if (exitUser != null) {
            return CommonResponse.fail("用户已存在");
        }
        try {
            User user = new User();
            user.setUserName(userModel.getUsername());
            user.setEmail(userModel.getEmail());
            user.setUserPhone(userModel.getMobile());
            user.setUserPwd(userModel.getPassword());
            String salt = UUID.randomUUID().toString().substring(0, 5);
            user.setSalt(salt);
            user.setUserPwd(MD5Util.MD5(user.getUserPwd() + salt));
            int uuid = userService.addUser(user);
            if (uuid != 0) {
                String ticket = UUID.randomUUID().toString().replaceAll("-", "");
                jedisAdapter.set(ticket, uuid + "");
                jedisAdapter.expire(ticket);
                commonResponse.setData(ticket);
                return commonResponse;
            }
        } catch (Exception e) {
            logger.error("注册失败" + e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return CommonResponse.fail("系统异常");
        }
        return CommonResponse.fail("注册失败");
    }
}
