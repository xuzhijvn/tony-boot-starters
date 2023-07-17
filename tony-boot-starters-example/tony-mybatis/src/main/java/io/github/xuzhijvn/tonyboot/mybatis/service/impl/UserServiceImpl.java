package io.github.xuzhijvn.tonyboot.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.xuzhijvn.tonyboot.mybatis.entity.User;
import io.github.xuzhijvn.tonyboot.mybatis.mapper.UserMapper;
import io.github.xuzhijvn.tonyboot.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService{

}




