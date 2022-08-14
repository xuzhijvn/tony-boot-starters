package com.tony.mybatis.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.mybatis.example.entity.User;
import com.tony.mybatis.example.service.UserService;
import com.tony.mybatis.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService{

}




