package top.okeng.exception;


public class BizSystemException extends BizException {

    public BizSystemException(int ret, String code, String msg, Object data) {
        super(ret, code, msg, data);
    }

    public BizSystemException(int ret, String code, String msg) {
        super(ret, code, msg);
    }
}
