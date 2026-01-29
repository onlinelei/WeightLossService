package top.okeng.exception;


/**
 * @author ray
 * @description 业务异常，很可能异常当中带数据
 * @date 2024/8/16 4:16 PM
 */
public abstract class BizException extends RuntimeException {
    /**
     * 业务数据
     */
    private Object data;
    /**
     * 外部编码
     */
    private int ret;
    /**
     * 内部编码
     */
    private String code;
    /**
     * 返回消息
     */
    private String msg;

    public BizException(String code, String msg, Object data) {
        super(msg);
        this.ret = -1;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public BizException(int ret, String code, String msg, Object data) {
        super(msg);
        this.data = data;
        this.ret = ret;
        this.code = code;
        this.msg = msg;
    }

    public BizException(int ret, String code, String msg) {
        super(msg);
        this.ret = ret;
        this.code = code;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
