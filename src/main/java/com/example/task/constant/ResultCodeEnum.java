package com.example.task.constant;

/**
 * 不同业务异常及其对应的状态码
 */
public enum ResultCodeEnum {

    /**
     * 参数校验失败
     */
    PARAM_VALIDATE_FAILED(10001, "参数校验失败"),

    /**
     * 用户名已存在（注册接口用）
     */
    USERNAME_ALREADY_EXIST(10002, "用户名已存在，请勿重复插入"),

    /**
     * 用户名或密码错误（登录接口用）
     */
    WRONG_USERNAME_OR_PASSWORD(10003, "用户名或密码错误"),

    /**
     * 重复操作
     */
    REPEAT_OPERATION(10004, "重复操作"),

    /**
     * 手机号已注册
     */
    PHONE_ALREADY_EXIST(10005,"手机号已注册"),

    /**
     * 用户提供的原密码错误
     */
    WRONG_ORIGIN_PASSWORD(10006,"原密码错误"),

    /**
     * 部门不存在
     **/
    WRONG_DEP(10010,"部门不存在"),

    /**
     * 部门已存在
     */
    DEPNAME_ALREADY_EXIST(10011,"部门已存在，请勿重复插入"),

    /**
     * 部门下存在子部门
     */
    HAS_SON_DEP(10012, "存在子部门，请勿删除"),

    /**
     * 个人所得税计算出错
     */
    WRONG_MONTH(10020,"个人所得税无法计算，请检查输入"),

    /**
     * 重复插入薪酬记录
     **/
    SALARYRECORD_ALREADY_EXIST(10021, "该条薪酬记录已存在，请勿重复插入"),

    /**
     * 重复使用相同的公告标题
     **/
    TITLE_ALREADY_EXIST(10022,"该公告标题已存在，请勿重复使用");

    private final int code;
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
