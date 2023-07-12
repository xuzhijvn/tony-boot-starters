package com.tony.boot.mybatis.example.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tony.boot.mybatis.BindEnum;
import com.tony.boot.mybatis.BindEnumValue;
import com.tony.boot.mybatis.example.Job;
import com.tony.boot.web.json.FastJsonEnumSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户
 *
 * @TableName user
 */
@TableName(value = "user")
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private Long avatarId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态：1启用、0禁用
     */
    private Long enabled;
    /**
     * 部门名称
     */
    private Long deptId;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 岗位名称
     */
    private Long jobId;
    /**
     * 枚举value (返给前端)
     */
    @TableField(exist = false)
    @BindEnumValue(key = "jobId", enumClass = Job.class)
    private String jobText;
    /**
     * 枚举
     */
    @TableField(exist = false)
    @BindEnum(key = "jobId")
    @JSONField(serializeUsing = FastJsonEnumSerializer.class)
    private Job jobEnum;
    /**
     * 创建日期
     */
    private LocalDateTime createTime;
    /**
     * 最后修改密码的日期
     */
    private LocalDateTime lastPasswordResetTime;
    /**
     *
     */
    private String nickName;
    /**
     *
     */
    private String sex;
    /**
     *
     */
    private LocalDateTime updateTime;
    /**
     *
     */
    private Boolean isDel;

    public String getJobText() {
        return jobText;
    }

    public void setJobText(String jobText) {
        this.jobText = jobText;
    }

    public Job getJobEnum() {
        return jobEnum;
    }

    public void setJobEnum(Job jobEnum) {
        this.jobEnum = jobEnum;
    }

    /**
     * ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 头像
     */
    public Long getAvatarId() {
        return avatarId;
    }

    /**
     * 头像
     */
    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    /**
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 状态：1启用、0禁用
     */
    public Long getEnabled() {
        return enabled;
    }

    /**
     * 状态：1启用、0禁用
     */
    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    /**
     * 部门名称
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 部门名称
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 岗位名称
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * 岗位名称
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * 创建日期
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 创建日期
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改密码的日期
     */
    public LocalDateTime getLastPasswordResetTime() {
        return lastPasswordResetTime;
    }

    /**
     * 最后修改密码的日期
     */
    public void setLastPasswordResetTime(LocalDateTime lastPasswordResetTime) {
        this.lastPasswordResetTime = lastPasswordResetTime;
    }

    /**
     *
     */
    public String getNickName() {
        return nickName;
    }

    /**
     *
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     *
     */
    public String getSex() {
        return sex;
    }

    /**
     *
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     *
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     *
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     *
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}