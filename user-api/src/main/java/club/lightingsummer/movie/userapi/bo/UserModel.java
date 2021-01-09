package club.lightingsummer.movie.userapi.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/28 0028
 * @description：
 */
@Data
public class UserModel implements Serializable {
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String address;
}
