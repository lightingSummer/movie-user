package club.lightingsummer.movie.userbiz;

import club.lightingsummer.movie.userapi.api.UserInfoAPI;
import club.lightingsummer.movie.userapi.api.UserLoginAPI;
import club.lightingsummer.movie.userapi.bo.CommonResponse;
import club.lightingsummer.movie.userapi.bo.UserModel;
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
    @Autowired private UserInfoAPI userInfoAPI;

    @Test
    public void contextLoads() {
    }

    @Test
    public void registerTest(){

        /*
        User userModel = new User();
        userModel.setUserPwd("123");
        userModel.setUserPhone("123");
        userModel.setUserName("夏之");
        userModel.setEmail("11@qq.com");
        userModel.setAddress("111");
        userModel.setSalt("asddf");
        userMapper.insertSelective(userModel);
        */

        UserModel userModel = new UserModel();
        userModel.setUserPwd("123");
        userModel.setUserPhone("123");
        userModel.setUserName("夏之");
        userModel.setEmail("11@qq.com");
        userModel.setAddress("111");
        CommonResponse commonResponse = userLoginAPI.register(userModel);
        System.out.println(commonResponse.getStatus()+commonResponse.getMsg());


    }


    @Test
    public void loginTest(){
        CommonResponse commonResponse = userLoginAPI.login("夏之","123");
        System.out.println(commonResponse);
    }

    @Test
    public void userInfoTest(){
        CommonResponse commonResponse = userInfoAPI.getUserInfo(2);
        System.out.println(commonResponse);
    }

}
