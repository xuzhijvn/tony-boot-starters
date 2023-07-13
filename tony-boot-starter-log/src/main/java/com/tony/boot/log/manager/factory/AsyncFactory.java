package com.tony.boot.log.manager.factory;


import com.tony.boot.log.dao.entity.SysOperLog;
import com.tony.boot.log.dao.service.ISysOperLogService;
import com.tony.boot.tools.utils.spring.SpringUtils;
import com.tony.boot.tools.utils.AddressUtils;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author liuhulu
 */
public class AsyncFactory {

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
            }
        };
    }

}
