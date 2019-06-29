package club.lightingsummer.movie.userbiz.api;

import club.lightingsummer.movie.userapi.api.UserLoginAPI;
import club.lightingsummer.movie.userapi.bo.CommonResponse;
import club.lightingsummer.movie.userapi.bo.UserInfoModel;
import club.lightingsummer.movie.userapi.enums.ResponseStatus;
import club.lightingsummer.movie.userapi.po.User;
import club.lightingsummer.movie.userbiz.utils.MD5Util;
import club.lightingsummer.movie.usersal.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/29 0029
 * @description：
 */
@Component
@Service(interfaceClass = UserLoginAPI.class,loadbalance = "roundrobin")
public class UserLoginAPIImpl implements UserLoginAPI {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginAPIImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public CommonResponse login(String Name, String password) {
        return null;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/6/29 0029
     * @description: 用户注册接口
     * @param userInfoModel
     * @return club.lightingsummer.movie.userapi.bo.CommonResponse
     */
    @Override
    public CommonResponse register(UserInfoModel userInfoModel) {
        CommonResponse commonResponse = CommonResponse.success();
        if (userInfoModel.getUserName() == null || userInfoModel.getUserPwd() == null
                || StringUtils.isBlank(userInfoModel.getUserName())
                || StringUtils.isBlank(userInfoModel.getUserPwd())) {
            return CommonResponse.fail(ResponseStatus.PARAM_LACK);
        }
        User exitUser = userService.getUserInfoByName(userInfoModel.getUserName());
        if (exitUser != null) {
            return CommonResponse.fail("用户已存在");
        }
        try {
            User user = new User();
            BeanUtils.copyProperties(userInfoModel, user);
            String salt = UUID.randomUUID().toString().substring(0, 5);
            user.setSalt(salt);
            user.setUserPwd(MD5Util.MD5(user.getUserPwd()+salt));
            if(userService.addUser(user)){
                return commonResponse;
            }
        } catch (Exception e) {
            logger.error("注册失败" + e);
            return CommonResponse.fail("系统异常");
        }
        return CommonResponse.fail("注册失败");
    }
}
