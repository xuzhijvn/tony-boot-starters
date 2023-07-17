package io.github.xuzhijvn.tonyboot.mp.service.impl;

import io.github.xuzhijvn.tonyboot.mp.entity.User;
import io.github.xuzhijvn.tonyboot.mp.mapper.UserMapper;
import io.github.xuzhijvn.tonyboot.mp.service.UserService;
import io.github.xuzhijvn.tonyboot.mybatis.plus.service.impl.TonyServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends TonyServiceImpl<UserMapper, User>
implements UserService {

}




