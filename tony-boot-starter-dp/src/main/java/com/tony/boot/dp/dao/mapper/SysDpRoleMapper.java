package com.tony.boot.dp.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tony.boot.dp.dao.entity.SysDpRole;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity SysDpRole
 */
public interface SysDpRoleMapper extends BaseMapper<SysDpRole> {


    IPage<SysDpRole> rolePages(IPage<SysDpRole> page, @Param(Constants.WRAPPER) Wrapper<SysDpRole> queryWrapper);
//    List<SysDpRole> rolePages(IPage<SysDpRole> page , @Param(Constants.WRAPPER) Wrapper<SysDpRole> queryWrapper);

}




