package top.okeng.rpc.template;


import top.okeng.exception.BaseError;
import top.okeng.exception.BizException;
import top.okeng.rpc.response.WebBaseResult;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author ray
 * @description
 * @date 2024/8/16 4:16 PM
 */
public class WebProviderTemplate extends AbstractProviderTemplate {

    /**
     * 执行模板 有返回值
     *
     * @param function     执行函数
     * @param errorHandler 错误处理
     * @param <T>          执行返回对象
     * @return WebBaseResult 执行返回包装
     */
    public static <T> WebBaseResult<T> execute(Supplier<T> function, Function<Throwable, WebBaseResult<T>> errorHandler) {
        try {
            return WebProviderTemplate.getSuccess(function.get());
        } catch (Throwable throwable) {
            return errorHandler.apply(throwable);
        }
    }

    /**
     * 执行模板 无返回值
     *
     * @param function     执行函数
     * @param errorHandler 错误处理
     * @param <T>          执行返回对象
     * @return WebBaseResult 执行返回包装
     */
    public static <T> WebBaseResult<T> executeWithoutResult(Supplier<T> function, Function<Throwable, WebBaseResult<T>> errorHandler) {
        try {
            function.get();
            return WebProviderTemplate.getSuccess(null);
        } catch (Throwable throwable) {
            return errorHandler.apply(throwable);
        }
    }

    public static <T> WebBaseResult<T> getSuccess(T data) {
        return new WebBaseResult<T>(BaseError.SUCCESS.getRet(), BaseError.SUCCESS.getMsg(), data);
    }

    public static <T> WebBaseResult<T> getFail(Throwable throwable) {
        return getFail(throwable, null);
    }

    public static <T> WebBaseResult<T> getFail(Throwable throwable, T data) {
        if (throwable instanceof BizException) {
            final BizException bizException = (BizException) throwable;
            return new WebBaseResult<T>(formatRet(bizException.getCode()), bizException.getMsg(), data);
        }
        return new WebBaseResult<T>(formatRet(BaseError.SERVER_ERR.getCode()), throwable.getMessage(), data);
    }
}
