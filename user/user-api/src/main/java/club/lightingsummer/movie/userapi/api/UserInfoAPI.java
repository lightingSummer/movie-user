package club.lightingsummer.movie.userapi.api;

import club.lightingsummer.movie.userapi.bo.CommonResponse;
import club.lightingsummer.movie.userapi.bo.UserInfoModel;
import club.lightingsummer.movie.userapi.bo.UserModel;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/28 0028
 * @description：
 */
public interface UserInfoAPI {

    CommonResponse<Boolean> checkName(String userName);

    CommonResponse<UserInfoModel> getUserInfo(int uuid);

    CommonResponse<UserInfoModel> updateUserInfo(UserInfoModel userInfoModel);

}
