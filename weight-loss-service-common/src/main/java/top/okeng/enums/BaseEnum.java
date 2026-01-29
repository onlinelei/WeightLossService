package top.okeng.enums;

/**
 * @author ray
 * @description
 * @since 2026/1/29
 */

public interface BaseEnum {
    /**
     * 获取枚举描述信息
     *
     * @return 返回枚举描述
     */
    String getMsg();

    /**
     * 获取枚举值
     *
     * @return 返回枚举值
     */
    String getCode();
}
