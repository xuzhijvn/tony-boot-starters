package io.github.xuzhijvn.tonyboot.log.dao.service;

import io.github.xuzhijvn.tonyboot.log.dao.entity.SysOperLog;
import io.github.xuzhijvn.tonyboot.log.dao.mapper.SysOperLogMapper;
import io.github.xuzhijvn.tonyboot.tools.text.Convert;
import io.github.xuzhijvn.tonyboot.tools.utils.spring.SpringUtils;

import java.util.List;

/**
 * 操作日志 服务层处理
 *
 * @author tony
 */
public class SysOperLogServiceImpl implements ISysOperLogService {

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        SpringUtils.getBean(SysOperLogMapper.class).insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return SpringUtils.getBean(SysOperLogMapper.class).selectOperLogList(operLog);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteOperLogByIds(String ids) {
        return SpringUtils.getBean(SysOperLogMapper.class).deleteOperLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询操作日志详细
     *
     * @param id 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long id) {
        return SpringUtils.getBean(SysOperLogMapper.class).selectOperLogById(id);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        SpringUtils.getBean(SysOperLogMapper.class).cleanOperLog();
    }
}
