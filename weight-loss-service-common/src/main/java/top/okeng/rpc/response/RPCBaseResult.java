package top.okeng.rpc.response;


import java.io.Serializable;

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
