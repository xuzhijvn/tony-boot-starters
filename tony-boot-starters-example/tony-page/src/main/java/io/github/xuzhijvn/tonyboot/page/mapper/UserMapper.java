package io.github.xuzhijvn.tonyboot.page.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.xuzhijvn.tonyboot.page.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Entity User
 */
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from user")
    List<User> listAll();

}




