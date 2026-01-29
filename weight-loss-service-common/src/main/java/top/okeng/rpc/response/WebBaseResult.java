package top.okeng.rpc.response;


import java.io.Serializable;

/**
 * @author ray
 * @description 通用的返回结果
 * @date 2024/8/16 4:16 PM
 */
public class WebBaseResult<T> extends BaseResult<T> implements Serializable {

    public WebBaseResult() {
    }

    public WebBaseResult(int ret, String msg, T data) {
        super(ret, msg, data);
    }
}