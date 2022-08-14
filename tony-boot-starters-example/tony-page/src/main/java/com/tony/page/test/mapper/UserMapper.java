package com.tony.page.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.page.test.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Entity com.tony.page.test.domain.User
 */
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from user")
    List<User> listAll();

}




