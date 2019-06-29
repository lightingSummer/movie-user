package club.lightingsummer.movie.userapi.api;

import club.lightingsummer.movie.userapi.bo.CommonResponse;
import club.lightingsummer.movie.userapi.bo.UserInfoModel;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/28 0028
 * @description：
 */
public interface UserInfoAPI {

    CommonResponse checkUsername(String username);

    CommonResponse getUserInfo(int uuid);

    CommonResponse updateUserInfo(UserInfoModel userInfoModel);

}
