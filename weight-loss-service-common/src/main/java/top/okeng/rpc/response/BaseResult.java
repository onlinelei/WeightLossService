package top.okeng.rpc.response;


/**
 * @author ray
 * @description 结果
 * @date 2024/8/16 4:16 PM
 */
public class BaseResult<T> extends BaseDTO {

    private T data;
    private Integer code;
    private String message;

    public BaseResult() {
    }

    public BaseResult(T data) {
        this.code = 0;
        this.data = data;
        this.message = "ok";
    }

    public BaseResult(Integer code, String message, T data) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "data=" + data +
                ", ret=" + code +
                ", msg='" + message + '\'' +
                '}';
    }
}