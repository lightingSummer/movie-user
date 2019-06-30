package club.lightingsummer.movie.userbiz.api;

import club.lightingsummer.movie.userapi.api.UserInfoAPI;
import club.lightingsummer.movie.userapi.bo.CommonResponse;
import club.lightingsummer.movie.userapi.bo.UserInfoModel;
import club.lightingsummer.movie.userapi.enums.ResponseStatus;
import club.lightingsummer.movie.userapi.po.User;
import club.lightingsummer.movie.usersal.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/30 0030
 * @description：
 */
@Component
@Service(interfaceClass = UserInfoAPI.class, loadbalance = "roundrobin")
public class UserInfoAPIImpl implements UserInfoAPI {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginAPIImpl.class);

    @Autowired
    private UserService userService;

    /**
     * @author: lightingSummer
     * @date: 2019/6/30 0030
     * @description: 查看用户名是否存在
     * @param userName
     * @return club.lightingsummer.movie.userapi.bo.CommonResponse
     */
    @Override
    public CommonResponse<Boolean> checkName(String userName) {
        CommonResponse<Boolean> commonResponse = CommonResponse.success(Boolean.class);
        try {
            User user = userService.getUserInfoByName(userName);
            boolean exitName = false;
            if (user != null) {
                exitName = true;
            }
            commonResponse.setData(exitName);
        } catch (Exception e) {
            logger.error("检查用户名失败" + e.getMessage());
            return CommonResponse.fail(Boolean.class);
        }
        return commonResponse;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/6/30 0030
     * @description: 用户信息查询
     * @param uuid
     * @return club.lightingsummer.movie.userapi.bo.CommonResponse
     */
    @Override
    public CommonResponse<UserInfoModel> getUserInfo(int uuid) {
        CommonResponse<UserInfoModel> commonResponse = CommonResponse.success(UserInfoModel.class);
        try {
            User user = userService.getUserInfoById(uuid);
            if (user == null) {
                return CommonResponse.fail(UserInfoModel.class, ResponseStatus.USER_NOT_EXIT);
            }
            UserInfoModel userInfoModel = new UserInfoModel();
            BeanUtils.copyProperties(user, userInfoModel);
            commonResponse.setData(userInfoModel);
        } catch (BeansException e) {
            logger.error("查询用户信息失败" + e.getMessage());
            return CommonResponse.fail(UserInfoModel.class, ResponseStatus.SYSTEM_ERROR);
        }
        return commonResponse;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/6/30 0030
     * @description: 更新用户信息
     * @param userInfoModel
     * @return club.lightingsummer.movie.userapi.bo.CommonResponse<club.lightingsummer.movie.userapi.bo.UserInfoModel>
     */
    @Override
    public CommonResponse<UserInfoModel> updateUserInfo(UserInfoModel userInfoModel) {
        CommonResponse<UserInfoModel> commonResponse = CommonResponse.success(UserInfoModel.class);
        try {
            User user = new User();
            BeanUtils.copyProperties(userInfoModel, user);
            if (!userService.updateUserInfo(user)) {
                return CommonResponse.fail(UserInfoModel.class, ResponseStatus.FAIL);
            }
            commonResponse.setData(userInfoModel);
        } catch (Exception e) {
            logger.error("更新用户信息失败" + e.getMessage());
            return CommonResponse.fail(UserInfoModel.class, ResponseStatus.SYSTEM_ERROR);
        }
        return commonResponse;
    }
}
