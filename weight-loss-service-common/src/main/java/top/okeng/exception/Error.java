package top.okeng.exception;



public interface Error {

    /**
     * ret
     *
     * @return ret
     */
    Integer getRet();

    /**
     * code
     *
     * @return code
     */
    String getCode();

    /**
     * msg
     *
     * @return msg
     */
    String getMsg();

    /**
     * 异常转换
     *
     * @return BizException
     */
    BizException getException();

    /**
     * 异常转换
     *
     * @param msg msg
     * @return BizException
     */
    BizException getException(String msg);

    /**
     * 异常转换
     *
     * @param data data
     * @return BizException
     */
    BizException getException(Object data);
}
