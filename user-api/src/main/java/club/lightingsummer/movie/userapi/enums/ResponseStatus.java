package club.lightingsummer.movie.userapi.enums;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/28 0028
 * @description：
 */
public enum ResponseStatus {

    SUCCESS(0, "成功"),
    FAIL(1,"失败"),
    PARAM_LACK(200,"参数缺少"),
    USER_NOT_EXIT(201,"用户不存在"),
    USER_PASSWORD_WRONG(202,"用户密码错误"),
    SYSTEM_ERROR(203,"系统异常");

    private int code;
    private String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
