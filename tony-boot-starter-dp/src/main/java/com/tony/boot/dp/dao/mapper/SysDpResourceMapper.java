package com.tony.boot.dp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.boot.dp.dao.entity.SysDpResource;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Entity SysDpResource
 */
public interface SysDpResourceMapper extends BaseMapper<SysDpResource> {


    Set<SysDpResource> getResourceByUserId(@Param("user_id") long userId);


}




