package io.github.xuzhijvn.tonyboot.dp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.xuzhijvn.tonyboot.dp.dao.entity.SysDpResource;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Entity SysDpResource
 */
public interface SysDpResourceMapper extends BaseMapper<SysDpResource> {


    Set<SysDpResource> getResourceByUserId(@Param("user_id") long userId);


}




