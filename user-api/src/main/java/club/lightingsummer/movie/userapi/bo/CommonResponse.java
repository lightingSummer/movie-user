package club.lightingsummer.movie.userapi.bo;

import club.lightingsummer.movie.userapi.enums.ResponseStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/28 0028
 * @description：
 */
@Data
public class CommonResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private CommonResponse(){}

    public static<T> CommonResponse success(){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(ResponseStatus.SUCCESS.getCode());

        return commonResponse;
    }

    public static<T> CommonResponse<T> success(Class<?> T){
        CommonResponse<T> commonResponse = new CommonResponse<T>();
        commonResponse.setStatus(0);

        return commonResponse;
    }

    public static<T> CommonResponse success(String msg){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(0);
        commonResponse.setMsg(msg);

        return commonResponse;
    }

    public static<T> CommonResponse<T> fail(Class<?> T){
        CommonResponse<T> commonResponse = new CommonResponse<T>();
        commonResponse.setStatus(ResponseStatus.FAIL.getCode());
        commonResponse.setMsg(ResponseStatus.FAIL.getMsg());

        return commonResponse;
    }

    public static<T> CommonResponse<T> fail(Class<?> T,ResponseStatus status){
        CommonResponse<T> commonResponse = new CommonResponse<T>();
        commonResponse.setStatus(status.getCode());
        commonResponse.setMsg(status.getMsg());

        return commonResponse;
    }

    public static<T> CommonResponse fail(String msg){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(ResponseStatus.FAIL.getCode());
        commonResponse.setMsg(ResponseStatus.FAIL.getMsg());

        return commonResponse;
    }

    public static<T> CommonResponse fail(ResponseStatus status){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(status.getCode());
        commonResponse.setMsg(status.getMsg());

        return commonResponse;
    }

}
