package top.okeng.exception;

/**
 * @author ray
 * @description <TODO description class purpose>
 * @date 2024/8/16 4:16 PM
 */
public class BizSystemException extends BizException {

    public BizSystemException(int ret, String code, String msg, Object data) {
        super(ret, code, msg, data);
    }

    public BizSystemException(int ret, String code, String msg) {
        super(ret, code, msg);
    }
}
