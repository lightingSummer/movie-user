package club.lightingsummer.movie.userapi.bo;

import club.lightingsummer.movie.userapi.enums.ResponseStatus;
import lombok.Data;

/**
 * @author     ：lightingSummer
 * @date       ：2019/6/28 0028
 * @description：
 */
@Data
public class CommonResponse<T> {

    private int status;
    private String msg;
    private T data;

    private CommonResponse(){}

    public static<T> CommonResponse success(){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(ResponseStatus.SUCCESS.getCode());

        return commonResponse;
    }

    public static<T> CommonResponse success(String msg){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(0);
        commonResponse.setMsg(msg);

        return commonResponse;
    }

    public static<T> CommonResponse fail(String msg){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(1);
        commonResponse.setMsg(msg);

        return commonResponse;
    }

    public static<T> CommonResponse fail(ResponseStatus status){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(status.getCode());
        commonResponse.setMsg(status.getMsg());

        return commonResponse;
    }

}
