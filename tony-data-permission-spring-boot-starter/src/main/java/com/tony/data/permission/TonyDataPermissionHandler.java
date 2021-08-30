/*
*       Copyright© (2020).
*/
package com.tony.data.permission;

import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import net.sf.jsqlparser.expression.Expression;

import java.util.List;

/**
* @author tony
* @create 2021-08-25
* @description: SQL拦截器，拼接权限相关
*/
public class TonyDataPermissionHandler extends DataPermissionInterceptor {

    private List<Permission> pms;

}
