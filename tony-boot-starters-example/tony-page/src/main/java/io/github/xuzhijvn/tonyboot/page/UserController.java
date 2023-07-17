/*
 *       Copyright© (2020).
 */
package io.github.xuzhijvn.tonyboot.page;

import io.github.xuzhijvn.tonyboot.page.domain.User;
import io.github.xuzhijvn.tonyboot.page.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tony
 * @create 2021-10-19
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 列出所有用户
     *
     * @return
     */
    @Pagination(pageNo = "pageNo1", pageSize = "pageSize1")
    @GetMapping("/list")
    public AjaxResult list() {
        List<User> userList = userMapper.listAll();
        return AjaxResult.success(userList);
    }

    @GetMapping("/list2")
    public AjaxResult list2() {
        List<User> userList = userMapper.listAll();
        return AjaxResult.success(userList);
    }

    @Pagination(pageNo = "pageNo1", pageSize = "pageSize1", pureMode = false)
    @GetMapping("/list3")
    public Object list3() {
        List<User> userList = userMapper.listAll();
        return userList;
    }

}