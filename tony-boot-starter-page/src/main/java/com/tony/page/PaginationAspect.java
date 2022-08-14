/*
 *       Copyright© (2020).
 */
package com.tony.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.common.utils.ServletUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author tony
 * @create 2021-10-19
 * @description:
 */
@Aspect
@Slf4j
public class PaginationAspect {

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.tony.page.Pagination)")
    public void access() {

    }

    @SneakyThrows
    @Around("access()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Pagination pagination = getPaginationAnnotation(joinPoint);
        startPage(pagination.pageNo(), pagination.pageSize());
        // 调用原本方法的内容并获取返回值
        Object result = joinPoint.proceed(args);
        // 返回的数据类型要保证和注解方法上的一致
        return pageResult(result, pagination.pureMode());
    }

    /**
     * 获取Pagination注解
     *
     * @param joinPoint
     * @return
     */
    public Pagination getPaginationAnnotation(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Pagination pagination = method.getAnnotation(Pagination.class);
        return pagination;
    }

    /**
     * 开始分页
     */
    private void startPage(String pageNoParameterName, String pageSizeParameterName) {
        // 获取pageNo和pageSize
        int pageNo = ServletUtils.getParameterToInt(pageNoParameterName, 1);
        int pageSize = ServletUtils.getParameterToInt(pageSizeParameterName, 10);
        PageHelper.startPage(pageNo, pageSize);
    }

    /**
     * 对分页结果进行包装 如果分页成功则会返回PageResult
     *
     * @param result
     */
    private Object pageResult(Object result, boolean pureMode) {
        /**
         * 如果分页成功，则查询返回的结应该是一个Page {@link com.github.pagehelper.Page}
         * 进行一次强制转换就能获取到 total、pageNo、pageSize 这些字段
         */
        PageInfo pageInfo = null;
        AjaxResult ajaxResult = null;
        // 列表数据 如果方法返回Page则直接使用 如果是AjaxResult则getData再封装
        Object list = null;
        if (result instanceof Page) {
            list = result;
            Page page = (Page) result;
            pageInfo = new PageInfo(page);
        } else if (result instanceof AjaxResult) {
            ajaxResult = (AjaxResult) result;
            Object data = ajaxResult.getData();
            if (data instanceof List) {
                list = data;
                pageInfo = new PageInfo((List) data);
            }
        }
        if (pageInfo != null) {
            PageAjaxResult pageAjaxResult = new PageAjaxResult(ajaxResult);
            pageAjaxResult.setData(list);
            pageAjaxResult.setPageNo(pageInfo.getPageNum());
            pageAjaxResult.setPageSize(pageInfo.getPageSize());
            pageAjaxResult.setTotal(pageInfo.getTotal());

            if (pureMode) {
                return PageResult.of(pageAjaxResult);
            }

            return pageAjaxResult;
        }
        return result;
    }
}