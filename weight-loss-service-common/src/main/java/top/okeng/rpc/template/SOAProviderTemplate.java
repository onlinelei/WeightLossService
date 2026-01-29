package top.okeng.rpc.template;

import top.okeng.exception.BaseError;
import top.okeng.exception.BizException;
import top.okeng.rpc.response.RPCBaseResult;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * @author ray
 * @description
 * @date 2024/8/16 4:16 PM
 */
public class SOAProviderTemplate extends AbstractProviderTemplate {

    /**
     * 执行模板 有返回值
     *
     * @param function     执行函数
     * @param errorHandler 错误处理
     * @param <T>          执行返回对象
     * @return RPCBaseResult 执行返回包装
     */
    public <T> RPCBaseResult<T> execute(Supplier<T> function, Function<Throwable, RPCBaseResult<T>> errorHandler) {
        RPCBaseResult<T> result;
        try {
            result = SOAProviderTemplate.getSuccess(function.get());
        } catch (Throwable throwable) {
            result = errorHandler.apply(throwable);
        }
        return msgHandle(result);
    }

    /**
     * 执行模板 无返回值
     *
     * @param function     执行函数
     * @param errorHandler 错误处理
     * @param <T>          执行返回对象
     * @return RPCBaseResult 执行返回包装
     */

    public <T> RPCBaseResult<T> executeWithoutResult(Supplier<T> function, Function<Throwable, RPCBaseResult<T>> errorHandler) {
        RPCBaseResult<T> result;
        try {
            function.get();
            result = SOAProviderTemplate.getSuccess(null);
        } catch (Throwable throwable) {
            result = errorHandler.apply(throwable);
        }
        return msgHandle(result);
    }


    /**
     * 默认成功处理
     *
     * @param data 包装数据
     * @param <T>  包装类型
     * @return 包装成功结果
     */
    public static <T> RPCBaseResult<T> getSuccess(T data) {
        return new RPCBaseResult<T>(BaseError.SUCCESS.getRet(), BaseError.SUCCESS.getMsg(), data);
    }

    /**
     * 默认失败处理
     *
     * @param throwable 异常
     * @param <T>       包装类型
     * @return 失败结果
     */
    public static <T> RPCBaseResult<T> getFail(Throwable throwable) {
        return getFail(throwable, null);
    }

    /**
     * 默认失败处理
     *
     * @param throwable 异常
     * @param data      包装数据
     * @param <T>       包装类型
     * @return 失败结果
     */
    public static <T> RPCBaseResult<T> getFail(Throwable throwable, T data) {
        //业务异常处理
        if (throwable instanceof BizException) {
            final BizException bizException = (BizException) throwable;
            return new RPCBaseResult<T>(formatRet(bizException.getCode()), bizException.getMsg(), data);
        }
        //非法参数处理
        if (throwable instanceof IllegalArgumentException) {
            return new RPCBaseResult<T>(formatRet(0, BaseError.ILLEGAL_PARAMETER.getCode()), throwable.getMessage(), data);
        }
        //非法状态处理
        if (throwable instanceof IllegalStateException) {
            return new RPCBaseResult<T>(formatRet(0, BaseError.ILLEGAL_STATE.getCode()), throwable.getMessage(), data);
        }
        return new RPCBaseResult<T>(formatRet(0, BaseError.SERVER_ERR.getCode()), throwable.getMessage(), data);
    }
}
