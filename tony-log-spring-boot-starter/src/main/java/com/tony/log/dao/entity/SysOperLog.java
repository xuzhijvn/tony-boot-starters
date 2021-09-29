package com.tony.log.dao.entity;

import com.tony.log.dao.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

/**
 * 操作日志记录表 oper_log
 *
 * @author tony
 */
public class SysOperLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private Long id;

    /**
     * 操作模块
     */
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    private Integer businessType;

    /**
     * 业务类型数组
     */
    private Integer[] businessTypes;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    private Integer operatorType;

    /**
     * 操作人员
     */
    private String opUser;

    /**
     * 部门名称
     */
    private String deptName = "";

    /**
     * 请求url
     */
    private String operUrl;

    /**
     * 操作地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String errorMsg = "";

    /**
     * 操作时间
     */
    private LocalDateTime updatedAt;
    /**
     * 记录创建时间
     */
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer[] getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(Integer[] businessTypes) {
        this.businessTypes = businessTypes;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperLocation() {
        return operLocation;
    }

    public void setOperLocation(String operLocation) {
        this.operLocation = operLocation;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("businessType", getBusinessType())
                .append("businessTypes", getBusinessTypes())
                .append("method", getMethod())
                .append("requestMethod", getRequestMethod())
                .append("operatorType", getOperatorType())
                .append("opUser", getOpUser())
                .append("deptName", getDeptName())
                .append("operUrl", getOperUrl())
                .append("operIp", getOperIp())
                .append("operLocation", getOperLocation())
                .append("operParam", getOperParam())
                .append("status", getStatus())
                .append("errorMsg", getErrorMsg())
                .append("updatedAt", getUpdatedAt())
                .append("createdAt", getCreatedAt())
                .toString();
    }
}
