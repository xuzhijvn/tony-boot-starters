package com.tony.boot.mybatis.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.boot.mybatis.example.entity.User;
import com.tony.boot.mybatis.example.mapper.UserMapper;
import com.tony.boot.mybatis.example.service.UserService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService{

}




