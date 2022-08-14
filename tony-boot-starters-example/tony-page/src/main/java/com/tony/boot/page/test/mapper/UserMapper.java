package com.tony.boot.page.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.boot.page.test.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Entity User
 */
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from user")
    List<User> listAll();

}




