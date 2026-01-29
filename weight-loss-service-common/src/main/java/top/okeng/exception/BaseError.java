package top.okeng.exception;

import java.util.Objects;

/**
 * @author ray
 * @description <TODO description class purpose>
 * @date 2024/8/16 4:16 PM
 */
public enum BaseError implements Error {
    /*** 系统异常 ***/
    SUCCESS(1, "1", "svc.success"),
    ILLEGAL_PARAMETER(100, "100", "svc.illegal_parameter"),
    ILLEGAL_STATE(200, "200", "svc.illegal_state"),
    SERVER_ERR(500, "500", "svc.sever_logic_err"),


    /*** 业务异常 ***/
    USERNAME_EXISTS(1002, "1002", "用户名已存在"),
    USER_NOT_EXISTS(1003, "1003", "用户不存在"),
    PASSWORD_ERROR(1004, "1004", "密码错误"),
    PASSWORD_NOT_MATCH(1005, "1005", "两次密码不一致"),
    TOKEN_INVALID(2001, "2001", "Token 无效"),
    TOKEN_EXPIRED(2002, "2002", "Token 过期"),
    RECORD_NOT_FOUND(3001, "3001", "记录不存在"),
    INTERNAL_ERROR(5000, "5000", "服务器内部错误"),


    ;

    private Integer ret;
    private String code;
    private String msg;

    BaseError(Integer ret, String code, String msg) {
        this.ret = ret;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getRet() {
        return ret;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public BizException getException() {
        return new BizSystemException(this.getRet(), this.getCode(), this.getMsg());
    }

    @Override
    public BizException getException(String msg) {
        return new BizSystemException(this.getRet(), this.getCode(), msg);
    }

    @Override
    public BizException getException(Object data) {
        return new BizSystemException(this.getRet(), this.getCode(), this.getMsg(), Objects.toString(data, ""));
    }
}
