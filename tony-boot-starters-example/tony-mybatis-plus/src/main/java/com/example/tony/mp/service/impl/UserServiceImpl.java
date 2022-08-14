package com.example.tony.mp.service.impl;

import com.example.tony.mp.entity.User;
import com.example.tony.mp.mapper.UserMapper;
import com.example.tony.mp.service.UserService;
import com.tony.boot.mybatis.plus.service.impl.TonyServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends TonyServiceImpl<UserMapper, User>
implements UserService {

}




