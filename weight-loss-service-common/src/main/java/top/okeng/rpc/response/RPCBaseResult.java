package top.okeng.rpc.response;


import java.io.Serializable;

/**
 * @author ray
 * @description RPC接口通用的返回结果
 * @date 2024/8/16 4:16 PM
 */
public class RPCBaseResult<T> extends BaseResult<T> implements Serializable {

    public RPCBaseResult() {
    }

    public RPCBaseResult(BaseResult<T> baseResult) {
        super(baseResult.getCode(), baseResult.getMessage(), baseResult.getData());
    }

    public RPCBaseResult(int ret, String msg, T data) {
        super(ret, msg, data);
    }

}
