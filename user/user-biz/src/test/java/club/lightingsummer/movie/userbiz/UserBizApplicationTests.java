package club.lightingsummer.movie.userbiz;

import club.lightingsummer.movie.userapi.api.UserLoginAPI;
import club.lightingsummer.movie.userapi.bo.CommonResponse;
import club.lightingsummer.movie.userapi.bo.UserInfoModel;
import club.lightingsummer.movie.userapi.po.User;
import club.lightingsummer.movie.userbiz.api.UserLoginAPIImpl;
import club.lightingsummer.movie.userdal.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBizApplicationTests {

    @Autowired private UserMapper userMapper;
    @Autowired private UserLoginAPI userLoginAPI;

    @Test
    public void contextLoads() {
    }

    @Test
    public void loginTest(){

        /*
        User userInfoModel = new User();
        userInfoModel.setUserPwd("123");
        userInfoModel.setUserPhone("123");
        userInfoModel.setUserName("夏之");
        userInfoModel.setEmail("11@qq.com");
        userInfoModel.setAddress("111");
        userInfoModel.setSalt("asddf");
        userMapper.insertSelective(userInfoModel);
        */

        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setUserPwd("123");
        userInfoModel.setUserPhone("123");
        userInfoModel.setUserName("夏之");
        userInfoModel.setEmail("11@qq.com");
        userInfoModel.setAddress("111");
        CommonResponse commonResponse = userLoginAPI.register(userInfoModel);
        System.out.println(commonResponse.getStatus()+commonResponse.getMsg());


    }

}
