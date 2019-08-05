# dubbo-movie-user
Movie project user模块
用户模块<br>

## 用到的技术及实现主要功能<br>
* 使用dubbo提供cinema信息各种查询服务，使用zookeeper做注册中心，用于服务注册及调用<br>
* 使用springboot作为后端主要框架，aop配置日志<br>
* 使用mysql5.7作为数据库存储，mybatis做查询，mybatis-generator生成xml映射，pagehelper做分页<br>
* Redis做缓存，存储sessionID,用户web前后端交互

## 待优化
* 用户信息等热点接口全部使用redis缓存
* 学习一些安全框架比如shiro

```java
public interface UserInfoAPI {
    // 检查用户名是否存在
    CommonResponse<Boolean> checkName(String userName);
    // 获取用户信息
    CommonResponse<UserInfoModel> getUserInfo(int uuid);
    // 更新用户信息
    CommonResponse<UserInfoModel> updateUserInfo(UserInfoModel userInfoModel);
    // 根据ticket获取用户id
    CommonResponse<Integer> getUserIdByTicket(String ticket);
}
public interface UserLoginAPI {
    // 登录
    CommonResponse login(String userName, String password);
    // 注册
    CommonResponse register(UserModel userModel);
}
```
