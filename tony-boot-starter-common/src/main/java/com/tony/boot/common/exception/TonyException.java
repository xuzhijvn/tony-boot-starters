/*
 *       Copyright© (2020).
 */
package com.tony.boot.common.exception;

/**
 * @author tony
 * @create 2021/9/19
 * @description:
 */
public class TonyException extends RuntimeException {

    private static final long serialVersionUID = -4379801359412979859L;

    public static final String SVC = "svc.";

    public static final String ERROR = "-error:";

    protected Integer ret;

    protected String code;

    protected String msg;

    protected String solution;

    protected String clazz;

    protected Object[] params;

    public TonyException() {
    }

    public TonyException(Throwable throwable) {
        super(throwable);
    }

    public TonyException(Integer ret, Throwable throwable) {
        super(throwable);
    }

    public TonyException(String msg, Throwable throwable) {
        super(msg, throwable);
        this.msg = msg;
    }

    public TonyException(Integer ret, String msg) {
        super(msg);
        this.ret = ret;
        this.msg = msg;
    }

    public TonyException(Integer ret, String code, String msg) {
        super(msg);
        this.ret = ret;
        this.code = code;
        this.msg = msg;
    }

    public TonyException(Integer ret, String code, String msg, Throwable throwable) {
        super(msg, throwable);
        this.ret = ret;
        this.code = code;
        this.msg = msg;
    }

    public TonyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public TonyException(Integer ret) {
        this.ret = ret;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * 对服务名进行标准化处理：如book.upload转换为book-upload，
     *
     * @param serviceName
     * @return
     */
    public String transform(String serviceName) {
        if (serviceName != null) {
            serviceName = serviceName.replace(".", "-");
            return serviceName;
        } else {
            return "LACK_SERVICE";
        }
    }

    public String getInterfaceName(Class<?> clazz) {
        Class<?>[] clazzs = clazz.getInterfaces();
        if (clazzs.length > 0) {
            return clazzs[0].getName();
        } else {
            return clazz.getName();
        }
    }

    public String getKey() {
        if (null == clazz) {
            return SVC + "." + ret;
        } else {
            return SVC + transform(clazz) + ERROR + ret;
        }
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}