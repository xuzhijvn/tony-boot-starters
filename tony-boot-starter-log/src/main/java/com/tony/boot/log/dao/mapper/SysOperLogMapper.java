package com.tony.boot.log.dao.mapper;

import com.tony.boot.log.dao.entity.SysOperLog;

import java.util.List;

/**
 * 操作日志 数据层
 *
 * @author tony
 */
public interface SysOperLogMapper {
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    public void insertOperlog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteOperLogByIds(String[] ids);

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    public SysOperLog selectOperLogById(Long id);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
